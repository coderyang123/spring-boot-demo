package com.demo.mybatisplus.mapper;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.domain.entity.UserDO;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
  @Autowired private UserMapper userMapper;

  /************************************************-新增-************************************************/

  /** 测试新增 */
  @Test
  public void testInsert() {
    UserDO user = new UserDO(null, "张三", 23, "zhangsan@atguigu.com", null);

    // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    int result = userMapper.insert(user);
    assertEquals(1, result);

    // 成功直接拿回写的 ID
    assertNotNull(user.getId());
  }

  /************************************************-删除-************************************************/

  /** 测试删除 */
  @Test
  public void testDeleteById() {
    // 通过id删除用户信息
    // DELETE FROM user WHERE id=?
    int result = userMapper.deleteById(1L);
    assertEquals(1, result);
  }

  /** 测试批量删除 */
  @Test
  public void testDeleteBatchIds() {
    // 通过多个id批量删除
    // DELETE FROM user WHERE id IN ( ? , ? , ? )
    List<Integer> idList = List.of(1, 2, 3);

    int result = userMapper.deleteBatchIds(idList);
    assertEquals(3, result);
  }

  /** 测试map条件删除 */
  @Test
  public void testDeleteByMap() {
    // 根据map集合中所设置的条件删除记录
    // DELETE FROM user WHERE age = ? AND name = ?
    Map<String, Object> map = Map.of("age", 20, "name", "Jack");

    int result = userMapper.deleteByMap(map);
    assertEquals(1, result);
  }

  /** 测试条件删除 */
  @Test
  public void testDelete() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "Jack");

    int result = userMapper.delete(queryWrapper);
    assertEquals(1, result);
  }

  /** 测试条件删除（lambda语法） */
  @Test
  public void testDelete2() {
    LambdaQueryWrapper<UserDO> queryWrapper =
        new QueryWrapper<UserDO>().lambda().eq(UserDO::getName, "Jack");

    int result = userMapper.delete(queryWrapper);
    assertEquals(1, result);
  }

  /** 测试条件删除（lambda语法） */
  @Test
  public void testDelete3() {
    LambdaQueryWrapper<UserDO> queryWrapper =
        new LambdaQueryWrapper<UserDO>().eq(UserDO::getName, "Jack");

    int result = userMapper.delete(queryWrapper);
    assertEquals(1, result);
  }

  /************************************************-更新-************************************************/

  /** 测试修改 */
  @Test
  public void testUpdateById() {
    // UPDATE user SET name=?, age=? WHERE id=?
    UserDO user = new UserDO(5L, "admin", 22, "test5@baomidou.com", null);

    int result = userMapper.updateById(user);
    assertEquals(1, result);
  }

  /** 测试修改 */
  @Test
  public void testUpdate() {
    // 构造更新条件
    UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", "1");

    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(22);

    int result = userMapper.update(user, updateWrapper);
    assertEquals(1, result);
  }

  /** 测试修改 */
  @Test
  public void testUpdate2() {
    // 构造更新条件
    UserDO update = new UserDO();
    update.setId(1L);
    UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>(update);

    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(22);

    int result = userMapper.update(user, updateWrapper);
    assertEquals(1, result);
  }

  /** 测试修改（lambda语法） */
  @Test
  public void testUpdate3() {
    // 构造更新条件
    LambdaUpdateWrapper<UserDO> updateWrapper =
        new LambdaUpdateWrapper<UserDO>().eq(UserDO::getName, "Jack").set(UserDO::getAge, "20");

    // 构造更新数据
    UserDO user = new UserDO();
    user.setAge(22);

    int result = userMapper.update(user, updateWrapper);
    assertEquals(1, result);
  }

  /** 测试修改（lambda链式语法） */
  @Test
  public void testUpdate4() {
    boolean bool =
        new LambdaUpdateChainWrapper<>(userMapper)
            .eq(UserDO::getId, 1)
            .set(UserDO::getName, "大米")
            .update();
    assertTrue(bool);
  }

  /************************************************-普通查询-************************************************/

  /** 根据id查询 */
  @Test
  public void testSelectById() {
    // 根据id查询用户信息
    // SELECT id,name,age,email FROM user WHERE id=?
    UserDO user = userMapper.selectById(4L);
    assertNotNull(user);
  }

  /** 根据多个id查询多个用户 */
  @Test
  public void testSelectBatchIds() {
    // 根据多个id查询多个用户信息
    // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
    List<Long> idList = asList(4L, 5L);
    List<UserDO> userList = userMapper.selectBatchIds(idList);
    assertNotNull(userList);
  }

  /** 通过map条件查询 */
  @Test
  public void testSelectByMap() {
    // 通过map条件查询用户信息
    // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    Map<String, Object> map = Map.of("age", 20, "name", "Jack");
    List<UserDO> list = userMapper.selectByMap(map);
    assertNotNull(list);
  }

  /** 查询部分字段 */
  @Test
  public void testSelectObjs() {
    List<Object> nameList =
        userMapper.selectObjs(new QueryWrapper<UserDO>().select("name").eq("age", 18));
    System.out.println("nameList：" + nameList);
  }

  /************************************************-条件查询-************************************************/

  /** 查询所有 */
  @Test
  public void testSelectList() {
    // 查询所有用户信息
    // SELECT id,name,age,email FROM user
    List<UserDO> list = userMapper.selectList(null);
    assertNotNull(list);
  }

  /** 查询名字中包含雨并且年龄小于40 */
  @Test
  public void testSelectList2() {
    // name like '%雨%' and age < 40
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", "雨").lt("age", 40);

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询名字中包含雨年并且龄大于等于20且小于等于40并且email不为空 */
  @Test
  public void testSelectList3() {
    // name like '%雨%' and age between 20 and 40 and email is not null
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列 */
  @Test
  public void testSelectList4() {
    // name like '王%' or age>=25 order by age desc, id asc
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("name", "王").or().gt("age", 25).orderByDesc("age").orderByAsc("id");

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询创建日期为2019年2月14日并且直属上级为名字为王姓 */
  @Test
  public void testSelectList5() {
    // date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where
    // name like '王%')
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .apply("date_format(create_time, '%Y-%m-%d')={0}", "2019-02-12")
        .inSql("id", "select id from user where name like '王%");

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询名字为王姓并且年龄小于40或邮箱不为空 */
  @Test
  public void testSelectList6() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .likeRight("name", "王")
        .and(condition -> condition.lt("age", 40).or().isNotNull("email"));

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询名字为王姓并且（年龄小于40或邮箱不为空） */
  @Test
  public void testSelectList7() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .likeRight("name", "王")
        .nested(condition -> condition.lt("age", 40).or().isNotNull("email"));

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 范围查询：年龄为20，21，22 */
  @Test
  public void testSelectList8() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.in("age", List.of(20, 21, 22));

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 范围查询：年龄为20，21，22，只返回最后一条 */
  @Test
  public void testSelectList9() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.in("age", List.of(20, 21, 22)).last("LIMIT 1");

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询部分字段 */
  @Test
  public void testSelectList10() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("name", "age").eq("id", 1);

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询部分字段（排除部分字段） */
  @Test
  public void testSelectList11() {
    // 排除email字段
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select(UserDO.class, user -> !Objects.equals(user.getColumn(), "email"))
        .lt("age", 40);

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
    assertNotNull(userList);
  }

  /** 查询名字中包含雨并且年龄小于40 */
  @Test
  public void testSelectList12() {
    String name = "雨";
    // name like '%雨%' and age < 40
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.like(!isEmpty(name), "name", name).lt("age", 40);

    List<UserDO> userList = userMapper.selectList(queryWrapper);
    assertNotNull(userList);
  }

  /** 查询名字中包含雨并且年龄小于40 */
  @Test
  public void testSelectMaps() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "name").gt("age", 18);

    List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
    assertNotNull(list);
  }

  /** 查询年龄为18的用户数量 */
  @Test
  public void testSelectCount() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.select().gt("age", 18);

    Long count = userMapper.selectCount(queryWrapper);
    assertEquals(4, count);
  }

  /** 查询年龄为18的用户数量 */
  @Test
  public void testSelectOne() {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.select().gt("id", 1);

    UserDO user = userMapper.selectOne(queryWrapper);
    assertNotNull(user);
  }

  /************************************************-Lambda条件查询-************************************************/

  /** 查询ID为1的用户 */
  @Test
  public void testSelectOne2() {
    LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.select().eq(UserDO::getId, 1L);

    UserDO user = userMapper.selectOne(queryWrapper);
    assertNotNull(user);
  }

  /** 查询ID为1的用户的姓名 */
  @Test
  public void testSelectOne3() {
    LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.select(UserDO::getName).eq(UserDO::getId, 1L);

    UserDO user = userMapper.selectOne(queryWrapper);
    assertNotNull(user);
  }

  /** 查询年龄大于18的用户 */
  @Test
  public void testList() {
    List<UserDO> list = new LambdaQueryChainWrapper<>(userMapper).gt(UserDO::getAge, 18).list();
    assertNotNull(list);
  }

  /** 查询年龄等于18的用户 */
  @Test
  public void testList2() {
    Integer age = 18;
    List<UserDO> list =
        new LambdaQueryChainWrapper<>(userMapper).eq(nonNull(age), UserDO::getAge, age).list();
    assertNotNull(list);
  }

  /************************************************-分页条件查询-************************************************/

  @Test
  public void testSelectPage() {
    // 分页条件
    Page<UserDO> page = new Page<>(1, 5);

    // 查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.ge("age", 18);

    Page<UserDO> userPage = userMapper.selectPage(page, queryWrapper);

    // 总页数
    long pages = userPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = userPage.getTotal();
    assertEquals(7, total);

    // 对象集合
    List<UserDO> userList = userPage.getRecords();
    assertNotNull(userList);
  }

  @Test
  public void testSelectMapsPage() {
    // 分页条件
    Page<Map<String, Object>> page = new Page<>(1, 5);

    // 查询条件
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.ge("age", 18);

    Page<Map<String, Object>> userPage = userMapper.selectMapsPage(page, queryWrapper);

    // 总页数
    long pages = userPage.getPages();
    assertEquals(2, pages);

    // 总记录数
    long total = userPage.getTotal();
    assertEquals(7, total);

    // 对象集合
    List<Map<String, Object>> userList = userPage.getRecords();
    assertNotNull(userList);
  }
}
