package com.demo.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-06-23 11:30:00
 */
@SpringBootApplication
@EnableScheduling
public class LocalCacheApplication {

  public static void main(String[] args) {
    SpringApplication.run(LocalCacheApplication.class, args);
  }
}
