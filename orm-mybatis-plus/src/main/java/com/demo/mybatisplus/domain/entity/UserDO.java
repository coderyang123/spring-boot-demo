package com.demo.mybatisplus.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserDO {
  /** 主键ID */
  private Long id;

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;

  /** 邮箱 */
  private String email;

  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
