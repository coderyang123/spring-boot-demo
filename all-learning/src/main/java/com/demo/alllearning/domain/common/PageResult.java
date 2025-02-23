package com.demo.alllearning.domain.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 通用分页查询返回实体
 *
 * @author yueyang
 * @since 2021-03-08 12:33:00
 */
@Data
public class PageResult<T> implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -5218199854814181136L;

  /** 当前页数 */
  private Integer pageNo;

  /** 每页行数 */
  private Integer pageSize;

  /** 总记录数 */
  private Long totalNum;

  /** 总页数 */
  private Integer totalPage;

  /** 数据实体 */
  private T data;
}
