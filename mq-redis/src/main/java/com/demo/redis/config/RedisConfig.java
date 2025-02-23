package com.demo.redis.config;

import com.demo.redis.listener.InfoListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Redis配置类
 *
 * @author yueyang
 * @since 2022-08-24 10:49:00
 */
@RequiredArgsConstructor
@AutoConfigureAfter(InfoListener.class)
@Configuration
public class RedisConfig {

  private final InfoListener infoListener;

  /**
   * 创建消息监听容器
   *
   * @param connectionFactory Redis连接工厂
   * @return 消息监听容器
   */
  @Bean
  public RedisMessageListenerContainer getRedisMessageListenerContainer(
      RedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
    listenerContainer.setConnectionFactory(connectionFactory);

    // 可以添加多个监听订阅频道
    // 当前监听的是通道：topic-queue
    listenerContainer.addMessageListener(
        new MessageListenerAdapter(infoListener), new PatternTopic("topic-queue"));

    return listenerContainer;
  }
}
