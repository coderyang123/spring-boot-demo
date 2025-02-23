package com.demo.nosql;

import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * 操作Set类型的数据
 *
 * @author yueyang
 * @since 2022-08-25 11:30:00
 */
@Slf4j
@SpringBootTest
public class ZSetRedisTemplateTest {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  /** 普通设置-获取 */
  @Test
  void testOpsForHash() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    // 设置单个值
    ops.add("zset-key", "zset-value", 1.0D);

    // 设置多个值
    ZSetOperations.TypedTuple<Object> objectTypedTuple1 =
        new DefaultTypedTuple<>("zset-value2", 2.0D);
    ZSetOperations.TypedTuple<Object> objectTypedTuple2 =
        new DefaultTypedTuple<>("zset-value3", 3.0D);
    Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
    tuples.add(objectTypedTuple1);
    tuples.add(objectTypedTuple2);
    ops.add("zset-key", tuples);

    // 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
    Long rank = ops.rank("zset-key", "zset-value");
    log.info("the rank is : {}", rank);

    // 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从大到小)顺序排列
    Long reverseRank = ops.reverseRank("zset-key", "zset-value");
    log.info("the reverseRank is : {}", reverseRank);

    // 获取所有元素
    Set<Object> set = ops.range("zset-key", 0, -1);
    set.forEach(System.out::print);
  }

  /** 删除值 */
  @Test
  void testOpsForSet2() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    // 移除指定值
    Long count = ops.remove("set-key", "zset-value", "zset-value2");
    log.info("remove count is : {}", count);
  }

  /** 删除键 */
  @Test
  void testOpsForSet3() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    // 移除指定键
    Boolean delete = ops.getOperations().delete("set-key");
    log.info("is deleted: {}", delete);
  }

  /** 获取值大小 */
  @Test
  void testOpsForSet4() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    // 设置值
    Long size = ops.size("set-key");
    log.info("value size is : {}", size);
  }

  /** 增加值权重 */
  @Test
  void testOpsForSet5() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    // 设置值
    Double score = ops.incrementScore("set-key", "zset-value3", 4.0D);
    log.info("value score now is : {}", score);
  }

  /** 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列 */
  @Test
  void testOpsForSet6() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    Set<Object> set = ops.rangeByScore("set-key", 1.0D, 4.0D);
    set.forEach(System.out::print);
  }

  /** 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列 */
  @Test
  void testOpsForSet7() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    Set<Object> set = ops.reverseRange("set-key", 0, -1);
    set.forEach(System.out::print);
  }

  /** 通过分数返回有序集合指定区间内的成员个数 */
  @Test
  void testOpsForSet8() {
    ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();

    Long count = ops.count("set-key", 1.0D, 4.0D);
    log.info("value count is : {}", count);
  }
}
