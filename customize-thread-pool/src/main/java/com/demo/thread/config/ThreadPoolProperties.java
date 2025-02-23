package com.demo.thread.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池参数类
 *
 * @author yueyang
 * @since 2021-04-12 23:02:00
 */
@ConfigurationProperties("thread-pool")
@Component
@Data
public class ThreadPoolProperties implements InitializingBean {

  public static Integer CORE_POOL_SIZE;
  public static Integer MAXIMUM_POOL_SIZE;
  public static Long KEEP_ALIVE_TIME;

  /** 核心线程数（默认3） */
  Integer corePoolSize = 3;

  /** 最大线程数（默认6） */
  Integer maximumPoolSize = 6;

  /** 超过 corePoolSize 线程数量的线程最大空闲时间（默认10） */
  Long keepAliveTime = 10L;

  /** 当初始化Bean完成，私有成员变量被赋值后，给常量字段赋值 */
  @Override
  public void afterPropertiesSet() {
    CORE_POOL_SIZE = corePoolSize;
    MAXIMUM_POOL_SIZE = maximumPoolSize;
    KEEP_ALIVE_TIME = keepAliveTime;
  }
}
