package com.demo.mybatisplus.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询传输实体类
 *
 * @author yueyang
 * @since 2022-07-22 14:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryDTO {
  /** 姓名 */
  private String name;

  /** 年龄 */
  private Integer age;
}
