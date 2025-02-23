package com.demo.rocketmq.service.api;

/**
 * 消息服务
 *
 * @author yueyang
 * @since 2022-08-16 10:00:00
 */
public interface MessageService {

  /**
   * 同步发送消息至MQ队列
   *
   * @param id 消息ID
   */
  void synchronizedSendMessageId(String id);

  /**
   * 异步发送消息至MQ队列
   *
   * @param id 消息ID
   */
  void asynchronizedSendMessageId(String id);
}
