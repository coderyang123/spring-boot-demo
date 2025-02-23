package com.demo.websocket2.config;

import com.demo.websocket2.handler.WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket 配置类
 *
 * @author yueyang
 * @since 2021-04-21 23:38:00
 */
@Configuration
public class WebSocketConfigure implements WebSocketConfigurer {

  private final WebSocketHandler webSocketHandler;

  public WebSocketConfigure(WebSocketHandler webSocketHandler) {
    this.webSocketHandler = webSocketHandler;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler, "/connect").withSockJS();
  }
}
