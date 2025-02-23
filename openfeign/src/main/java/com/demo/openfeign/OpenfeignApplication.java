package com.demo.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
@EnableFeignClients
public class OpenfeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenfeignApplication.class, args);
  }
}
