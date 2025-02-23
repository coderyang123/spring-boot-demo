package com.demo.activemq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
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

  @JmsListener(destination = "message.queue.name")
  public void receiveName(String name) {
    log.info("已完成消息发送业务，name:{}", name);
  }

  @JmsListener(destination = "message.queue.data")
  @SendTo("message.queue.data.next")
  public String receiveData(String data) {
    log.info("已完成消息发送业务，data:{}", data);
    return data;
  }

  @JmsListener(destination = "message.queue.data.next")
  public void receiveDataNext(String data) {
    log.info("已完成流转来的消息发送业务，data.next:{}", data);
  }
}
