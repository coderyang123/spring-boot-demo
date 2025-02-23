package com.demo.cache.entity;

import com.demo.cache.utils.LocalCacheUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 清理过期缓存线程类
 *
 * @author yueyang
 * @since 2022-06-23 13:48:00
 */
@Slf4j
public class CleanTimeOutThread implements Runnable {

  /** 设置清理线程的运行状态为正在运行 */
  public static void setCleanThreadRun() {
    LocalCacheUtils.CLEAN_THREAD_IS_RUN = true;
  }

  /** 设置清理线程的运行状态为停止运行 */
  public static void setCleanThreadStop() {
    LocalCacheUtils.CLEAN_THREAD_IS_RUN = false;
  }

  /** 清理过期缓存 */
  @Override
  public void run() {
    log.info("清理本地过期的缓存线程启动...");
    setCleanThreadRun();
    LocalCacheUtils.deleteTimeOut();

    // 模拟清理耗时
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    setCleanThreadStop();
  }
}
