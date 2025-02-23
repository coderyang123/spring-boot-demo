package com.demo.springsecurity.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户传输实体类
 *
 * @author yueyang
 * @since 2021-03-07 22:34:00
 */
@Data
public class UserDTO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -8768274390538463497L;

  /** 用户名 */
  private String username;

  /** 密码 */
  private String password;

  /** 用户角色（多角色用逗号间隔） */
  private String roles;
}
