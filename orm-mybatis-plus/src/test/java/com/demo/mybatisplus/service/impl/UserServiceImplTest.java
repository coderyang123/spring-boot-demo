package com.demo.mybatisplus.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.domain.entity.UserDO;
import com.demo.mybatisplus.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
  @Autowired private UserService userService;

  /************************************************-新增-************************************************/

  @Test
  public void testSave() {
    UserDO user = new UserDO(null, "张三", 23, "zhangsan@atguigu.com", null);
    boolean result = userService.save(user);
    assertTrue(result);

    // 成功直接拿回写的 ID
    assertNotNull(user.getId());
  }

  /** 测试批量插入 */
  @Test
  public void testSaveBatch() {
    // SQL长度有限制，海量数据插入单条SQL无法实行，
    // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
    List<UserDO> users = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      UserDO user = new UserDO();
      user.setName("ybc" + i);
      user.setAge(20 + i);
      users.add(user);
    }
    // SQL:INSERT INTO t_user ( username, age ) VALUES ( ?, ? )
    boolean result = userService.saveBatch(users);
    assertTrue(result);
  }

  @Test
  public void testSaveOrUpdate() {
    UserDO user = new UserDO();
    user.setId(2L);
    user.setAge(18);

    boolean result = userService.saveOrUpdate(user);
    assertTrue(result);

    // 验证修改
    UserDO userDO = userService.getById(2L);
    assertEquals(18, userDO.getAge());
  }

  @Test
  public void testSaveOrUpdate2() {
    // 构造更新条件
    UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", 2L);

    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(18);

    boolean result = userService.saveOrUpdate(user, updateWrapper);
    assertTrue(result);

    // 验证修改
    UserDO userDO = userService.getById(2L);
    assertEquals(18, userDO.getAge());
  }

  @Test
  public void testSaveOrUpdateBatch() {
    UserDO user = new UserDO();
    user.setId(1L);
    user.setAge(25);
    UserDO user2 = new UserDO();
    user2.setId(2L);
    user2.setAge(25);
    List<UserDO> userList = List.of(user, user2);

    boolean result = userService.saveOrUpdateBatch(userList);
    assertTrue(result);
  }

  /** 把年龄18岁的用户都修改为26 */
  @Test
  public void testSaveOrUpdateBatch2() {
    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(26);
    UserDO user2 = new UserDO();
    user2.setAge(26);
    List<UserDO> userList = List.of(user, user2);

    // 构造更新条件
    UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("age", 18);

    boolean result = userService.saveOrUpdateBatch(userList);
    assertTrue(result);
  }

  /************************************************-删除-************************************************/

  /** 批量删除记录 */
  @Test
  public void testRemoveByIds() {
    List<Long> list = List.of(1L, 2L, 3L);

    boolean result = userService.removeByIds(list);
    assertTrue(result);
  }

  /** 批量删除记录 */
  @Test
  public void testRemoveBatchByIds() {
    List<Long> list = List.of(1L, 2L, 3L);

    boolean result = userService.removeBatchByIds(list);
    assertTrue(result);
  }

  /** 批量删除记录 */
  @Test
  public void testRemoveByMap() {
    // 构造删除条件
    Map<String, Object> map = Map.of("age", 18, "name", "Jone");

    boolean result = userService.removeByMap(map);
    assertTrue(result);
  }

  /** 条件删除 */
  @Test
  public void testRemove() {
    // 构造删除条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", 1L);

    boolean result = userService.remove(queryWrapper);
    assertTrue(result);
  }

  /** 条件删除所有 */
  @Test
  public void testRemove2() {
    boolean result = userService.remove(Wrappers.emptyWrapper());
    assertTrue(result);
  }

  /************************************************-更新-************************************************/

  /** 单个更新 */
  @Test
  public void testUpdateById() {
    UserDO user = new UserDO();
    user.setId(2L);
    user.setAge(18);

    boolean result = userService.updateById(user);
    assertTrue(result);
  }

  /** 批量更新 */
  @Test
  public void testUpdateBatchById() {
    UserDO user = new UserDO();
    user.setId(2L);
    user.setAge(18);
    UserDO user2 = new UserDO();
    user2.setId(3L);
    user2.setAge(18);
    List<UserDO> list = List.of(user, user2);

    boolean result = userService.updateBatchById(list);
    assertTrue(result);
  }

  /** 条件更新 */
  @Test
  public void testUpdate() {
    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(18);

    // 构造更新条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", 1L);

    boolean result = userService.update(user, queryWrapper);
    assertTrue(result);
  }

  /** 条件更新（lambda语法） */
  @Test
  public void testUpdate2() {
    boolean result =
        userService.lambdaUpdate().eq(UserDO::getId, 1).set(UserDO::getName, "大米").update();
    assertTrue(result);
  }

  /************************************************-普通查询-************************************************/

  /** 测试查询记录数 */
  @Test
  public void getCountTest() {
    long count = userService.count();
    assertEquals(7, count);
  }

  /** 查询单个 */
  @Test
  public void testGetById() {
    UserDO user = userService.getById(1L);
    assertNotNull(user);
  }

  /************************************************-条件查询-************************************************/

  /** 查询结果封装为Map */
  @Test
  public void testGetMap() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", 1L);

    Map<String, Object> map = userService.getMap(queryWrapper);
    assertNotNull(map);
  }

  /** 查询所有 */
  @Test
  public void testList() {
    List<UserDO> list = userService.list();
    assertNotNull(list);
  }

  /** 条件查询 */
  @Test
  public void testList2() {
    // 构造查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("age", 18);

    List<UserDO> list = userService.list(queryWrapper);
    assertNotNull(list);
  }

  /** 条件查询 */
  @Test
  public void testListByMap() {
    // 构造查询条件
    Map<String, Object> map = Map.of("age", 18);

    List<UserDO> list = userService.listByMap(map);
    assertNotNull(list);
  }

  /** 条件查询-结果封装为Map */
  @Test
  public void testListMaps() {
    List<Map<String, Object>> list = userService.listMaps();
    assertNotNull(list);
  }

  /** 条件查询-结果封装为Map */
  @Test
  public void testListMaps2() {
    // 构造查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("age", 18);

    List<Map<String, Object>> list = userService.listMaps(queryWrapper);
    assertNotNull(list);
  }

  /************************************************-Lambda条件查询-************************************************/

  /** 查询年龄大于18的用户 */
  @Test
  public void testList3() {
    // 构造查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("age", 18);

    List<UserDO> list = userService.lambdaQuery().gt(UserDO::getAge, 18).list();
    assertNotNull(list);
  }

  /** 查询年龄大于18的用户 */
  @Test
  public void testList4() {
    Integer age = 18;
    List<UserDO> list =
        userService.lambdaQuery().gt(Objects.nonNull(age), UserDO::getAge, age).list();
    assertNotNull(list);
  }

  /** 查询包含在内的用户 */
  @Test
  public void testList5() {
    List<String> nameList = List.of("Jone", "Jack", "Tom");

    List<UserDO> list = userService.lambdaQuery().in(UserDO::getAge, nameList).list();
    assertNotNull(list);
  }

  /** 查询部分字段 */
  @Test
  public void testListObjs() {
    List<Integer> ageList =
        userService.listObjs(
            new LambdaQueryWrapper<UserDO>().select(UserDO::getAge),
            age -> Integer.valueOf(age.toString()));
    System.out.println(ageList);
  }

  /** 查询部分字段 */
  @Test
  public void testListObjs2() {
    List<String> nameList =
        userService.listObjs(
            new LambdaQueryWrapper<UserDO>().select(UserDO::getName), Object::toString);
    System.out.println(nameList);
  }

  /************************************************-分页条件查询-************************************************/

  /** 条件查询-结果封装为Map */
  @Test
  public void testPage() {
    // 构造分页条件
    Page<UserDO> page = new Page<>(1, 5);

    Page<UserDO> resultPage = userService.page(page);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(7, total);

    // 对象集合
    List<UserDO> userList = resultPage.getRecords();
    assertNotNull(userList);
  }

  /** 条件查询-结果封装为Map */
  @Test
  public void testPage2() {
    // 构造分页条件
    Page<UserDO> page = new Page<>(1, 5);

    // 构造查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("age", 18);

    // 查询
    Page<UserDO> resultPage = userService.page(page, queryWrapper);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(1, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(3, total);

    // 对象集合
    List<UserDO> userList = resultPage.getRecords();
    assertNotNull(userList);
  }

  /** 条件查询-结果封装为Map */
  @Test
  public void testPageMaps() {
    // 构造分页条件
    Page<Map<String, Object>> page = new Page<>(1, 5);

    // 查询
    Page<Map<String, Object>> resultPage = userService.pageMaps(page);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(7, total);

    // 对象集合
    List<Map<String, Object>> userList = resultPage.getRecords();
    assertNotNull(userList);
  }

  /** 条件查询-结果封装为Map */
  @Test
  public void testPageMaps2() {
    // 构造分页条件
    Page<Map<String, Object>> page = new Page<>(1, 5);

    // 构造查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("age", 18);

    // 查询
    Page<Map<String, Object>> resultPage = userService.pageMaps(page, queryWrapper);
    assertNotNull(resultPage);

    // 总页数
    long pages = resultPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = resultPage.getTotal();
    assertEquals(7, total);

    // 对象集合
    List<Map<String, Object>> userList = resultPage.getRecords();
    assertNotNull(userList);
  }
}
