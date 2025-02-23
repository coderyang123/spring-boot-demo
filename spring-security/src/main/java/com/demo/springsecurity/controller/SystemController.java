package com.demo.springsecurity.controller;

import com.demo.springsecurity.domain.common.ResponseResult;
import com.demo.springsecurity.domain.dto.UserDTO;
import com.demo.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录管理
 *
 * @author yueyang
 * @since 2021-04-23 12:10:00
 */
@Controller
@RequestMapping("system")
public class SystemController {
  private final UserService userService;

  public SystemController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 登录成功首页
   *
   * @return 首页
   */
  @GetMapping("index")
  public String index() {
    return "index";
  }

  /**
   * 用户管理页
   *
   * @return 用户管理页
   */
  @GetMapping("user")
  public String userList() {
    return "user";
  }

  /**
   * 角色管理
   *
   * @return 角色管理
   */
  @GetMapping("role")
  public String roleList() {
    return "role";
  }

  /**
   * 菜单管理
   *
   * @return 菜单管理
   */
  @GetMapping("menu")
  public String menuList() {
    return "menu";
  }

  /**
   * 订单管理
   *
   * @return 订单管理
   */
  @GetMapping("order")
  public String orderList() {
    return "order";
  }

  /**
   * 新增用户页面
   *
   * @return 新增用户页面
   */
  @GetMapping("add")
  public String add() {
    return "add";
  }

  /** 新增用户 */
  @PostMapping("addUser")
  @ResponseBody
  public ResponseResult<Void> userAdd(UserDTO userDTO) {
    userService.addUser(userDTO);
    return ResponseResult.success();
  }
}
