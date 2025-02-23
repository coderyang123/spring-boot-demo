package com.demo.druid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.druid.domain.entity.UserDO;
import com.demo.druid.mapper.UserMapper;
import com.demo.druid.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-05-28 10:50:00
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  //  @Override
  //  public int create(UserDO userDO) {
  //    return userMapper.insert(userDO);
  //  }

  //  @Override
  //  public UserDO getByName(String username) {
  //    QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
  //    wrapper.eq("username", username);
  //    return userMapper.selectOne(wrapper);
  //  }

  //  @Override
  //  public int deleteById(Long id) {
  //    return userMapper.deleteById(id);
  //  }

}
