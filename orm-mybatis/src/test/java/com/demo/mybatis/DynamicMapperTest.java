package com.demo.mybatis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.demo.mybatis.entity.EmpDO;
import com.demo.mybatis.mapper.DynamicMapper;
import java.util.List;
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
public class DynamicMapperTest extends OrmMybatisApplicationTests {
  @Autowired private DynamicMapper dynamicMapper;

  /** 测试查询 */
  @Test
  void getEmpListByMoreTest() {
    EmpDO empDO = new EmpDO();
    empDO.setEmpName("test2");
    empDO.setAge(20);

    List<EmpDO> info = dynamicMapper.getEmpListByMore(empDO);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询 */
  @Test
  void getEmpListByMore2Test() {
    EmpDO empDO = new EmpDO();
    empDO.setEmpName("test2");
    empDO.setAge(20);

    List<EmpDO> info = dynamicMapper.getEmpListByMore2(empDO);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询 */
  @Test
  void getEmpListByMore3Test() {
    EmpDO empDO = new EmpDO();
    empDO.setEmpName("test2");
    empDO.setAge(20);

    List<EmpDO> info = dynamicMapper.getEmpListByMore3(empDO);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试查询 */
  @Test
  void getEmpListByChooseTest() {
    EmpDO empDO = new EmpDO();
    empDO.setEmpName("foo");
    empDO.setAge(23);
    empDO.setSex("男");

    List<EmpDO> info = dynamicMapper.getEmpListByChoose(empDO);
    assertNotNull(info);
    log.info("查询结果:");
    System.out.println(JSON.toJSONString(info, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试新增（校验每个参数） */
  @Test
  void insertEmpTest() {
    EmpDO emp = new EmpDO();
    emp.setEmpName("test");
    emp.setAge(10);
    emp.setSex("男");
    emp.setEmail("12");

    int result = dynamicMapper.insertEmp(emp);
    assertEquals(1, result);
    log.info("插入结果:");
    System.out.println(JSON.toJSONString(result, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试批量新增 */
  @Test
  void insertMoreEmpTest() {
    EmpDO emp = new EmpDO();
    emp.setEmpName("test");
    emp.setAge(10);
    emp.setSex("男");
    emp.setEmail("12");

    EmpDO emp2 = new EmpDO();
    emp2.setEmpName("test2");
    emp2.setAge(20);
    emp2.setSex("女");
    emp2.setEmail("34");

    List<EmpDO> list = List.of(emp, emp2);

    int result = dynamicMapper.insertMoreEmp(list);
    assertEquals(2, result);
    log.info("插入结果:");
    System.out.println(JSON.toJSONString(result, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试删除 */
  @Test
  void deleteMoreByArrayTest() {
    int[] eids = {1, 2};

    int result = dynamicMapper.deleteMoreByArray(eids);
    assertEquals(2, result);
    log.info("删除结果:");
    System.out.println(JSON.toJSONString(result, JSONWriter.Feature.PrettyFormat));
  }

  /** 测试删除 */
  @Test
  void deleteMoreByArray2Test() {
    int[] eids = {1, 2};
    int result = dynamicMapper.deleteMoreByArray2(eids);

    assertEquals(2, result);
    log.info("删除结果:");
    System.out.println(JSON.toJSONString(result, JSONWriter.Feature.PrettyFormat));
  }
}
