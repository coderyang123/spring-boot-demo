package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.EmpDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工管理
 *
 * @author yueyang
 * @since 2022-02-26 10:15:00
 */
@Mapper
public interface EmpMapper {

  /**
   * 查询员工及其所在部门信息
   *
   * @param eid 员工ID
   * @return 员工及其所在部门信息
   */
  EmpDO getEmpAndDeptInfo(@Param("eid") int eid);

  /**
   * 查询员工及其所在部门信息
   *
   * @param eid 员工ID
   * @return 员工及其所在部门信息
   */
  EmpDO getEmpAndDeptInfo2(@Param("eid") int eid);
}
