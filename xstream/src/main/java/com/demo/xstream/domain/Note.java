package com.demo.xstream.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 笔记实体类
 *
 * @author yueyang
 * @since 2022-06-08 09:39:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

  /** 标题 */
  @XStreamAlias("title")
  private String title;

  /** 描述 */
  @XStreamAlias("description")
  private String description;
}
