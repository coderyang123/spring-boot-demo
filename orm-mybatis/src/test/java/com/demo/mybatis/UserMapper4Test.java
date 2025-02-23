package com.demo.mybatis;

import com.demo.mybatis.mapper.UserMapper4;
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
public class UserMapper4Test extends OrmMybatisApplicationTests {
  @Autowired private UserMapper4 userMapper4;

  /** 测试删除 */
  @Test
  void deleteSome() {
    int result = userMapper4.deleteSome("1,2");
    log.info("删除结果:{}", result);
  }
}
