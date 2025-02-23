package com.demo.fastjson.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2021-03-29 11:09:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User2DO {

  /** 用户名 JSONField注解 ordinal：序列化/反序列化的顺序 */
  @JSONField(name = "user_name", ordinal = 5)
  private String username;

  /** 密码 JSONField注解 name：指定序列化后的字段名，serialize：是否序列化，默认true */
  @JSONField(name = "pass_word", serialize = false, ordinal = 4)
  private String password;

  /** 年龄 */
  @JSONField(name = "age", ordinal = 3)
  private Integer age;

  /** 生日 JSONField注解 format：时间格式化 */
  @JSONField(name = "birth_day", format = "yyyy/MM/dd", ordinal = 2)
  private LocalDate birthday;

  /** 手机号 */
  @JSONField(name = "phone", ordinal = 1)
  private String phone;
}
