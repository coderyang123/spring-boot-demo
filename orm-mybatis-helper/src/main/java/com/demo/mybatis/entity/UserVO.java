package com.demo.mybatis.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2022-02-27 22:55:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserVO {

  /** 主键 */
  private Long id;

  /** 用户名 */
  private String name;

  /** 加密后的密码 */
  private String password;

  /** 加密使用的盐 */
  private String salt;

  /** 邮箱 */
  private String email;

  /** 手机号码 */
  private String phoneNumber;

  /** 状态，-1：逻辑删除，0：禁用，1：启用 */
  private Integer status;

  /** 创建时间 */
  private Date createTime;

  /** 上次登录时间 */
  private Date lastLoginTime;

  /** 上次更新时间 */
  private Date lastUpdateTime;
}
