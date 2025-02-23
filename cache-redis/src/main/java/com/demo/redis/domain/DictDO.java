package com.demo.redis.domain;

import java.io.Serializable;

/**
 * 字典实体类
 *
 * @author yueyang
 * @since 2021-04-04 12:58:00
 */
public class DictDO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -5013225742350933442L;

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

  @Override
  public String toString() {
    return "DictDO{"
        + "id="
        + id
        + ", parentId="
        + parentId
        + ", name='"
        + name
        + '\''
        + ", value="
        + value
        + ", dictCode='"
        + dictCode
        + '\''
        + '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getDictCode() {
    return dictCode;
  }

  public void setDictCode(String dictCode) {
    this.dictCode = dictCode;
  }
}
