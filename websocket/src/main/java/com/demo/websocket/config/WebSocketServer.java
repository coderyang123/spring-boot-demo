package com.demo.websocket.config;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务端和客户端交互配置类
 *
 * @author yueyang
 * @since 2022-07-26 17:38:00
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket/{uid}")
public class WebSocketServer {
  // 静态变量，用来记录当前设备在线连接数。应该把它设计成线程安全的。
  private static final AtomicInteger onlineNum = new AtomicInteger(0);

  // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
  private static final CopyOnWriteArraySet<Session> sessionPools = new CopyOnWriteArraySet<>();

  /** 有客户端连接成功 */
  @OnOpen
  public void onOpen(Session session, @PathParam(value = "uid") String uid) {
    sessionPools.add(session);
    onlineNum.incrementAndGet();
    log.info(uid + "加入webSocket！当前人数为" + onlineNum);
  }

  /** 连接关闭调用的方法 */
  @OnClose
  public void onClose(Session session) {
    sessionPools.remove(session);
    int num = onlineNum.decrementAndGet();
    log.info("有连接关闭，当前连接数为：{}", num);
  }

  /** 发送消息 */
  public void sendMessage(Session session, String message) throws IOException {
    if (Objects.nonNull(session)) {
      synchronized (session) {
        session.getBasicRemote().sendText(message);
      }
    }
  }

  /** 群发消息 */
  public void broadCastInfo(String message) throws IOException {
    for (Session session : sessionPools) {
      if (session.isOpen()) {
        sendMessage(session, message);
      }
    }
  }

  /** 发生错误 */
  @OnError
  public void onError(Session session, Throwable throwable) {
    log.error("发生未知错误，【Session】：{}", session, throwable);
  }
}
