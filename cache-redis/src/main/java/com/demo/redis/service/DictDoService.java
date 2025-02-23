package com.demo.redis.service;

import com.demo.redis.domain.DictDO;
import java.util.Arrays;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2021-04-09 00:11:00
 */
@Service
public class DictDoService {

  /**
   * 模拟从数据库查询字典集合的操作，首次调用会将方法返回结果存储到Redis中，key为注解里指定的dictList
   *
   * @return 字典集合
   */
  @Cacheable(value = "dictList", keyGenerator = "keyGenerator")
  public List<DictDO> listDictDo() {
    DictDO dictDO = new DictDO();
    dictDO.setId(2L);
    dictDO.setParentId(1L);
    dictDO.setName("测试字典");
    dictDO.setValue(1);
    dictDO.setDictCode("test");

    DictDO dictDo2 = new DictDO();
    dictDo2.setId(3L);
    dictDo2.setParentId(2L);
    dictDo2.setName("测试字典2");
    dictDo2.setValue(2);
    dictDo2.setDictCode("test2");

    DictDO dictDo3 = new DictDO();
    dictDo3.setId(4L);
    dictDo3.setParentId(3L);
    dictDo3.setName("测试字典3");
    dictDo3.setValue(3);
    dictDo3.setDictCode("test3");

    System.out.println("未使用缓存——————");
    return Arrays.asList(dictDO, dictDo2, dictDo3);
  }

  /** 模拟新增/删除/更新数据字典的操作，会将Redis中key为dictList的缓存清空 */
  @CacheEvict(value = "dictList", allEntries = true)
  public void updateDictDo() {
    System.out.println("清空Redis中key为dictList的缓存");
  }
}
