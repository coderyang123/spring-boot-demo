package com.demo.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author yueyang
 * @since 2023-04-05 15:56:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  private Integer id;
  private String name;
  private Integer age;
}
