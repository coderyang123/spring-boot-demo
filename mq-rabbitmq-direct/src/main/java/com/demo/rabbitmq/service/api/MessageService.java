package com.demo.rabbitmq.service.api;

/**
 * 消息服务
 *
 * @author yueyang
 * @since 2022-08-16 10:00:00
 */
public interface MessageService {

  /**
   * 发送消息至MQ队列
   *
   * @param id 消息ID
   */
  void sendMessageId(String id);
}
