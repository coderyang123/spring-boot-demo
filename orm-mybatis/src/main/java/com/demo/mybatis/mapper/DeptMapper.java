package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.DeptDO;
import com.demo.mybatis.entity.DeptDO2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工管理
 *
 * @author yueyang
 * @since 2022-02-26 10:15:00
 */
@Mapper
public interface DeptMapper {

  /**
   * 根据部门ID查询部门以及所属的员工信息
   *
   * @param did 部门ID
   * @return 部门以及所属的员工信息
   */
  DeptDO getDeptEmpByDid(@Param("did") int did);

  /**
   * 根据部门ID查询部门以及所属的员工信息
   *
   * @param did 部门ID
   * @return 部门以及所属的员工信息
   */
  DeptDO2 getDeptEmpByDid2(@Param("did") int did);
}
