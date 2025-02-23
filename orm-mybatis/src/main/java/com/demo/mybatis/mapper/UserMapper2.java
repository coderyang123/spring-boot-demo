package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.UserDO;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 12:55:00
 */
@Mapper
public interface UserMapper2 {

  /**
   * 根据用户名查询用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  UserDO getUserByName(@Param("username") String username);

  /**
   * 根据用户名和年龄查询用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  UserDO getUserByNameAndAge(@Param("username") String username, @Param("age") int age);

  /**
   * 根据用户名和年龄查询用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  UserDO getUserByNameAndAge2(@Param("username") String username, @Param("age") int age);

  /**
   * 根据用户名和年龄查询用户信息
   *
   * @param paramMap 参数
   * @return 用户信息
   */
  UserDO getUserByMap(Map<String, Object> paramMap);

  /**
   * 根据用户名和年龄查询用户信息
   *
   * @param userDO 参数
   * @return 用户信息
   */
  UserDO getUserByObject(UserDO userDO);
}
