package com.demo.mybatis.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门实体类
 *
 * @author yueyang
 * @since 2022-02-27 09:55:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDO {
  private Integer did;

  private String deptName;

  private List<EmpDO> emps;
}
