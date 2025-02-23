package com.demo.async.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置（和yml文件的配置作用一样，二者可互换）
 *
 * @author yueyang
 * @since 2021-03-11 20:35:00
 */
@Configuration
public class ExecutorConfig {

  /**
   * 自定义线程池
   *
   * @return 线程池
   */
  @Bean
  public Executor serviceExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    // 核心线程数（当前机器的核心数）
    threadPoolTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
    // 线程名称前缀
    threadPoolTaskExecutor.setThreadNamePrefix("async-task-");
    // 拒绝策略
    threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    // 执行初始化
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor;
  }
}
