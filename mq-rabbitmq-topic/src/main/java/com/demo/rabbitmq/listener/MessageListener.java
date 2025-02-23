package com.demo.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费监听器
 *
 * @author yueyang
 * @since 2022-08-16 10:24:00
 */
@Slf4j
@Component
public class MessageListener {

  @RabbitListener(queues = "topic-queue")
  public void receiveId(String id) {
    log.info("1已消费消息，id:{}", id);
  }

  @RabbitListener(queues = "topic-queue2")
  public void receiveId2(String id) {
    log.info("2已消费消息，id:{}", id);
  }
}
