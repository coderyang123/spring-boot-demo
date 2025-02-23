package com.demo.multi.datasource.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.multi.datasource.MultiDatasourceMybatisApplicationTests;
import com.demo.multi.datasource.domain.User;
import com.demo.multi.datasource.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据服务层实现测试类
 *
 * @author yueyang
 * @since 2022-06-01 17:30:00
 */
@Slf4j
class UserServiceImplTest extends MultiDatasourceMybatisApplicationTests {

  @Autowired private UserService userService;

  /** 主从库添加 */
  @Test
  public void addUser() {
    User userMaster = User.builder().name("主库添加").age(20).build();
    userService.addUser(userMaster);

    User userSlave = User.builder().name("从库添加").age(20).build();
    userService.save(userSlave);
  }

  /** 从库查询 */
  @Test
  public void testListUser() {
    List<User> list = userService.list(new QueryWrapper<>());
    log.info("【list】= {}", JSONUtil.toJsonStr(list));
  }
}
