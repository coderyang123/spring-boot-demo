package com.demo.fastjson.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级实体类
 *
 * @author yueyang
 * @since 2022-06-16 14:09:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class2DO {

  /** 用户实体类集合 */
  @JSONField(name = "users")
  private List<UserDO> userDOList;
}
