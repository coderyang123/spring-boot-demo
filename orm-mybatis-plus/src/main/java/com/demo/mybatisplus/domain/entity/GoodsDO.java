package com.demo.mybatisplus.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品实体类
 *
 * @author yueyang
 * @since 2022-07-22 17:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods")
public class GoodsDO {
  /** 主键ID */
  private Long id;

  /** 商品名 */
  private String name;

  /** 用户主键ID */
  private Long userId;

  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
