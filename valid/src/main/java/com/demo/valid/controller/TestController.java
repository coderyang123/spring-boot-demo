package com.demo.valid.controller;

import com.demo.valid.domain.ResponseResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@Slf4j
@Validated // 开启基础类型的校验
@RequestMapping("/api/test")
@RestController
public class TestController {

  /**
   * 查询用户
   *
   * @param username 用户名
   * @return 通用返回结果模型
   */
  @GetMapping("/query")
  public ResponseResult<Void> query(
      @NotBlank(message = "用户名不能为空！") String username, @NotNull(message = "年龄不能为空！") Integer age) {
    log.info("查询用户参数：{}, {}", username, age);
    return ResponseResult.success();
  }
}
