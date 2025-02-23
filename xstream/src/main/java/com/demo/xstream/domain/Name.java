package com.demo.xstream.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师姓名实体类
 *
 * @author yueyang
 * @since 2022-06-09 09:46:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {

  /** 名 */
  private String firstName;

  /** 姓 */
  private String lastName;

  /** 昵称 */
  private String nickName;

  /** 编码 */
  private String code;
}
