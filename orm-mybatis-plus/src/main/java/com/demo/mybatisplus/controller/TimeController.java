package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.domain.entity.TimeDO;
import com.demo.mybatisplus.service.TimeService;
import org.springframework.web.bind.annotation.*;

/**
 * 时间管理
 *
 * @author yueyang
 * @since 2023-01-14 00:05:00
 */
@RestController
@RequestMapping("/time")
public class TimeController {
  private final TimeService timeService;

  public TimeController(TimeService timeService) {
    this.timeService = timeService;
  }

  /**
   * 单个查询
   *
   * @param id 时间ID
   * @return 时间数据
   */
  @GetMapping("/{id}")
  public TimeDO selectById(@PathVariable("id") Long id) {
    return timeService.getById(id);
  }

  /**
   * 新增时间
   *
   * @param timeDO 时间数据
   * @return 新增结果
   */
  @PostMapping()
  public boolean save(@RequestBody TimeDO timeDO) {
    return timeService.save(timeDO);
  }
}
