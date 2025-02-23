package com.demo.nosql;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

/**
 * 操作Set类型的数据
 *
 * @author yueyang
 * @since 2022-08-25 11:30:00
 */
@Slf4j
@SpringBootTest
public class SetRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 普通设置-获取 */
  @Test
  void testOpsForHash() {
    SetOperations<String, Object> ops = redisTemplate.opsForSet();

    // 设置值
    ops.add("set-key", 1, 2, 3, 4, 5);

    // 判断是不是属于该键
    Boolean isMember = ops.isMember("set-key", 1);
    assertEquals(Boolean.TRUE, isMember);

    // 随机获取一个值
    Integer value = (Integer) ops.randomMember("set-key");
    log.info("value is : {}", value);

    // 获取所有值
    Set<Object> members = ops.members("set-key");
    members.forEach(System.out::print);
  }

  /** 删除值 */
  @Test
  void testOpsForSet2() {
    SetOperations<String, Object> ops = redisTemplate.opsForSet();

    // 移除指定值
    Long count = ops.remove("set-key", 1, 2);
    log.info("remove count is : {}", count);

    // 移除1个随机值
    Integer value = (Integer) ops.pop("set-key");
    log.info("value is : {}", value);

    // 移除2个随机值
    List<Object> list = ops.pop("set-key", 2);
    log.info("value list is : {}", list);
  }

  /** 删除键 */
  @Test
  void testOpsForSet3() {
    SetOperations<String, Object> ops = redisTemplate.opsForSet();

    // 移除指定值
    Boolean delete = ops.getOperations().delete("set-key");
    log.info("is deleted: {}", delete);
  }

  /** 获取值大小 */
  @Test
  void testOpsForSet4() {
    SetOperations<String, Object> ops = redisTemplate.opsForSet();

    // 设置值
    Long size = ops.size("hash-key");
    log.info("value size is : {}", size);
  }
}
