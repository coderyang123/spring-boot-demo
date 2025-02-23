package com.demo.websocket2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 主启动类
 *
 * @author yueyang
 * @since 2022-03-01 16:30:00
 */
@SpringBootApplication
@EnableWebSocket
public class Websocket2Application {

  public static void main(String[] args) {
    SpringApplication.run(Websocket2Application.class, args);
  }
}
