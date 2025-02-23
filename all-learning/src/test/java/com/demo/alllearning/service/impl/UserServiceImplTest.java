package com.demo.alllearning.service.impl;

import com.demo.alllearning.domain.common.PageQuery;
import com.demo.alllearning.domain.common.PageResult;
import com.demo.alllearning.domain.dto.UserDTO;
import com.demo.alllearning.domain.dto.UserQueryDTO;
import com.demo.alllearning.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceImplTest {
  @Autowired UserService userService;

  @Test
  void save() {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("jerry");
    userDTO.setPassword("123456");
    userDTO.setAge(20);
    userDTO.setEmail("1023178987@qq.com");
    userDTO.setPhone("18209887488");
    userDTO.setVersion(1L);
    int save = userService.save(userDTO);
    log.info("{}", save);
  }

  /**
   * 更新时乐观锁使用规则 1.如果更新数据中version字段为null：更新不使用乐观锁，version字段不累加
   * 2.如果更新数据中version字段不为null，但值和数据库里的不一致：更新使用乐观锁，更新失败
   * 3.如果更新数据中version字段不为null，且值和数据库里的一致：更新使用乐观锁，更新成功
   */
  @Test
  void update() {
    Long id = 1368935465749397505L;
    UserDTO userDTO = new UserDTO();
    userDTO.setAge(20);
    userDTO.setPassword("111111");
    //    userDTO.setVersion(1L);
    int update = userService.update(id, userDTO);
    log.info("{}", update);
  }

  @Test
  void delete() {
    int delete = userService.delete(1380927054004666370L);
    log.info("{}", delete);
  }

  @Test
  void query() {
    PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
    pageQuery.setPageNo(1);
    pageQuery.setPageSize(10);
    pageQuery.setQuery(new UserQueryDTO());
    PageResult<List<UserDTO>> result = userService.query(pageQuery);
    log.info("{}", result);
  }
}
