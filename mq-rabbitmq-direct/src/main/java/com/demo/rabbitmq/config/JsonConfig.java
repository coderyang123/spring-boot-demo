package com.demo.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JSON消息转换器配置类
 *
 * @author yueyang
 * @since 2023-04-05 16:02:00
 */
@Configuration
public class JsonConfig {
  /**
   * 配置消息转换器
   *
   * @return 消息转换器
   */
  @Bean(name = "jsonMessageConverter")
  public Jackson2JsonMessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }
}
