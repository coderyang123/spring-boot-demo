package com.demo.jwt.controller;

import com.demo.jwt.domain.User;
import com.demo.jwt.util.JwtUtils;
import java.util.Objects;
import org.springframework.web.bind.annotation.*;

/**
 * 测试
 *
 * @author yueyang
 * @since 2021-08-03 21:39:00
 */
@RestController
@RequestMapping("jwt")
public class TestController {

  /**
   * 获取用户 token
   *
   * @param user 用户实体
   * @return token
   */
  @PostMapping("getToken")
  public String getToken(@RequestBody User user) {
    // 通过传入的的username获取password与传入的password做比较，这里模拟一个
    String realPassword = "123456";

    if (Objects.equals(realPassword, user.getPassword())) {
      return JwtUtils.getToken(user);
    }

    return "密码错误，token 获取失败！";
  }

  @GetMapping("needToken")
  public String needToken() {
    return "鉴权成功！";
  }

  @GetMapping("needlessToken")
  public String needlessToken() {
    return "不需token校验";
  }
}
