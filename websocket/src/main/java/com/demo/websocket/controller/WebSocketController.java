package com.demo.websocket.controller;

import com.demo.websocket.config.WebSocketServer;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接受消息
 *
 * @author yueyang
 * @since 2022-07-26 17:40:00
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/socket")
public class WebSocketController {
  private final WebSocketServer webSocketServer;

  @Value("${socket.password}")
  public String password;

  /**
   * 模拟手机客户端请求接口
   *
   * @param id 发生异常的设备ID
   * @param pwd 密码（实际开发记得加密）
   * @throws IOException IO异常
   */
  @PostMapping(value = "/onReceive")
  public void onReceive(String id, String pwd) throws IOException {
    // 密码校验一致（这里举例，实际开发还要有个密码加密的校验的），则进行群发
    if (pwd.equals(password)) {
      webSocketServer.broadCastInfo(id);
    }
  }
}
