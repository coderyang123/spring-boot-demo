package com.demo.rabbitmq;

import com.demo.rabbitmq.domain.User;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MqRabbitmqApplicationTests {
  /** RabbitTemplate为我们封装了大量的RabbitMQ操作，已经由Starter提供，因此直接注入使用即可 */
  @Resource RabbitTemplate template;

  @Test
  void contextLoads() {}

  @Test
  void testConvertAndSend() {
    // 使用convertAndSend方法一步到位，参数基本和之前是一样的
    // 最后一个消息本体可以是Object类型，真是大大的方便
    template.convertAndSend("amq.direct", "testRoutingKey", "Hello World!");
  }

  @Test
  void testConvertSendAndReceive() {
    // 使用convertAndSend方法一步到位，参数基本和之前是一样的
    // 最后一个消息本体可以是Object类型，真是大大的方便
    Object receive = template.convertSendAndReceive("amq.direct", "testRoutingKey", "Hello World!");
    log.info("接收到的消息：{}", receive);
  }

  @Test
  void testConvertSendAndReceive2() {
    // 使用convertAndSend方法一步到位，参数基本和之前是一样的
    // 最后一个消息本体可以是Object类型，真是大大的方便
    Object receive =
        template.convertSendAndReceive("amq.direct", "testRoutingKey", new User(1, "张三", 18));
    log.info("接收到的消息：{}", receive);
  }
}
