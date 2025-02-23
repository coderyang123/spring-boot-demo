package com.demo.fastjson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人类实体类
 *
 * @author yueyang
 * @since 2022-06-16 14:09:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumanDO {

  /** ID */
  private Integer id;

  /** 用户实体类 */
  private UserDO userDO;
}
