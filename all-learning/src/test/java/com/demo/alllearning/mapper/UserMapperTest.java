package com.demo.alllearning.mapper;

import com.demo.alllearning.domain.entity.UserDO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户测试类
 *
 * @author yueyang
 * @since 2021-03-07 23:13:00
 */
@SpringBootTest
@Slf4j
public class UserMapperTest {
  @Autowired UserMapper userMapper;

  @Test
  public void find() {
    Map<String, Object> map = new HashMap<>(16);
    map.put("username", "xiaoming");

    List<UserDO> list = userMapper.selectByMap(map);
    log.info("{}", list);
  }

  @Test
  public void find1() {
    UserDO userDO = userMapper.selectById(1L);
    log.info("{}", userDO);
  }
}
