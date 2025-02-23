package com.demo.druid.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2021-05-28 10:16:00
 */
@Data
@TableName("user")
public class UserDO implements Serializable {
  private static final long serialVersionUID = 5288503237050740936L;

  /** 用户ID */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  /** 用户名 */
  private String username;

  /** 用户密码 */
  private String password;

  /** 用户年龄 */
  private String age;
}
