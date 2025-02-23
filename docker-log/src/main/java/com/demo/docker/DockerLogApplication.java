package com.demo.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-07-28 16:30:00
 */
@RestController
@EnableScheduling
@SpringBootApplication
public class DockerLogApplication {

  public static void main(String[] args) {
    SpringApplication.run(DockerLogApplication.class, args);
  }

  @GetMapping("/")
  public String hello() {
    return "hello world.";
  }
}
