package com.demo.packagejar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 * @author yueyang
 * @since 2021-11-11 11:27:00
 */
@SpringBootApplication
@RestController
public class PackageJarApplication {

  public static void main(String[] args) {
    SpringApplication.run(PackageJarApplication.class, args);
  }

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
