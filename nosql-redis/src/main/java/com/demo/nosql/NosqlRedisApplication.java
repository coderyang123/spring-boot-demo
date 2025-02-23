package com.demo.nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-08-15 16:28:00
 */
@SpringBootApplication
public class NosqlRedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(NosqlRedisApplication.class, args);
  }
}
