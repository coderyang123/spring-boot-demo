package com.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连模式配置类
 *
 * @author yueyang
 * @since 2022-08-16 15:04:00
 */
@Configuration
public class RabbitMQConfig {

  /**
   * 配置队列
   *
   * @return 消息队列
   */
  @Bean
  public Queue directQueue() {
    return new Queue("directQueue");
  }

  /**
   * 配置队列2
   *
   * @return 消息队列2
   */
  @Bean("testQueue")
  public Queue queue() {
    return QueueBuilder
        // 非持久化类型
        .nonDurable("testQueue")
        .build();
  }

  /**
   * 配置交换机
   *
   * @return 交换机
   */
  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("directExchange");
  }

  /**
   * 配置交换机2
   *
   * @return 交换机2
   */
  @Bean("testDirectExchange")
  public DirectExchange exchange() {
    return ExchangeBuilder.directExchange("amq.direct").build();
  }

  /**
   * 绑定队列1和交换机
   *
   * @return 绑定
   */
  @Bean
  public Binding bindingExchange() {
    return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRoutingKey");
  }

  /**
   * 绑定队列2和交换机2
   *
   * @return 绑定
   */
  @Bean("testBinding")
  public Binding bindingExchange2(
      @Qualifier("testDirectExchange") Exchange exchange, @Qualifier("testQueue") Queue queue) {
    return BindingBuilder.bind(queue).to(exchange).with("testRoutingKey").noargs();
  }
}
