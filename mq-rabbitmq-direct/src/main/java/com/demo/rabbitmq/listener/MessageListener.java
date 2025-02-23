package com.demo.rabbitmq.listener;

import com.demo.rabbitmq.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
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
  /**
   * 监听消息
   *
   * @param message 消息
   */
  @RabbitListener(queues = "directQueue")
  public void receiveMessage(Message message) {
    log.info("已经监听到消息：{}", new String(message.getBody()));
  }

  /**
   * 也可以直接监听Message类型的消息
   *
   * @param message 消息
   */
  /*@RabbitListener(queues = "testQueue")
  public void receiveMessage2(Message message) {
    log.info("已经监听到Message类型的消息：{}", new String(message.getBody()));
  }*/

  /**
   * 也可以直接监听String类型的消息
   *
   * @param message 消息
   */
  /*@RabbitListener(queues = "testQueue")
  public void receiveMessage2(String message) {
    log.info("已经监听到String类型的消息：{}", message);
  }*/

  /**
   * 也可以直接监听String类型的消息，并且返回响应
   *
   * @param message 消息
   */
  /*@RabbitListener(queues = "testQueue")
  public String receiveMessage3(String message) {
    log.info("已经监听到String类型的消息：{}", message);
    return "响应成功！";
  }*/

  /**
   * 也可以直接监听实体类型的消息
   *
   * @param user 消息
   */
  @RabbitListener(queues = "testQueue", messageConverter = "jsonMessageConverter")
  public String receiveMessage3(User user) {
    log.info("已经监听到实体类型的消息：{}", user);
    return "响应成功！";
  }
}
