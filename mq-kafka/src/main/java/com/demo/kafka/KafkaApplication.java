package com.demo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 * @author yueyang
 * @since 2022-04-28 09:30:00
 */
@SpringBootApplication
@RestController
public class KafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplication.class, args);
  }
}
