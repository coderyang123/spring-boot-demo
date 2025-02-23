package com.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题配置类
 *
 * @author yueyang
 * @since 2022-08-16 15:04:00
 */
@Configuration
public class RabbitTopicConfig {

  /**
   * 配置队列
   *
   * @return 消息队列
   */
  @Bean
  public Queue topicQueue() {
    return new Queue("topic-queue");
  }

  /**
   * 配置队列
   *
   * @return 消息队列
   */
  @Bean
  public Queue topicQueue2() {
    return new Queue("topic-queue2");
  }

  /**
   * 配置交换机
   *
   * @return 交换机
   */
  @Bean
  public DirectExchange topicExchange() {
    return new DirectExchange("topicExchange");
  }

  /**
   * 绑定队列1和交换机
   *
   * @return 绑定
   */
  @Bean
  public Binding bindingExchange() {
    return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.*.id");
  }

  /**
   * 绑定队列2和交换机
   *
   * @return 绑定
   */
  @Bean
  public Binding bindingExchange2() {
    return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.message.*");
  }
}
