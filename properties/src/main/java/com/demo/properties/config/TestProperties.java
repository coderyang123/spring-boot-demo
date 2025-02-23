package com.demo.properties.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author yueyang
 * @since 2021-04-05 00:03:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "test.user")
public class TestProperties implements InitializingBean {

  public static String NAME;
  public static Integer AGE;
  public static String GENDER;
  public static String OTHER_PROPERTY;

  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;

  /** 性别 */
  private String gender;

  /** 其他属性 */
  private String otherProperty;

  /** 当初始化Bean完成，私有成员变量被赋值后，给常量字段赋值 */
  @Override
  public void afterPropertiesSet() {
    NAME = name;
    AGE = age;
    GENDER = gender;
    OTHER_PROPERTY = otherProperty;
  }
}
