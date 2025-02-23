package com.demo.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.demo.mybatis.entity.DeptDO;
import com.demo.mybatis.entity.DeptDO2;
import com.demo.mybatis.mapper.DeptMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 员工管理
 *
 * @author yueyang
 * @since 2022-02-27 10:17:00
 */
@Slf4j
public class DeptMapperTest extends OrmMybatisApplicationTests {
  @Autowired private DeptMapper deptMapper;

  /** 测试查询 */
  @Test
  void insertUserTest() {
    DeptDO info = deptMapper.getDeptEmpByDid(1);
    assertNotNull(info);
  }

  /** 测试查询 */
  @Test
  void insertUserTest2() {
    DeptDO2 info = deptMapper.getDeptEmpByDid2(1);
    assertNotNull(info);
  }
}
