package com.demo.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2021-04-26 22:02:00
 */
@ToString(callSuper = true)
@Data
@TableName("user")
public class UserDO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -8196526706294119547L;

  /** 主键ID */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  /** 用户名 */
  private String username;

  /** 密码 */
  private String password;

  /** 用户角色（格式：ROLE_XXX，多角色用逗号间隔） */
  private String roles;

  /** 用户权限（多权限用逗号间隔） */
  private String authorities;
}
