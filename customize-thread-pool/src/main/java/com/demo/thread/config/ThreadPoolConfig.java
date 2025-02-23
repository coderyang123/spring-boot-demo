package com.demo.thread.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置类
 *
 * @author yueyang
 * @since 2021-04-12 23:02:00
 */
@Configuration
public class ThreadPoolConfig {

  @Bean
  public ThreadPoolExecutor threadPoolExecutor() {
    // 阻塞队列
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);

    // 拒绝策略
    ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

    return new ThreadPoolExecutor(
        ThreadPoolProperties.CORE_POOL_SIZE,
        ThreadPoolProperties.MAXIMUM_POOL_SIZE,
        ThreadPoolProperties.KEEP_ALIVE_TIME,
        TimeUnit.SECONDS,
        workQueue,
        abortPolicy);
  }
}
