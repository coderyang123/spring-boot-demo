package com.demo.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费监听器
 *
 * @author yueyang
 * @since 2022-08-16 10:24:00
 */
@Slf4j
@Component
public class InfoListener implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    log.info("已完成消息发送，message:{}", message.toString());
  }
}
