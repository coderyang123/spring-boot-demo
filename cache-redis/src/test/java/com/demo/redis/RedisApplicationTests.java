package com.demo.redis;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisApplicationTests {

  @Autowired StringRedisTemplate stringRedisTemplate;

  @Test
  void contextLoads() {}

  /** 简单的存取值 */
  @Test
  public void test1() {
    // 获取redis操作类
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

    // 新增数据
    operations.set("testKey", "testValue");

    // 获取数据
    String testValue = operations.get("testKey");
    System.out.println(testValue);

    // 判断key是否存在
    Boolean isExist = stringRedisTemplate.hasKey("testKey");
    System.out.println(isExist);

    // 判断key对应值的类型
    DataType type = stringRedisTemplate.type("testKey");
    System.out.println(type);

    // 获取所有key
    Set<String> keys = stringRedisTemplate.keys("*");
    System.out.println(keys.toString());

    // 改key名
    stringRedisTemplate.rename("testKey", "testKey1");

    // 再获取数据
    System.out.println(operations.get("testKey1"));

    // 删除数据
    stringRedisTemplate.delete("testKey1");

    // 获取数据
    System.out.println(operations.get("testKey1"));
  }

  /** 自增值（例如用于访问计数等） */
  @Test
  public void test2() {
    // 获取redis操作类
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    // 新增数据
    operations.set("testKey", "0");
    // 键对应的值自加1
    operations.increment("testKey");
    // 获取数据
    String testKey = operations.get("testKey");
    System.out.println(testKey);
    // 键对应的值再自加1
    operations.increment("testKey");
    // 获取数据
    String testKey2 = operations.get("testKey");
    System.out.println(testKey2);
  }

  @Test
  public void test3() {
    // 获取redis操作类
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

    // 新增数据(键存在会覆盖value)
    operations.set("testKey", "1");

    // 不存在key才新增数据
    operations.setIfAbsent("testKey", "1");

    // 获取所有key
    Set<String> keys = stringRedisTemplate.keys("*");

    // 删除所有
    Long delete = stringRedisTemplate.delete(keys);
  }
}
