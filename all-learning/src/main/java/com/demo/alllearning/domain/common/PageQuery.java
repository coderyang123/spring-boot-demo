package com.demo.alllearning.domain.common;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 通用分页查询实体
 *
 * @author yueyang
 * @since 2021-03-08 19:50:00
 */
@Data
public class PageQuery<T> implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = 4112819726190883922L;

  /** 当前页数 */
  @NotNull(message = "页数不能为空！")
  @Min(value = 1, message = "页数必须为正数！")
  private Integer pageNo = 1;

  /** 每页行数 */
  @NotNull(message = "每页行数不能为空！")
  @Max(value = 100, message = "每页行数必须小于100条！")
  private Integer pageSize = 20;

  /** 动态查询条件 */
  @Valid // 开启级联约束（query实体属性有约束注解的时候也生效）
  @NotNull(message = "动态查询条件不能为空！")
  private T query;
}
