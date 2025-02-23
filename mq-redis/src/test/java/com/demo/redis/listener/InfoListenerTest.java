package com.demo.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootTest
class InfoListenerTest {

  @Autowired private StringRedisTemplate stringRedisTemplate;

  @Test
  void testSendMessage() {
    for (int i = 0; i < 10; i++) {
      log.info("待发送消息：" + i);
      stringRedisTemplate.convertAndSend("topic-queue", "this is a message :" + i);
    }
  }
}
