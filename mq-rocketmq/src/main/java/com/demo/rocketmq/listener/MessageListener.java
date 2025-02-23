package com.demo.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费监听器
 *
 * @author yueyang
 * @since 2022-08-16 10:24:00
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "message-id", consumerGroup = "group-rocketmq")
public class MessageListener implements RocketMQListener<String> {

  @Override
  public void onMessage(String id) {
    log.info("已消费消息，id:{}", id);
  }
}
