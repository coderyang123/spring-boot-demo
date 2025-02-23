package com.demo.cache.controller;

import com.demo.cache.domain.DictDO;
import com.demo.cache.service.DictDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author yueyang
 * @since 2022-08-15 14:46:00
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dict")
@RestController
public class DictController {
  private final DictDoService dictDoService;

  @GetMapping()
  public DictDO getDictDo(@RequestParam("id") Long id) {
    DictDO dictDo = dictDoService.getDictDo(id);
    log.info("dictDo:{}", dictDo);
    return dictDo;
  }

  @GetMapping("/clearDictDoCache")
  public void clearDictDoCache() {
    dictDoService.clearDictDoCache();
  }
}
