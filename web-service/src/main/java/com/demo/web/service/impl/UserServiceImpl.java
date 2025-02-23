package com.demo.web.service.impl;

import com.demo.web.domain.UserDTO;
import com.demo.web.service.UserService;
import javax.jws.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-06-06 15:50:00
 */
@Slf4j
@Component
@WebService(
    serviceName = "userService", // 对外发布的服务名
    targetNamespace = "http://service.web.demo.com", // 指定你想要的名称空间，通常使用使用包名反转
    endpointInterface = "com.demo.web.service.UserService")
public class UserServiceImpl implements UserService {

  /**
   * 添加用户
   *
   * @param email 邮箱
   * @param username 用户名
   * @param password 密码
   * @return 添加是否成功
   */
  @Override
  public int addUser(String email, String username, String password) {
    log.info("模拟添加用户成功~");
    return 0;
  }

  /**
   * 更新用户信息
   *
   * @param userDTO 用户传输类
   * @return 更新是否成功
   */
  @Override
  public int updateUser(UserDTO userDTO) {
    log.info("模拟更新用户成功~");
    return 0;
  }
}
