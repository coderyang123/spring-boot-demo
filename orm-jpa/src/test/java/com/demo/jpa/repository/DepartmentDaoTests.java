package com.demo.jpa.repository;

import com.alibaba.fastjson.JSON;
import com.demo.jpa.OrmJpaApplicationTests;
import com.demo.jpa.entity.DepartmentDO;
import com.demo.jpa.entity.UserDO;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试类
 *
 * @author yueyang
 * @since 2022-02-28 14:44:00
 */
@Slf4j
public class DepartmentDaoTests extends OrmJpaApplicationTests {
  @Autowired private DepartmentDao departmentDao;
  @Autowired private UserDao userDao;

  /** 测试保存，根节点 */
  @Test
  public void saveTest() {
    // 新增用户数据
    UserDO userDO =
        new UserDO(
            "user_1", "111111", "abc", "testSave1@code.com", "18208009981", 1, new Date(), null);
    UserDO userDO2 =
        new UserDO(
            "user_2", "111111", "abc", "testSave2@code.com", "18208009982", 1, new Date(), null);
    UserDO userDO3 =
        new UserDO(
            "user_3", "111111", "abc", "testSave3@code.com", "18208009983", 1, new Date(), null);
    List<UserDO> userDOList = List.of(userDO, userDO2, userDO3);
    userDao.saveAll(userDOList);

    // 新增部门数据
    DepartmentDO testSave1 =
        DepartmentDO.builder().name("testSave1").orderNo(0).levels(0).superior(null).build();
    DepartmentDO testSave1_1 =
        DepartmentDO.builder().name("testSave1_1").orderNo(0).levels(1).superior(testSave1).build();
    DepartmentDO testSave1_2 =
        DepartmentDO.builder().name("testSave1_2").orderNo(0).levels(1).superior(testSave1).build();
    DepartmentDO testSave1_1_1 =
        DepartmentDO.builder()
            .name("testSave1_1_1")
            .orderNo(0)
            .levels(2)
            .superior(testSave1_1)
            .build();
    List<DepartmentDO> departmentDOList =
        List.of(testSave1, testSave1_1, testSave1_2, testSave1_1_1);
    departmentDao.saveAll(departmentDOList);

    // 查询部门信息
    Collection<DepartmentDO> deptAll = departmentDao.findAll();
    log.info("【部门信息】：");
    System.out.println(JSON.toJSONString(deptAll, true));

    // 为24号员工配置部门信息
    userDao
        .findById(24L)
        .ifPresent(
            user -> {
              user.setName("添加部门");
              user.setDepartmentDOList(departmentDOList);
              userDao.save(user);
            });
    log.info("用户所属部门:");
    log.info(JSON.toJSONString(userDao.findById(24L).get().getDepartmentDOList()));
  }

  @Test
  public void findTest() {
    departmentDao
        .findById(37L)
        .ifPresent(
            dept -> {
              Collection<UserDO> userList = dept.getUserList();

              // 关联关系由user维护中间表，department userList不会发生变化，可以增加查询方法来处理  重写getUserList方法
              System.out.println("部门下用户:" + JSON.toJSONString(userList));
            });
  }

  @Test
  public void updateTest() {
    userDao
        .findById(1L)
        .ifPresent(
            user -> {
              user.setName("清空部门");
              user.setDepartmentDOList(null);
              userDao.save(user);
            });
    log.info("用户部门={}", userDao.findById(1L).get().getDepartmentDOList());
  }
}
