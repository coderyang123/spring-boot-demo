package com.demo.packagewar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 * @author yueyang
 * @since 2021-04-19 21:50:00
 */
@SpringBootApplication
@RestController
public class PackageWarApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(PackageWarApplication.class, args);
  }

  /**
   * 若需要打成 war 包，则需要写一个类继承 {@link SpringBootServletInitializer} 并重写 {@link
   * SpringBootServletInitializer#configure(SpringApplicationBuilder)}
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(PackageWarApplication.class);
  }

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
