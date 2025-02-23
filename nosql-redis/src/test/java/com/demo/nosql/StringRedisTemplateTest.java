package com.demo.nosql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 操作String类型的数据
 *
 * @author yueyang
 * @since 2022-08-24 16:36:00
 */
@Slf4j
@SpringBootTest
public class StringRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 普通设置-获取 */
  @Test
  void testOpsForValue() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key", "test-value");

    // 获取值
    String value = (String) ops.get("test-key");
    log.info("value is :{}", value);
  }

  /** 带过期时间的设置-获取 */
  @Test
  void testOpsForValue2() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key2", "test-value2", 20, TimeUnit.SECONDS);

    // 获取值
    String value = (String) ops.get("test-key2");
    log.info("value is :{}", value);
  }

  /** 指定偏移量的设置-获取 */
  @Test
  void testOpsForValue3() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key3", "test-value3");

    // 从指定位置开始覆盖值
    ops.set("test-key3", "replace-value3", 5);

    // 获取值
    String value = (String) ops.get("test-replace-value3");
    log.info("value is :{}", value);
  }

  /** 条件设置-获取 */
  @Test
  void testOpsForValue4() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 初次可以设置值
    Boolean isSet = ops.setIfAbsent("test-key4", "test-value4");
    assertEquals(Boolean.TRUE, isSet);

    // 已存在就不会设置
    Boolean isSet2 = ops.setIfAbsent("test-key4", "replace-value4");
    assertEquals(Boolean.FALSE, isSet2);

    // 获取值
    String value = (String) ops.get("test-key4");
    log.info("value is :{}", value);
  }

  /** 多个设置-获取 */
  @Test
  void testOpsForValue5() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置多个值
    Map<String, String> map = Map.of("key1", "value1", "key2", "value2");
    ops.multiSet(map);

    // 获取多个值
    Set<String> set = Set.of("key1", "key2");
    List<Object> valueList = ops.multiGet(set);
    log.info("value is :{}", valueList);
  }

  /** 获取key对应的值并重新赋值 */
  @Test
  void testOpsForValue6() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key6", "test-value6");

    // 获取值并重新赋值
    String value = (String) ops.getAndSet("test-key6", "test-key66");
    log.info("value is :{}", value);

    // 获取值
    String value2 = (String) ops.get("test-key6");
    log.info("value2 is :{}", value2);
  }

  /** 获取key对应的值并删除-该命令在Redis-6.2.0版本后可用，这里先注释掉 */
  @Test
  void testOpsForValue7() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key7", "test-value7");

    // 获取值并删除值
    //    String value = ops.getAndDelete("test-key7");
    //    log.info("value is :{}", value);

    // 获取值
    String value2 = (String) ops.get("test-key7");
    log.info("value2 is :{}", value2);
  }

  /** 获取key对应的值并增加 */
  @Test
  void testOpsForValue8() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key8", 1L);

    // 获取值并增加1
    Long value = ops.increment("test-key8");
    log.info("value is :{}", value);

    // 获取值并增加2
    Long value2 = ops.increment("test-key8", 2);
    log.info("value2 is :{}", value2);

    // 获取值
    Integer value3 = (Integer) ops.get("test-key8");
    log.info("value3 is :{}", value3);
  }

  /** 追加值 */
  @Test
  void testOpsForValue9() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key9", "test-value9");

    // 获取值并增加1
    Integer value = ops.append("test-key9", "-2");
    log.info("value is :{}", value);

    // 获取值
    String value2 = (String) ops.get("test-key9");
    log.info("value2 is :{}", value2);
  }

  /** 获取值长度 */
  @Test
  void testOpsForValue10() {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    // 设置值
    ops.set("test-key10", "test-value10");

    // 获取值的长度
    Long valueSize = ops.size("test-key10");
    log.info("valueSize is :{}", valueSize);
  }
}
