package com.demo.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.demo.mybatis.entity.UserDO;
import com.demo.mybatis.mapper.UserMapper2;
import java.util.HashMap;
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
public class UserMapper2Test extends OrmMybatisApplicationTests {
  @Autowired private UserMapper2 userMapper2;

  /** 测试查询单个参数 */
  @Test
  void getUserByNameTest() {
    UserDO user = userMapper2.getUserByName("foo");
    assertNotNull(user);
    log.info("user:{}", user);
  }

  /** 测试查询多个参数 */
  @Test
  void getUserByNameAndAgeTest() {
    UserDO user = userMapper2.getUserByNameAndAge("foo", 23);
    assertNotNull(user);
    log.info("user:{}", user);
  }

  /** 测试查询多个参数 */
  @Test
  void getUserByNameAndAge2Test() {
    UserDO user = userMapper2.getUserByNameAndAge("foo", 23);
    assertNotNull(user);
    log.info("user:{}", user);
  }

  /** 测试查询多个参数 */
  @Test
  void getUserByMapTest() {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("username", "foo");
    paramMap.put("age", 23);

    UserDO user = userMapper2.getUserByMap(paramMap);
    assertNotNull(user);
    log.info("user:{}", user);
  }

  /** 测试查询多个参数 */
  @Test
  void getUserByObjectTest() {
    UserDO userDO = new UserDO();
    userDO.setAge(23);
    userDO.setUsername("foo");

    UserDO user = userMapper2.getUserByObject(userDO);
    assertNotNull(user);
    log.info("user:{}", user);
  }
}
