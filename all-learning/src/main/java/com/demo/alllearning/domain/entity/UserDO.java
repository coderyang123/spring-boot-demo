package com.demo.alllearning.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2021-03-07 22:02:00
 */

// 将父类的里的属性也用于比较该类的实例是否相等
@EqualsAndHashCode(callSuper = true)
// 将父类实现toString的输出包含到该类的输出
@ToString(callSuper = true)
@Data
@TableName("user")
public class UserDO extends BaseEntity {

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
