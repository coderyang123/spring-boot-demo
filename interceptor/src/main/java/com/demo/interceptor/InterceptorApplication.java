package com.demo.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2021-08-04 21:48:00
 */
@SpringBootApplication
@RestController
public class InterceptorApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterceptorApplication.class, args);
  }

  @GetMapping("noAuthentication")
  public String noAuthentication() {
    return "无需拦截";
  }

  @GetMapping("noAuthentication2")
  public String noAuthentication2() {
    return "无需拦截2";
  }

  @GetMapping("needAuthentication")
  public String needAuthentication() {
    return "需拦截";
  }
}
