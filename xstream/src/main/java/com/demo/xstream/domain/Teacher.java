package com.demo.xstream.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师实体类
 *
 * @author yueyang
 * @since 2022-06-09 09:46:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("teacher")
public class Teacher {

  @XStreamAlias("name")
  private Name name;
}
