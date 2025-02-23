package com.demo.alllearning.domain.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户视图实体类
 *
 * @author yueyang
 * @since 2021-03-07 22:38:00
 */
@Data
public class UserVO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = 8462789728416927373L;

  /** 用户名 */
  private String username;

  /** 密码 */
  private String password;

  /** 年龄 */
  private Integer age;

  /** 邮箱 */
  private String email;

  /** 手机号 */
  private String phone;
}
