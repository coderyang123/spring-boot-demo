package com.demo.jpa.repository;

import com.demo.jpa.OrmJpaApplicationTests;
import com.demo.jpa.entity.UserDO;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 测试类
 *
 * @author yueyang
 * @since 2022-02-28 14:44:00
 */
@Slf4j
public class UserDODaoTests extends OrmJpaApplicationTests {
  @Autowired private UserDao userDao;

  /** 测试保存 */
  @Test
  public void saveTest() {
    UserDO userDO =
        UserDO.builder()
            .name("testSave6")
            .password("111111")
            .salt("abc")
            .email("testSave6@code.com")
            .phoneNumber("17300000006")
            .status(1)
            .lastLoginTime(new Date())
            .build();
    userDao.save(userDO);

    Optional<UserDO> byId = userDao.findById(userDO.getId());
    log.info("【byId】= {}", byId.get());
  }

  /** 测试删除 */
  @Test
  public void deleteTest() {
    long count = userDao.count();
    log.info("【count】= {}", count);

    userDao.deleteById(11L);
    long afterDeleteCount = userDao.count();
    log.info("【afterDeleteCount】= {}", afterDeleteCount);
  }

  /** 测试修改 */
  @Test
  public void updateTest() {
    userDao
        .findById(3L)
        .ifPresent(
            user -> {
              user.setName("JPA修改名字");
              userDao.save(user);
            });
    log.info("【JPA修改名字】= {}", userDao.findById(1L).get().getName());
  }

  /** 测试查询单个 */
  @Test
  public void queryOneTest() {
    Optional<UserDO> byId = userDao.findById(1L);
    log.info("【byId】= {}", byId.get());
  }

  /** 测试查询所有 */
  @Test
  public void queryAllTest() {
    List<UserDO> users = userDao.findAll();
    log.info("【users】= {}", users);
  }

  /** 测试分页排序查询 */
  @Test
  public void queryPageTest() {
    // 初始化数据
    //    initData();

    // JPA分页的时候起始页是页码减1
    int currentPage = 0;
    int pageSize = 5;
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
    Page<UserDO> userPage = userDao.findAll(pageRequest);

    log.info("【size】= {}", userPage.getSize());
    log.info("【getTotalElements】= {}", userPage.getTotalElements());
    log.info(
        "【id】= {}", userPage.getContent().stream().map(UserDO::getId).collect(Collectors.toList()));
  }

  /** 初始化10条数据 */
  private void initData() {
    List<UserDO> userList = Lists.newArrayList();
    for (int i = 0; i < 10; i++) {
      int index = 3 + i;
      UserDO user =
          UserDO.builder()
              .name("testSave" + index)
              .password("111111")
              .salt("abc")
              .email("testSave" + index + "@code.com")
              .phoneNumber("1730000000" + index)
              .status(1)
              .lastLoginTime(new Date())
              .build();
      userList.add(user);
    }
    userDao.saveAll(userList);
  }
}
