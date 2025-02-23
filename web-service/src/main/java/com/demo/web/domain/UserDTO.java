package com.demo.web.domain;

import lombok.Data;

/**
 * 用户传输类
 *
 * @author yueyang
 * @since 2022-06-06 15:52:00
 */
@Data
public class UserDTO {

  /** 用户ID */
  private Long id;

  /** 邮箱 */
  private String email;

  /** 用户名 */
  private String username;

  /** 密码 */
  private String password;
}
