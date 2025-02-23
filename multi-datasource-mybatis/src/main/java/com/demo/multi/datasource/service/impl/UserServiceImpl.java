package com.demo.multi.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.multi.datasource.domain.User;
import com.demo.multi.datasource.mapper.UserMapper;
import com.demo.multi.datasource.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 数据服务层实现
 *
 * @author yueyang
 * @since 2022-06-01 17:10:00
 */
@Service
// 代表所有方法都走从库
@DS("slave")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  /**
   * 在方法上写 {@code @DS("master")} 代表该方法走主库
   *
   * @param user 用户
   */
  @DS("master")
  @Override
  public void addUser(User user) {
    baseMapper.insert(user);
  }
}
