package com.demo.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工实体类
 *
 * @author yueyang
 * @since 2022-02-27 09:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDO {
  private Integer eid;

  private String empName;

  private Integer age;

  private String sex;

  private String email;

  private DeptDO deptDO;
}
