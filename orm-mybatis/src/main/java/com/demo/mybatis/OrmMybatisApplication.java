package com.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
// 配置包扫描路径
@MapperScan(basePackages = "com.demo.mybatis.mapper")
public class OrmMybatisApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrmMybatisApplication.class, args);
  }
}
