package com.demo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@SpringBootApplication
@MapperScan("com.demo.mybatisplus.mapper")
public class OrmMybatisPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrmMybatisPlusApplication.class, args);
  }
}
