package com.demo.nosql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 操作List类型的数据
 *
 * @author yueyang
 * @since 2022-08-24 16:36:00
 */
@Slf4j
@SpringBootTest
public class ListRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 普通设置-获取 */
  @Test
  void testOpsForList() {
    ListOperations<String, Object> ops = redisTemplate.opsForList();

    // 设置值
    ops.leftPush("list-key", 1);
    ops.leftPush("list-key", 2);
    ops.leftPush("list-key", 3);

    // 获取并删除值
    Integer value = (Integer) ops.leftPop("list-key");
    Integer value2 = (Integer) ops.leftPop("list-key");
    Integer value3 = (Integer) ops.leftPop("list-key");
    log.info("value is :{}", value);
    log.info("value2 is :{}", value2);
    log.info("value3 is :{}", value3);
  }

  /** 普通设置-获取 */
  @Test
  void testOpsForList2() {
    ListOperations<String, Object> ops = redisTemplate.opsForList();

    // 设置值
    ops.leftPush("list-key", 1);
    ops.leftPush("list-key", 2);
    ops.leftPush("list-key", 3);

    // 在指定元素后设置值
    ops.leftPush("list-key", 1, 4);
    ops.leftPush("list-key", 2, 5);
    ops.leftPush("list-key", 3, 6);

    // 获取并删除左边4个值
    List<Object> list = ops.leftPop("list-key", 4L);
    list.forEach(System.out::print);

    // 在5秒超时时间内获取并删除值
    Integer value = (Integer) ops.leftPop("list-key", Duration.ofSeconds(5));
    log.info("value is :{}", value);

    // 在5秒超时时间内获取并删除值
    Integer value2 = (Integer) ops.leftPop("list-key", 5L, TimeUnit.SECONDS);
    log.info("value2 is :{}", value2);
  }

  /** 裁剪值 */
  @Test
  void testOpsForList3() {
    ListOperations<String, Object> ops = redisTemplate.opsForList();

    // 设置值
    ops.leftPush("list-key", 1);
    ops.leftPush("list-key", 2);
    ops.leftPush("list-key", 3);

    // 获取所有值
    List<Object> list = ops.range("list-key", 0L, -1L);
    list.forEach(System.out::print);

    // 裁剪部分值，去掉第一个元素
    ops.trim("list-key", 1, -1);

    // 再次获取所有值
    List<Object> list2 = ops.range("list-key", 0L, -1L);
    list2.forEach(System.out::print);
  }

  /** 获取值长度-如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。 */
  @Test
  void testOpsForList4() {
    ListOperations<String, Object> ops = redisTemplate.opsForList();

    // 设置值
    ops.leftPush("list-key", 4);
    ops.leftPush("list-key", 5);
    ops.leftPush("list-key", 6);

    // 获取所有值
    Long size = ops.size("list-key");
    assertEquals(5, size);
  }
}
