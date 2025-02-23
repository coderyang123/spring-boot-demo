package com.demo.websocket3.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 一对多群发消息
 *
 * @author yueyang
 * @since 2021-06-07 14:21:00
 */
@Slf4j
@ServerEndpoint(value = "/oneToMany") // 前端通过此URL和后端交互，建立连接
@Component
public class OneToMany {

  /** 在线人数 */
  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

  /** 在线客户端 */
  private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();

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
    CLIENTS.put(session.getId(), session);
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
    CLIENTS.remove(session.getId());
    log.info("用户{}退出，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
  }

  /**
   * 接收到消息时
   *
   * @param message 消息
   * @param session 会话
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
    sendMessage(message, session);
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
   * 群发消息
   *
   * @param message 消息
   * @param session 会话
   */
  public void sendMessage(String message, Session session) {
    // 遍历客户端在线人数
    CLIENTS.forEach(
        (id, sessionEntry) -> {
          // 拿到消息
          String sessionId = session.getId();

          // 排除自己
          if (!sessionId.equals(sessionEntry.getId())) {
            log.info("服务端转发客户端[{}]的群发消息给客户端[{}]，消息内容：{}", sessionId, sessionEntry.getId(), message);
            sessionEntry.getAsyncRemote().sendText(message);
          }
        });
  }
}
