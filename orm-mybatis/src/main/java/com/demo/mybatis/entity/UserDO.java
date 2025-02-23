package com.demo.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2022-02-26 12:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
  private Integer id;

  private String username;

  private String password;

  private Integer age;

  private String sex;

  private String email;
}
