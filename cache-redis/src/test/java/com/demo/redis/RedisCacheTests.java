package com.demo.redis;

import com.demo.redis.domain.DictDO;
import com.demo.redis.service.DictDoService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Redis 缓存测试类
 *
 * @author yueyang
 * @since 2021-04-08 22:58:00
 */
@SpringBootTest
public class RedisCacheTests {

  @Autowired private DictDoService dictDoService;

  @Test
  public void listDictDo() {
    List<DictDO> dictDOS = dictDoService.listDictDo();
    System.out.println(dictDOS);
  }

  @Test
  public void updateDictDo() {
    dictDoService.updateDictDo();
  }
}
