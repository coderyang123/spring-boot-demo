package com.demo.redis;

import com.demo.redis.domain.DictDO;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis 存取对象测试类
 *
 * @author yueyang
 * @since 2021-04-08 22:58:00
 */
@SpringBootTest
public class RedisTemplateTests {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  @Test
  public void saveDict() {
    DictDO dictDO = new DictDO();
    dictDO.setId(2L);
    dictDO.setParentId(1L);
    dictDO.setName("测试字典");
    dictDO.setValue(1);
    dictDO.setDictCode("test");

    redisTemplate.opsForValue().set("dict2", dictDO, 5, TimeUnit.MINUTES);
  }

  @Test
  public void getDict() {
    DictDO dict = (DictDO) redisTemplate.opsForValue().get("dict");
    System.out.println(dict);
  }
}
