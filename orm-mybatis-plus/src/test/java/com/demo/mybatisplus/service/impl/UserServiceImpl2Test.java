package com.demo.mybatisplus.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.domain.bo.UserInfoBO;
import com.demo.mybatisplus.domain.dto.QueryDTO;
import com.demo.mybatisplus.domain.entity.UserDO;
import com.demo.mybatisplus.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** 自定义查询 */
@SpringBootTest
class UserServiceImpl2Test {
  @Autowired private UserService userService;

  @Test
  void testListUser() {
    Page<QueryDTO> page = new Page<>(1, 1);
    QueryDTO queryDTO = new QueryDTO("雨化田", 18);

    Page<UserDO> resultPage = userService.listUser(page, queryDTO);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(2, total);

    // 对象集合
    List<UserDO> userList = resultPage.getRecords();
    assertNotNull(userList);
  }

  @Test
  void testListUserAndGoods() {
    Page<QueryDTO> page = new Page<>(1, 1);
    QueryDTO queryDTO = new QueryDTO("雨化田", 18);

    Page<UserInfoBO> resultPage = userService.listUserAndGoods(page, queryDTO);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(2, total);

    // 对象集合
    List<UserInfoBO> userList = resultPage.getRecords();
    assertNotNull(userList);
  }
}
