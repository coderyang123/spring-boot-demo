package com.demo.valid.controller;

import com.demo.valid.domain.ResponseResult;
import com.demo.valid.domain.UserQueryDTO;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

  /**
   * 查询用户
   *
   * @param userQueryDTO 用户数据查询实体
   * @return 通用返回结果模型
   */
  @GetMapping("/query")
  public ResponseResult<Void> query(@Valid @RequestBody UserQueryDTO userQueryDTO) {
    log.info("查询用户参数：{}", userQueryDTO);
    return ResponseResult.success();
  }
}
