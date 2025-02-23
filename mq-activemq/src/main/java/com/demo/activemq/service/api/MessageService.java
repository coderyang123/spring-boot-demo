package com.demo.activemq.service.api;

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

  /**
   * 发送消息至MQ队列
   *
   * @param name 消息名
   */
  void sendMessageName(String name);

  /**
   * 发送消息至MQ队列
   *
   * @param data 消息名
   */
  void sendMessageData(String data);

  /**
   * 消费消息
   *
   * @return 消息ID
   */
  String consumeMessage();
}
