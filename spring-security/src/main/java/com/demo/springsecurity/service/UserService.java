package com.demo.springsecurity.service;

import com.demo.springsecurity.domain.dto.UserDTO;
import com.demo.springsecurity.domain.entity.UserDO;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-04-23 12:10:00
 */
public interface UserService {

  /**
   * 根据用户名获取用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  UserDO selectUserByUserName(String username);

  /**
   * 新增用户
   *
   * @param userDTO 用户信息
   */
  void addUser(UserDTO userDTO);
}
