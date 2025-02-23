package com.demo.alllearning.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 实体类基类
 *
 * @author yueyang
 * @since 2021-03-07 22:23:00
 */
@Data
public class BaseEntity implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -8196526706294119547L;

  /** 主键ID */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /** 修改时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime modifyTime;

  /** 创建人 */
  @TableField(fill = FieldFill.INSERT)
  private String creator;

  /** 操作人 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String operator;

  /** 是否已删除 0：未删除 1：已删除 */
  @TableField(fill = FieldFill.INSERT)
  @TableLogic
  private Integer deleted;

  /** 版本号 */
  @Version
  @TableField(fill = FieldFill.INSERT)
  private Long version;
}
