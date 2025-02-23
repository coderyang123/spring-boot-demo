package com.demo.jpa.repository;

import com.demo.jpa.entity.DepartmentDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 部门管理类
 *
 * @author yueyang
 * @since 2022-02-28 14:44:00
 */
@Repository
public interface DepartmentDao extends JpaRepository<DepartmentDO, Long> {

  /**
   * 根据层级查询部门
   *
   * @param level 层级
   * @return 部门列表
   */
  List<DepartmentDO> findDepartmentsByLevels(Integer level);
}
