package com.demo.easyexcel.common.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 导出Excel线程池配置类
 *
 * @author yueyang
 * @since 2022-07-20 10:02:00
 */
@Configuration
public class ThreadPoolConfig {

  @Bean
  public ThreadPoolTaskExecutor exportThreadPoolExecutor() {
    // 机器核心线程数
    int processors = Runtime.getRuntime().availableProcessors();

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(processors + 1);
    executor.setMaxPoolSize(processors * 2);
    executor.setKeepAliveSeconds(30);
    executor.setQueueCapacity(1000);
    executor.setThreadNamePrefix("自定义导出Excel线程-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    return executor;
  }
}
