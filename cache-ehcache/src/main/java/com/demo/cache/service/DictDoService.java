package com.demo.cache.service;

import com.demo.cache.domain.DictDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2022-08-15 00:15:00
 */
@Slf4j
@Service
public class DictDoService {

  /**
   * 模拟从数据库查询操作
   *
   * @return 字典集合
   */
  @Cacheable(value = "cache-dict", key = "#id")
  public DictDO getDictDo(Long id) {
    DictDO dictDO = new DictDO();
    dictDO.setId(id);
    dictDO.setParentId(1L);
    dictDO.setName("测试字典");
    dictDO.setValue(1);
    dictDO.setDictCode("test");

    log.info("未使用缓存——————");
    return dictDO;
  }

  /** 模拟新增/删除/更新数据字典的操作，会将Redis中key为getDictDo的缓存清空 */
  @CacheEvict(value = "cache-dict", allEntries = true)
  public void clearDictDoCache() {
    log.info("清空Redis中key为getDictDo的缓存");
  }
}
