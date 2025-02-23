package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.domain.entity.UserDO;
import com.demo.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2023-01-13 22:59:00
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 单个查询
   *
   * @param id 用户ID
   * @return 用户数据
   */
  @GetMapping("/{id}")
  public UserDO selectById(@PathVariable("id") Long id) {
    return userService.getById(id);
  }

  /**
   * 新增用户
   *
   * @param userDO 用户数据
   * @return 新增结果
   */
  @PostMapping()
  public boolean save(@RequestBody UserDO userDO) {
    return userService.save(userDO);
  }
}
