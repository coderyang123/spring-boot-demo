package com.demo.websocket3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Websocket配置类
 *
 * @author yueyang
 * @since 2021-06-07 14:51:00
 */
@Configuration
public class WebSocketConfiguration {

  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }
}
