package com.demo.alllearning.common.config;

import com.demo.alllearning.common.constant.CacheConstants;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存管理器配置类
 *
 * @author yueyang
 * @since 2021-03-09 22:34:00
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

  /**
   * 缓存管理器实现类
   *
   * @return 缓存管理
   */
  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
    // 缓存集合
    List<CaffeineCache> cacheList = new ArrayList<>();
    cacheList.add(
        new CaffeineCache(
            CacheConstants.USERS_CACHE,
            Caffeine.newBuilder()
                // 最大缓存数据量
                .maximumSize(1000)
                // 缓存过期时间（最后一次访问后120过期）
                .expireAfterAccess(120, TimeUnit.SECONDS)
                .build()));
    simpleCacheManager.setCaches(cacheList);
    return simpleCacheManager;
  }
}
