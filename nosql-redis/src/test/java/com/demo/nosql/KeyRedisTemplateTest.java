package com.demo.nosql;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 操作Key
 *
 * @author yueyang
 * @since 2022-08-25 14:30:00
 */
@Slf4j
@SpringBootTest
public class KeyRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 设置key的失效时间，timeout是时间参数，timeunit是时间单位 */
  @Test
  void testExpire() {
    // 设置10s过期
    Boolean bool = redisTemplate.expire("test-key", 10, TimeUnit.SECONDS);
    log.info("set expire time : {}", bool);

    // 设置10s过期
    Boolean bool2 = redisTemplate.expire("test-key2", Duration.ofSeconds(10));
    log.info("set expire time : {}", bool2);
  }

  /** 设置key在一个时间点失效 */
  @Test
  void testExpireAt() {
    // 设置过期时间点
    Boolean bool = redisTemplate.expireAt("test-key3", new Date());
    log.info("set expire time : {}", bool);

    // 获取存活时间
    Long expire = redisTemplate.getExpire("test-key3");
    log.info("expire time is : {}", expire);

    // 获取存活时间，规定单位为秒
    Long expire2 = redisTemplate.getExpire("test-key3", TimeUnit.SECONDS);
    log.info("expire2 time is : {}s", expire2);
  }
}
