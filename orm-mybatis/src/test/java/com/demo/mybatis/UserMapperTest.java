package com.demo.mybatis;

import com.demo.mybatis.entity.UserDO;
import com.demo.mybatis.mapper.UserMapper;
import java.util.List;
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
public class UserMapperTest extends OrmMybatisApplicationTests {
  @Autowired private UserMapper userMapper;

  /** 测试新增 */
  @Test
  void insertUserTest() {
    int result = userMapper.insertUser();
    log.info("插入结果:{}", result);
  }

  /** 测试修改 */
  @Test
  void updateUserTest() {
    userMapper.updateUser();
  }

  /** 测试删除 */
  @Test
  void deleteUserTest() {
    userMapper.deleteUser();
  }

  /** 测试查询单个 */
  @Test
  void getUserByIdTest() {
    UserDO user = userMapper.getUserById();
    log.info("user:{}", user);
  }

  /** 测试查询所有 */
  @Test
  void getAllUserTest() {
    List<UserDO> userList = userMapper.getAllUser();
    log.info("userList:{}", userList);
  }
}
