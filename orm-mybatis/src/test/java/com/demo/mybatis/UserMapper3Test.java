package com.demo.mybatis;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.demo.mybatis.entity.UserDO;
import com.demo.mybatis.mapper.UserMapper3;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 17:17:00
 */
@Slf4j
public class UserMapper3Test extends OrmMybatisApplicationTests {
  @Autowired private UserMapper3 userMapper3;

  /** 测试查询单个 */
  @Test
  void getUserByIdTest() {
    UserDO user = userMapper3.getUserById();
    log.info("user:");
    System.out.println(JSON.toJSONString(user, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询所有 */
  @Test
  void getUserListTest() {
    List<UserDO> userList = userMapper3.getUserList();
    log.info("userList:");
    System.out.println(JSON.toJSONString(userList, JSONWriter.Feature.PrettyFormat));
  }

  /** 查询用户的总记录数 */
  @Test
  void getCountTest() {
    int count = userMapper3.getCount();
    log.info("count:");
    System.out.println(JSON.toJSONString(count, JSONWriter.Feature.PrettyFormat));
  }

  /** 根据用户ID查询用户信息为map集合 */
  @Test
  void getUserToMapTest() {
    Map<String, Object> map = userMapper3.getUserToMap(1);
    log.info("map:");
    System.out.println(JSON.toJSONString(map, JSONWriter.Feature.PrettyFormat));
  }

  /** 查询所有用户信息为map集合，方式一 */
  @Test
  void getAllUserToMapTest() {
    List<Map<String, Object>> mapList = userMapper3.getAllUserToMap();
    log.info("mapList:");
    System.out.println(JSON.toJSONString(mapList, JSONWriter.Feature.PrettyFormat));
  }

  /** 查询所有用户信息为map集合，方式二 */
  @Test
  void getAllUserToMap2Test() {
    Map<String, Object> map = userMapper3.getAllUserToMap2();
    log.info("map:");
    System.out.println(JSON.toJSONString(map, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试模糊查询所有（方式一） */
  @Test
  void getUserListByNameTest() {
    List<UserDO> userList = userMapper3.getUserListByName("dmi");
    log.info("userList:");
    System.out.println(JSON.toJSONString(userList, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试模糊查询所有（方式二） */
  @Test
  void getUserListByName2Test() {
    List<UserDO> userList = userMapper3.getUserListByName2("dmi");
    log.info("userList:");
    System.out.println(JSON.toJSONString(userList, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询单个（自定义映射关系） */
  @Test
  void getUserByIdTest2() {
    UserDO user = userMapper3.getUserById2();
    log.info("user:");
    System.out.println(JSON.toJSONString(user, JSONWriter.Feature.PrettyFormat));
  }
}
