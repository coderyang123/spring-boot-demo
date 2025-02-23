package com.demo.rocketmq.service.impl;

import com.demo.rocketmq.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
  private final RocketMQTemplate rocketMQTemplate;

  /**
   * 同步发送消息至MQ队列
   *
   * @param id 消息ID
   */
  @Override
  public void synchronizedSendMessageId(String id) {
    log.info("发送消息，id:{}", id);
    rocketMQTemplate.convertAndSend("message-id", id);
  }

  /**
   * 异步发送消息至MQ队列
   *
   * @param id 消息ID
   */
  @Override
  public void asynchronizedSendMessageId(String id) {
    SendCallback sendCallback =
        new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            log.info("消息发送成功，id:{}", id);
          }

          @Override
          public void onException(Throwable throwable) {
            log.error("消息发送失败，id:{}", id, throwable);
          }
        };
    rocketMQTemplate.asyncSend("message-id", id, sendCallback);
  }
}
