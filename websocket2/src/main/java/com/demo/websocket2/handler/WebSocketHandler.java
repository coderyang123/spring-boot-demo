package com.demo.websocket2.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket 消息处理器
 *
 * @author yueyang
 * @since 2021-04-21 23:35:00
 */
@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

  private static String fakeAi(String input) {
    if (input == null || "".equals(input)) {
      return "你说什么？没听清";
    }
    return input.replace('你', '我').replace("吗", "").replace('?', '!').replace('？', '！');
  }

  @Override
  public void afterConnectionEstablished(@NonNull WebSocketSession session) {
    log.info("和客户端建立连接");
  }

  @Override
  public void handleTransportError(WebSocketSession session, @NonNull Throwable exception)
      throws Exception {
    session.close(CloseStatus.SERVER_ERROR);
    log.error("连接异常", exception);
  }

  @Override
  public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status)
      throws Exception {
    super.afterConnectionClosed(session, status);
    log.info("和客户端断开连接");
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 获取到客户端发送过来的消息
    String receiveMessage = message.getPayload();
    log.info("客户端发送过来的消息:{}", receiveMessage);

    // 发送消息给客户端
    session.sendMessage(new TextMessage(fakeAi(receiveMessage)));
    // 关闭连接
    // session.close(CloseStatus.NORMAL);
  }
}
