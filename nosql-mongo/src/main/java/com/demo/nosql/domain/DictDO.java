package com.demo.nosql.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典实体类
 *
 * @author yueyang
 * @since 2022-08-15 16:58:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictDO implements Serializable {

  /** 序列化ID */
  @Serial private static final long serialVersionUID = -5013225742350933442L;

  /** ID */
  private Long id;

  /** 父ID */
  private Long parentId;

  /** 字典名 */
  private String name;

  /** 字典值 */
  private Integer value;

  /** 字典编码 */
  private String dictCode;
}
