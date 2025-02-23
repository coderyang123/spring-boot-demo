package com.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-09-02 10:17:00
 */
@Mapper
public interface UserMapper6 {

  /**
   * 更新用户名
   *
   * @param id 用户ID
   * @param username 用户名
   * @return 更新数
   */
  int update(@Param("id") Integer id, @Param("username") String username);
}
