package com.demo.xstream.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.List;
import lombok.Data;

/**
 * 学生实体类
 *
 * @author yueyang
 * @since 2022-06-08 09:35:00
 */
@XStreamAlias("student")
@Data
public class Student {

  /** 学生姓名 XStreamAlias 注解表示该字段的别名 XStreamAsAttribute 注解表示该字段作为父标签的一个属性 */
  @XStreamAlias("name")
  @XStreamAsAttribute
  private String studentName;

  /** 学生类型 XStreamOmitField 注解表示忽略该字段 */
  @XStreamOmitField private Integer type;

  /** 笔记集合 XStreamImplicit 注解表示该字段是集合或数组 */
  @XStreamImplicit(itemFieldName = "note")
  private List<Note> notes;
}
