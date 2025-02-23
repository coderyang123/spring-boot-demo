package com.demo.activemq.service.impl;

import com.demo.activemq.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息服务
 *
 * @author yueyang
 * @since 2022-08-16 10:04:00
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
  private final JmsMessagingTemplate jmsMessagingTemplate;

  /**
   * 发送消息至MQ队列
   *
   * @param id 消息ID
   */
  @Override
  public void sendMessageId(String id) {
    log.info("待发送消息，id:{}", id);
    jmsMessagingTemplate.convertAndSend("message.queue.id", id);
  }

  /**
   * 发送消息至MQ队列
   *
   * @param name 消息名
   */
  @Override
  public void sendMessageName(String name) {
    log.info("待发送消息，name:{}", name);
    jmsMessagingTemplate.convertAndSend("message.queue.name", name);
  }

  /**
   * 发送消息至MQ队列
   *
   * @param data 消息数据
   */
  @Override
  public void sendMessageData(String data) {
    log.info("待发送消息，data:{}", data);
    jmsMessagingTemplate.convertAndSend("message.queue.data", data);
  }

  /**
   * 消费消息
   *
   * @return 消息ID
   */
  @Override
  public String consumeMessage() {
    String id = jmsMessagingTemplate.receiveAndConvert("message.queue.id", String.class);
    log.info("已完成消息发送，id:{}, ", id);
    return id;
  }
}
