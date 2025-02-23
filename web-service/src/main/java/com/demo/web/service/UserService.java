package com.demo.web.service;

import com.demo.web.domain.UserDTO;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-06-06 15:49:00
 */
// 指定你想要的名称空间，通常使用使用包名反转
@WebService(targetNamespace = "http://service.web.demo.com")
public interface UserService {

  /**
   * 添加用户
   *
   * @param username 用户名
   * @param password 密码
   * @param email 邮箱
   * @return 添加是否成功
   */
  int addUser(
      @WebParam(name = "username") String username,
      @WebParam(name = "password") String password,
      @WebParam(name = "email") String email);

  /**
   * 更新用户信息
   *
   * @param userDTO 用户传输类
   * @return 更新是否成功
   */
  int updateUser(@WebParam(name = "user") UserDTO userDTO);
}
