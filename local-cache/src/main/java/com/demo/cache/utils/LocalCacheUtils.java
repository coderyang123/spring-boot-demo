package com.demo.cache.utils;

import com.demo.cache.config.CacheThreadFactory;
import com.demo.cache.entity.Cache;
import com.demo.cache.entity.CleanTimeOutThread;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 本地缓存工具类
 *
 * @author yueyang
 * @since 2022-06-23 11:22:00
 */
@Slf4j
@Component
public class LocalCacheUtils {

  /** 缓存最大个数 */
  private static final int CACHE_MAX_NUMBER = 100;

  /** 清理过期缓存是否在运行 */
  public static boolean CLEAN_THREAD_IS_RUN = false;

  /** 当前缓存个数 */
  private static int CURRENT_SIZE = 0;

  /** 缓存数据集合 */
  public static final Map<String, Cache> CACHE_DATA_MAP = new ConcurrentHashMap<>();

  /** 记录缓存使用的最近一次的记录 */
  private static final List<String> CACHE_USE_LOG_LIST = new LinkedList<>();

  /** 清理过期缓存线程池 */
  private static final ExecutorService EXECUTOR =
      Executors.newSingleThreadExecutor(new CacheThreadFactory());

  /**
   * 设置缓存
   *
   * @param cacheKey 缓存键
   * @param cacheValue 缓存值
   * @param cacheTime 缓存时长
   */
  public static void setCache(String cacheKey, Object cacheValue, long cacheTime) {
    long ttlTime;
    if (cacheTime < -1L) {
      return;
    }
    if (cacheTime == -1L) {
      ttlTime = cacheTime;
    } else {
      ttlTime = System.currentTimeMillis() + cacheTime;
    }

    // 清理缓存池
    cleanCache();

    // 记录缓存日志
    saveCacheUseLog(cacheKey);

    CURRENT_SIZE++;
    Cache cache = new Cache(cacheValue, ttlTime);
    CACHE_DATA_MAP.put(cacheKey, cache);
    log.info("设置了缓存：" + cacheKey);
  }

  /**
   * 设置缓存
   *
   * @param cacheKey 缓存键
   * @param cacheValue 缓存值
   */
  public static void setCache(String cacheKey, Object cacheValue) {
    setCache(cacheKey, cacheValue, -1L);
  }

  /**
   * 获取缓存值
   *
   * @param cacheKey 缓存键
   * @return 缓存值
   */
  public static Object getCache(String cacheKey) {
    // 删除过期缓存
    startCleanThread();

    if (isExistCache(cacheKey)) {
      saveCacheUseLog(cacheKey);
      return CACHE_DATA_MAP.get(cacheKey).getCacheValue();
    }
    return null;
  }

  /** 删除所有缓存 */
  public static void clear() {
    log.info("删除了所有缓存!");
    CACHE_DATA_MAP.clear();
    CURRENT_SIZE = 0;
  }

  /**
   * 删除某个缓存
   *
   * @param cacheKey 缓存键
   */
  public static void deleteCache(String cacheKey) {
    Object cacheValue = CACHE_DATA_MAP.remove(cacheKey);
    if (!Objects.isNull(cacheValue)) {
      log.info("删除了缓存：" + cacheKey);
      CURRENT_SIZE--;
    }
  }

  /**
   * 判断缓存是否存在
   *
   * @param cacheKey 缓存键
   * @return 是否存在
   */
  private static boolean isExistCache(String cacheKey) {
    Cache cache = CACHE_DATA_MAP.get(cacheKey);
    if (cache == null) {
      return false;
    }
    if (cache.getTtlTime() == -1L) {
      return true;
    }
    if (cache.getTtlTime() < System.currentTimeMillis()) {
      deleteCache(cacheKey);
      return false;
    }
    return true;
  }

  /** 删除最近最久未使用的缓存 */
  private static void deleteLRU() {
    log.info("删除了最近且最久未被使用的缓存!");
    String cacheKey = CACHE_USE_LOG_LIST.remove(CACHE_USE_LOG_LIST.size() - 1);
    deleteCache(cacheKey);
  }

  /** 删除过期的缓存 */
  public static void deleteTimeOut() {
    log.info("开始清理本地过期的缓存");
    List<String> deleteKeyList = new LinkedList<>();
    CACHE_DATA_MAP.forEach(
        (cacheKey, cacheData) -> {
          if (cacheData.getTtlTime() < System.currentTimeMillis()
              && cacheData.getTtlTime() != -1L) {
            deleteKeyList.add(cacheKey);
          }
        });
    deleteKeyList.forEach(LocalCacheUtils::deleteCache);
    log.info("共删除{}个过期的缓存", deleteKeyList.size());
  }

  /** 清理缓存池 当当前大小如果已经达到最大大小 首先删除过期缓存，如果过期缓存删除过后还是达到最大缓存数目 删除最久未使用缓存 */
  private static void cleanCache() {
    if (CURRENT_SIZE >= CACHE_MAX_NUMBER) {
      deleteTimeOut();
    }
    if (CURRENT_SIZE >= CACHE_MAX_NUMBER) {
      deleteLRU();
    }
  }

  /** 保存缓存的使用记录 */
  private static synchronized void saveCacheUseLog(String cacheKey) {
    CACHE_USE_LOG_LIST.remove(cacheKey);
    CACHE_USE_LOG_LIST.add(0, cacheKey);
  }

  /** 项目启动后10s开启清理过期缓存的线程一次，然后每隔2min开启一次 */
  @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 60 * 2)
  private static void startCleanThread() {
    if (!CLEAN_THREAD_IS_RUN) {
      EXECUTOR.submit(new CleanTimeOutThread());
    }
  }

  /** 获取缓存池信息 */
  public static void showUtilsInfo() {
    log.info("clean time out cache thread is run :" + CLEAN_THREAD_IS_RUN);
    log.info("cache max count is :" + CACHE_MAX_NUMBER);
    log.info("cache current count is :" + CURRENT_SIZE);
    log.info("cache object map is :" + CACHE_DATA_MAP);
    log.info("cache use log list is :" + CACHE_USE_LOG_LIST);
  }
}
