package com.demo.druid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.druid.domain.entity.UserDO;
import com.demo.druid.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-05-28 10:53:00
 */
@RestController
@RequestMapping("user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 新增用户
   *
   * @param userDO 用户实体
   * @return 新增
   */
  @PostMapping("/")
  public String create(@RequestBody UserDO userDO) {
    boolean bool = userService.save(userDO);

    return "用户已保存:" + bool;
  }

  /**
   * 查询用户
   *
   * @param username 用户名
   * @return 查询
   */
  @GetMapping("{username}")
  public String getByName(@PathVariable String username) {
    QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    UserDO userDO = userService.getOne(wrapper);

    return "查询到用户：" + userDO.toString();
  }

  /**
   * 删除用户
   *
   * @param id 用户ID
   * @return 删除
   */
  @DeleteMapping("{id}")
  public String deleteById(@PathVariable Long id) {
    boolean bool = userService.removeById(id);

    return "删除了用户:" + bool;
  }

  /**
   * 查询用户数量
   *
   * @return 查询
   */
  @GetMapping("count")
  public String getAllUsers() {
    List<UserDO> userDOList = userService.list(new QueryWrapper<>());

    return "所有用户：" + userDOList;
  }

  /**
   * 更新用户
   *
   * @param userDO 用户实体
   * @return 更新
   */
  @PutMapping("update")
  public String update(@RequestBody UserDO userDO) {
    boolean bool = userService.updateById(userDO);

    return "更新用户成功：" + bool;
  }
}
