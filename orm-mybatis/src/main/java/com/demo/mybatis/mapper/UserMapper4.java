package com.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 22:55:00
 */
@Mapper
public interface UserMapper4 {

  /**
   * 批量删除
   *
   * @param ids ID串
   * @return 删除结果
   */
  int deleteSome(@Param("ids") String ids);
}
