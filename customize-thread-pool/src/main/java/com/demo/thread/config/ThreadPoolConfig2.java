package com.demo.thread.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置类2
 *
 * @author yueyang
 * @since 2022-08-08 10:02:00
 */
@Configuration
public class ThreadPoolConfig2 {

  @Bean
  public ThreadPoolExecutor threadPoolExecutor() {
    // 阻塞队列
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);

    // 拒绝策略
    ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

    // 机器核心线程数
    int processors = Runtime.getRuntime().availableProcessors();

    return new ThreadPoolExecutor(
        processors + 1, processors * 2, 10L, TimeUnit.SECONDS, workQueue, abortPolicy);
  }
}
