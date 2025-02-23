package com.demo.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncApplication.class, args);
  }
}
