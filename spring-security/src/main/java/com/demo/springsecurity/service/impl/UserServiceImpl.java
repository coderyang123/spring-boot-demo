package com.demo.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.springsecurity.domain.dto.UserDTO;
import com.demo.springsecurity.domain.entity.UserDO;
import com.demo.springsecurity.mapper.UserMapper;
import com.demo.springsecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-04-23 12:10:00
 */
@Service
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  /**
   * 根据用户名获取用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  @Override
  public UserDO selectUserByUserName(String username) {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    return userMapper.selectOne(queryWrapper);
  }

  /**
   * 新增用户
   *
   * @param userDTO 用户信息
   */
  @Override
  public void addUser(UserDTO userDTO) {
    UserDO userDO = new UserDO();
    BeanUtils.copyProperties(userDTO, userDO);

    // 密码加密
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    userDO.setPassword(bCryptPasswordEncoder.encode(userDO.getPassword()));

    userMapper.insert(userDO);
  }
}
