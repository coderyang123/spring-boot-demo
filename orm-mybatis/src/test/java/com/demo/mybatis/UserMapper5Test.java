package com.demo.mybatis;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.demo.mybatis.entity.UserDO;
import com.demo.mybatis.mapper.UserMapper5;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 22:17:00
 */
@Slf4j
public class UserMapper5Test extends OrmMybatisApplicationTests {
  @Autowired private UserMapper5 userMapper5;

  /** 测试新增 */
  @Test
  void insertUserTest() {
    UserDO userDO = new UserDO(null, "bar", "123", 18, "男", "23123");
    int result = userMapper5.insertUser(userDO);
    log.info("新增结果:{}", result);

    // ID 为自增生成的ID
    System.out.println(JSON.toJSONString(userDO, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试批量新增 */
  @Test
  void batchInsertUserTest() {
    UserDO userDO = new UserDO(null, "bar", "123", 18, "男", "23123");
    UserDO userDO2 = new UserDO(null, "bar2", "123", 19, "男", "23123");
    List<UserDO> userDOList = Arrays.asList(userDO, userDO2);

    int result = userMapper5.batchInsertUserList(userDOList);
    log.info("新增结果:{}", result);

    // ID 为自增生成的ID
    userDOList.forEach(
        user -> System.out.println(JSON.toJSONString(user, JSONWriter.Feature.PrettyFormat)));
  }
}
