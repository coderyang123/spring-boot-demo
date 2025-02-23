package com.demo.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
@EnableWebSecurity
@MapperScan("com.demo.springsecurity.mapper")
public class SpringSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApplication.class, args);
  }
}
