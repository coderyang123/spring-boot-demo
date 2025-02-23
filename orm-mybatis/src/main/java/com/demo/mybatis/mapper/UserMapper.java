package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 12:55:00
 */
@Mapper
public interface UserMapper {

  /**
   * MyBatis面向接口编程的两个一致： 1、映射文件的namespace要和mapper接口的全类名保持一致 2、映射文件中SQL语句的id要和mapper接口中的方法名一致
   *
   * <p>表--实体类--mapper接口--映射文件
   */

  /**
   * 添加用户信息
   *
   * @return 是否添加成功
   */
  int insertUser();

  /** 修改用户信息 */
  void updateUser();

  /** 删除用户信息 */
  void deleteUser();

  /**
   * 根据ID查询用户信息
   *
   * @return 用户信息
   */
  UserDO getUserById();

  /**
   * 查询所有的用户信息
   *
   * @return 所有的用户信息
   */
  List<UserDO> getAllUser();
}
