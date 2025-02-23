package com.demo.fastjson.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2021-03-29 11:09:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

  /** 用户名 */
  private String username;

  /** 密码 */
  private String password;

  /** 年龄 */
  private Integer age;

  /** 生日 */
  private LocalDate birthday;

  /** 手机号 */
  private String phone;
}
