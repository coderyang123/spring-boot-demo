package com.demo.multi.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.multi.datasource.domain.User;

/**
 * 数据服务层
 *
 * @author yueyang
 * @since 2022-06-01 17:13:00
 */
public interface UserService extends IService<User> {

  /**
   * 添加 User
   *
   * @param user 用户
   */
  void addUser(User user);
}
