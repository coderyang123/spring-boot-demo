package com.demo.jetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@RestController
@SpringBootApplication
public class CollectionJettyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CollectionJettyApplication.class, args);
  }

  @GetMapping("/request")
  public String test() {
    return "response jetty!!!";
  }
}
