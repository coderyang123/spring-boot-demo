package com.demo.nosql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 操作Hash类型的数据
 *
 * @author yueyang
 * @since 2022-08-25 09:36:00
 */
@Slf4j
@SpringBootTest
public class HashRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 普通设置-获取 */
  @Test
  void testOpsForHash() {
    HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

    // 设置值
    ops.put("hash-key", "name", "tom");
    ops.put("hash-key", "age", 18);
    ops.put("hash-key", "gender", "男");

    // 确定哈希hashKey是否存在
    Boolean hasKey = ops.hasKey("hash-key", "name");
    Boolean hasKey2 = ops.hasKey("hash-key", "age");
    Boolean hasKey3 = ops.hasKey("hash-key", "gender");
    log.info("hasKey is :{}", hasKey);
    log.info("hasKey2 is :{}", hasKey2);
    log.info("hasKey3 is :{}", hasKey3);

    // 获取值
    String name = (String) ops.get("hash-key", "name");
    Integer age = (Integer) ops.get("hash-key", "age");
    String gender = (String) ops.get("hash-key", "gender");
    assertEquals("tom", name);
    assertEquals(18, age);
    assertEquals("男", gender);
  }

  /** 删除键 */
  @Test
  void testOpsForHash2() {
    HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

    // 设置值
    Long count = ops.delete("hash-key", "name");
    log.info("delete count is :{}", count);
  }

  /** 获取所有的键值对集合 */
  @Test
  void testOpsForHash3() {
    HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

    // 设置值
    Map<Object, Object> entries = ops.entries("hash-key");
    entries.forEach((key, value) -> log.info("key is : {}, value is : {}", key, value));
  }
}
