package com.demo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-08-16 15:04:00
 */
@SpringBootApplication
public class MqRabbitmqTopicApplication {

  public static void main(String[] args) {
    SpringApplication.run(MqRabbitmqTopicApplication.class, args);
  }
}
