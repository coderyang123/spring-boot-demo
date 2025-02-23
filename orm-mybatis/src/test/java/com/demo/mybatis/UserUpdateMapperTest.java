package com.demo.mybatis;

import com.demo.mybatis.mapper.UserMapper6;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户更新测试用例
 *
 * @author yueyang
 * @since 2022-09-02 10:17:00
 */
@Slf4j
public class UserUpdateMapperTest extends OrmMybatisApplicationTests {
  @Autowired private UserMapper6 userMapper6;

  /** 测试更新 */
  @Test
  void testUpdate() {
    int result = userMapper6.update(1, "updated");
    log.info("更新结果:{}", result);
  }
}
