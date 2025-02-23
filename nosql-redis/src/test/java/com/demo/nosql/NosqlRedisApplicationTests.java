package com.demo.nosql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
class NosqlRedisApplicationTests {
  @Autowired private RedisTemplate<String, String> redisTemplate;

  @Test
  void contextLoads() {}
}
