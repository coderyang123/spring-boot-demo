package com.demo.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
// 配置包扫描路径
@MapperScan(basePackages = {"com.demo.mybatis.mapper"})
public class OrmMybatisHelperApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrmMybatisHelperApplication.class, args);
  }
}
