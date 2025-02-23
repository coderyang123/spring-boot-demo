package com.demo.cache.controller;

import com.demo.cache.utils.LocalCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存管理
 *
 * @author yueyang
 * @since 2022-06-23 14:13:00
 */
@RestController
@Slf4j
public class Controller {

  @GetMapping("/setCache")
  public void setCache() {
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LocalCacheUtils.setCache("my_cache_key_" + i, i, 30 * 1000);
    }
  }

  @GetMapping("/getCache/{cacheKey}")
  public void getCache(@PathVariable String cacheKey) {
    Object cache = LocalCacheUtils.getCache(cacheKey);
    log.info("缓存为:{}", cache);
  }

  @PostMapping("/showUtilsInfo")
  public void showUtilsInfo() {
    LocalCacheUtils.showUtilsInfo();
  }

  @PostMapping("/clear")
  public void clear() {
    LocalCacheUtils.clear();
  }
}
