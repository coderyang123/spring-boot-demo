package com.demo.multi.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author yueyang
 * @since 2022-06-01 16:30:00
 */
@SpringBootApplication
@MapperScan(basePackages = "com.demo.multi.datasource.mapper")
public class MultiDatasourceMybatisApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultiDatasourceMybatisApplication.class, args);
  }
}
