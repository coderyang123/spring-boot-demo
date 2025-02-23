package com.demo.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-08-15 14:58:00
 */
@EnableCaching
@SpringBootApplication
public class CacheEhcacheApplication {

  public static void main(String[] args) {
    SpringApplication.run(CacheEhcacheApplication.class, args);
  }
}
