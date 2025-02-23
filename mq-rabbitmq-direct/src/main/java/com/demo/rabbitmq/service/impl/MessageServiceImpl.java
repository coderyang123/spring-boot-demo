package com.demo.rabbitmq.service.impl;

import com.demo.rabbitmq.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
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
  private final AmqpTemplate amqpTemplate;

  /**
   * 发送消息至MQ队列
   *
   * @param id 消息ID
   */
  @Override
  public void sendMessageId(String id) {
    log.info("发送消息，id:{}", id);
    amqpTemplate.convertAndSend("directExchange", "direct", id);
  }
}
