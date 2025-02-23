package com.demo.websocket3.handler;

import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 客户端服务端发消息
 *
 * @author yueyang
 * @since 2021-06-07 14:45:00
 */
@Slf4j
@ServerEndpoint(value = "/oneToService") // 前端通过此URL和后端交互，建立连接
@Component
public class OneToService {

  /** 在线人数 */
  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

  /**
   * 成功建立连接时
   *
   * @param session 会话
   */
  @OnOpen
  public void onOpen(Session session) {
    // 在线人数加1
    ONLINE_COUNT.incrementAndGet();

    // 将连接信息放入客户端
    log.info("新用户{}加入，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 关闭连接时
   *
   * @param session 会话
   */
  @OnClose
  public void onClose(Session session) {
    // 在线人数减1
    ONLINE_COUNT.decrementAndGet();
    log.info("用户{}退出，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 客户端发送过来的消息
   *
   * @param message 消息
   * @param session 会话
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);

    // 给客户端回消息
    sendMessage("Hello, " + message, session);
  }

  /**
   * 发生错误时
   *
   * @param session 会话
   * @param error 错误
   */
  @OnError
  public void onError(Session session, Throwable error) {
    log.error("Websocket发生错误:" + error);
  }

  /**
   * 回消息
   *
   * @param message 消息
   * @param session 会话
   */
  public void sendMessage(String message, Session session) {
    try {
      log.info("服务端给客户端[{}]发送消息，内容：{}", session.getId(), message);
      session.getBasicRemote().sendText(message);
    } catch (Exception e) {
      log.error("消息解析异常：", e);
    }
  }
}
