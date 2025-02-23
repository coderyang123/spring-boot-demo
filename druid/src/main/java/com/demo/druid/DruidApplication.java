package com.demo.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 * @author yueyang
 * @since 2021-05-28 10:14:00
 */
@SpringBootApplication
@RestController
@MapperScan("com.demo.druid.mapper")
public class DruidApplication {

  public static void main(String[] args) {
    SpringApplication.run(DruidApplication.class, args);
  }
}
