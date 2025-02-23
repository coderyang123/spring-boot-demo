package com.demo.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.demo.mybatis.entity.EmpDO;
import com.demo.mybatis.mapper.EmpMapper;
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
public class EmpMapperTest extends OrmMybatisApplicationTests {
  @Autowired private EmpMapper empMapper;

  /** 测试查询 */
  @Test
  void insertUserTest() {
    EmpDO info = empMapper.getEmpAndDeptInfo(1);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询 */
  @Test
  void insertUserTest2() {
    EmpDO info = empMapper.getEmpAndDeptInfo2(1);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }
}
