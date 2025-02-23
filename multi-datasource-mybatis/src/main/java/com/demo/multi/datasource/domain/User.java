package com.demo.multi.datasource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User实体类
 *
 * @author yueyang
 * @since 2022-06-01 17:01:00
 */
@Data
@TableName("multi_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

  /** 序列号 */
  private static final long serialVersionUID = -1923859222295750467L;

  /** 主键 */
  @TableId(type = IdType.ID_WORKER)
  private Long id;

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;
}
