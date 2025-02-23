package com.demo.websocket3.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.websocket3.domain.Message;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 一对一发消息
 *
 * @author yueyang
 * @since 2021-06-07 14:31:00
 */
@Slf4j
@ServerEndpoint(value = "/oneToOne") // 前端通过此URL和后端交互，建立连接
@Component
public class OneToOne {

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
   * 客户端发送过来的消息
   *
   * @param message 消息
   * @param session 会话
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    String sessionId = session.getId();
    String[] messages = message.split(" ");

    // 将客户端的消息转换为json字符串
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("message", messages[0]);
    jsonObject.put("userId", messages[1]);
    String jsonString = jsonObject.toJSONString();

    log.info("服务端收到客户端[{}]的消息[{}]", sessionId, jsonString);
    try {
      Message messageContent = JSON.parseObject(jsonString, Message.class);
      if (!Objects.isNull(messageContent)) {
        Session toSession = CLIENTS.get(messageContent.getUserId());
        if (!Objects.isNull(toSession)) {
          sendMessage(messageContent.getMessage(), toSession, sessionId);
        }
      }
    } catch (Exception e) {
      log.error("消息解析异常：", e);
    }
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
   * 服务端发送消息给客户端
   *
   * @param message 消息
   * @param session 会话
   * @param sessionId 会话ID
   */
  public void sendMessage(String message, Session session, String sessionId) {
    try {
      log.info("服务端转发客户端[{}]的消息给客户端[{}]，内容：{}", sessionId, session.getId(), message);
      session.getBasicRemote().sendText(message);
    } catch (Exception e) {
      log.error("消息解析异常：", e);
    }
  }
}
