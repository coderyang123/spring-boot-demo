package com.demo.cache.config;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义缓存线程工厂
 *
 * @author yueyang
 * @since 2022-06-23 18:07:00
 */
public class CacheThreadFactory implements ThreadFactory {

  @Override
  public Thread newThread(Runnable r) {
    return new Thread(r, "自定义过期缓存清理线程");
  }
}
