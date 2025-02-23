package com.demo.easyexcel;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.demo.easyexcel.mapper")
@EnableAsync
public class EasyexcelApplication {

  public static void main(String[] args) {
    SpringApplication.run(EasyexcelApplication.class, args);
  }
}
