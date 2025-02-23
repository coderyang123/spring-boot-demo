package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.UserDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 12:55:00
 */
@Mapper
public interface UserMapper3 {

  /**
   * 根据ID查询用户信息
   *
   * @return 用户信息
   */
  UserDO getUserById();

  /**
   * 查询所有用户信息
   *
   * @return 所有用户信息
   */
  List<UserDO> getUserList();

  /**
   * 查询用户的总记录数
   *
   * <p>在MyBatis中，对于Java中常用的类型都设置了类型别名
   * 例如：java.lang.Integer-->int|integer；int-->_int|_integer；Map-->map,List-->list
   *
   * @return 总记录数
   */
  int getCount();

  /**
   * 根据用户ID查询用户信息为map集合
   *
   * @param id 用户ID
   * @return 用户信息
   */
  Map<String, Object> getUserToMap(@Param("id") int id);

  /**
   * 查询所有用户信息为map集合，方式一
   *
   * <p>将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，此时可以将这些map放在一个list集合中获取
   *
   * @return 所有用户信息
   */
  List<Map<String, Object>> getAllUserToMap();

  /**
   * 查询所有用户信息为map集合，方式二
   *
   * <p>将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，并
   * 且最终要以一个map的方式返回数据，此时需要通过@MapKey注解设置map集合的键，值是每条数据所对应的 map集合
   *
   * @return 所有用户信息
   */
  @MapKey("id")
  Map<String, Object> getAllUserToMap2();

  /**
   * 模糊查询所有用户信息（方式一）
   *
   * @param username 用户名
   * @return 所有用户信息
   */
  List<UserDO> getUserListByName(@Param("username") String username);

  /**
   * 模糊查询所有用户信息（方式二）
   *
   * @param username 用户名
   * @return 所有用户信息
   */
  List<UserDO> getUserListByName2(@Param("username") String username);

  /**
   * 根据ID查询用户信息（自定义映射关系）
   *
   * @return 用户信息
   */
  UserDO getUserById2();
}
