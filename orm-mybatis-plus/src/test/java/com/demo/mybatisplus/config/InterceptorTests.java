package com.demo.mybatisplus.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.demo.mybatisplus.common.config.RequestDataHelper;
import com.demo.mybatisplus.domain.entity.UserDO;
import com.demo.mybatisplus.mapper.UserMapper;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InterceptorTests {
  @Autowired private UserMapper userMapper;

  @Test
  void test() {
    // 模拟根据参数计算出要查询的表后缀
    RequestDataHelper.setRequestData(Map.of("tableSuffix", "_1"));

    UserDO user = userMapper.selectById(1);
    assertNotNull(user);
  }
}
