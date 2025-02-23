package com.demo.mybatis;

import com.alibaba.fastjson.JSON;
import com.demo.mybatis.entity.UserDO;
import com.demo.mybatis.entity.UserVO;
import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.util.PageInfoUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

/**
 * 测试
 *
 * @author yueyang
 * @since 2022-02-27 22:55:00
 */
@Slf4j
public class UserMapperTest extends OrmMybatisHelperApplicationTests {

  @Autowired private UserMapper userMapper;

  /** 测试通用Mapper - 单个新增 */
  @Test
  public void insertTest() {
    UserDO userDO =
        UserDO.builder()
            .name("testSave3")
            .password("111111")
            .salt("abc")
            .email("testSave3@xkcoding.com")
            .phoneNumber("17300000003")
            .status(1)
            .lastLoginTime(new Date())
            .createTime(new Date())
            .lastUpdateTime(new Date())
            .build();
    userMapper.insertUseGeneratedKeys(userDO);
    log.info("【测试新增主键回写：{}", userDO.getId());
  }

  /** 测试通用Mapper - 批量新增 */
  @Test
  public void batchInsertTest() {
    List<UserDO> userList = Lists.newArrayList();
    for (int i = 4; i < 14; i++) {
      UserDO user =
          UserDO.builder()
              .name("testSave" + i)
              .password("111111")
              .salt("abc")
              .email("testSave" + i + "@code.com")
              .phoneNumber("1730000000" + i)
              .status(1)
              .lastLoginTime(new Date())
              .createTime(new Date())
              .lastUpdateTime(new Date())
              .build();
      userList.add(user);
    }

    int result = userMapper.insertList(userList);
    List<Long> ids = userList.stream().map(UserDO::getId).collect(Collectors.toList());
    log.info("【测试批量新增影响行数】= {}", result);
    log.info("【测试主键回写#userList.ids】= {}", ids);
  }

  /** 测试通用Mapper - 单个删除 */
  @Test
  public void deleteTest() {
    Long primaryKey = 13L;
    int result = userMapper.deleteByPrimaryKey(primaryKey);
    UserDO user = userMapper.selectByPrimaryKey(primaryKey);

    log.info("【测试删除影响行数】= {}", result);
    log.info("【测试记录是否被删除】= {}", user);
  }

  /** 测试通用Mapper - 单个更新 */
  @Test
  public void updateTest() {
    Long primaryKey = 1L;
    UserDO user = userMapper.selectByPrimaryKey(primaryKey);
    user.setName("通用Mapper名字更新");

    int result = userMapper.updateByPrimaryKeySelective(user);
    log.info("【测试更新影响行数】= {}", result);

    UserDO update = userMapper.selectByPrimaryKey(primaryKey);
    log.info("【更新后的对象信息】= {}", update);
  }

  /** 测试通用Mapper - 单个查询 */
  @Test
  public void queryOneTest() {
    UserDO user = userMapper.selectByPrimaryKey(1L);
    log.debug("【user】= {}", user);
  }

  /** 测试通用Mapper - 全部查询 */
  @Test
  public void queryAllTest() {
    List<UserDO> users = userMapper.selectAll();
    log.debug("【users】= {}", users);
  }

  /** 测试通用Mapper和分页助手 - 条件查询 */
  @Test
  public void queryByConditionTest() {
    Example example = new Example(UserDO.class);

    // 过滤
    example.createCriteria().andLike("name", "%Save1%").orEqualTo("phoneNumber", "17300000001");

    // 排序
    example.setOrderByClause("id desc");
    int count = userMapper.selectCountByExample(example);
    log.info("【记录数】= {}", count);

    // 分页
    PageHelper.startPage(1, 3);

    // 查询
    List<UserDO> userList = userMapper.selectByExample(example);
    PageInfo<UserDO> userPageInfo = new PageInfo<>(userList);
    log.debug("【userPageInfo】:");
    log.info(JSON.toJSONString(userPageInfo, true));
  }

  /** 测试分页助手 - 分页排序查询 */
  @Test
  public void queryByPageAndSortTest() {
    int currentPage = 2;
    int pageSize = 5;
    String orderBy = "id desc";

    // 查询总记录数
    int count = userMapper.selectCount(null);
    log.debug("【记录总行数】= {}", count);

    // 分页配置
    PageHelper.startPage(currentPage, pageSize, orderBy);
    List<UserDO> userList = userMapper.selectAll();
    PageInfo<UserDO> userPageInfo = new PageInfo<>(userList);
    log.debug("【userPageInfo】:");
    log.info(JSON.toJSONString(userPageInfo, true));
  }

  /** 测试分页助手 - 分页排序查询 */
  @Test
  public void testGetUserByLoginTime() {
    PageHelper.startPage(1, 5);
    List<UserDO> userList = userMapper.getUserByLoginTime();
    PageInfo<UserDO> userPageInfo = new PageInfo<>(userList);
    log.debug("【userPageInfo】:");
    log.info(JSON.toJSONString(userPageInfo, true));
  }

  /** 测试分页助手 - 分页排序查询 - 转换参数值 */
  @Test
  public void testGetUserByLoginTime2() {
    PageHelper.startPage(1, 5);
    List<UserDO> userList = userMapper.getUserByLoginTime();
    PageInfo<UserDO> userPageInfo = new PageInfo<>(userList);
    log.info(JSON.toJSONString(userPageInfo, true));

    // 转换部分元素值
    List<UserVO> list = new ArrayList<>();
    userList.forEach(
        user -> {
          UserVO userVO = new UserVO();
          BeanUtils.copyProperties(user, userVO);
          list.add(userVO);
        });

    // 重新封装分页参数
    PageInfo<UserVO> userPageInfo2 = new PageInfo<>();
    BeanUtils.copyProperties(userPageInfo, userPageInfo2);
    userPageInfo2.setList(list);
    log.info(JSON.toJSONString(userPageInfo2, true));
  }

  /** 测试分页助手 - 分页排序查询 - 转换参数值 */
  @Test
  public void testGetUserByLoginTime3() {
    PageHelper.startPage(1, 5);
    List<UserDO> userList = userMapper.getUserByLoginTime();
    PageInfo<UserDO> userPageInfo = new PageInfo<>(userList);
    log.info(JSON.toJSONString(userPageInfo, true));

    // 转换部分元素值
    PageInfo<UserVO> userPageInfo2 = PageInfoUtils.pageInfo2VO(userPageInfo, UserVO.class);
    log.info(JSON.toJSONString(userPageInfo2, true));
  }
}
