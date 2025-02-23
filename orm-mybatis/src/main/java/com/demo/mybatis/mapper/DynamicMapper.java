package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.EmpDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 动态SQL示例
 *
 * @author yueyang
 * @since 2022-02-26 10:15:00
 */
@Mapper
public interface DynamicMapper {

  /**
   * 查询员工信息
   *
   * @param empDO 查询条件
   * @return 员工及其所在部门信息
   */
  List<EmpDO> getEmpListByMore(EmpDO empDO);

  /**
   * 查询员工信息
   *
   * @param empDO 查询条件
   * @return 员工及其所在部门信息
   */
  List<EmpDO> getEmpListByMore2(EmpDO empDO);

  /**
   * 查询员工信息
   *
   * @param empDO 查询条件
   * @return 员工及其所在部门信息
   */
  List<EmpDO> getEmpListByMore3(EmpDO empDO);

  /**
   * 查询员工信息
   *
   * @param empDO 查询条件
   * @return 员工及其所在部门信息
   */
  List<EmpDO> getEmpListByChoose(EmpDO empDO);

  /**
   * 新增
   *
   * @param empDO 员工
   * @return 新增结果
   */
  int insertEmp(EmpDO empDO);

  /**
   * 批量新增
   *
   * @param empDOList 员工集合
   * @return 新增结果
   */
  int insertMoreEmp(List<EmpDO> empDOList);

  /**
   * 批量删除
   *
   * @param eids 员工ID数组
   * @return 删除结果
   */
  int deleteMoreByArray(int[] eids);

  /**
   * 批量删除
   *
   * @param eids 员工ID数组
   * @return 删除结果
   */
  int deleteMoreByArray2(int[] eids);
}
