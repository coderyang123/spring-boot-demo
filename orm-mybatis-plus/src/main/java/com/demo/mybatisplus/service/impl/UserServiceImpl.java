package com.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mybatisplus.domain.bo.UserInfoBO;
import com.demo.mybatisplus.domain.dto.QueryDTO;
import com.demo.mybatisplus.domain.entity.UserDO;
import com.demo.mybatisplus.mapper.UserMapper;
import com.demo.mybatisplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
  private final UserMapper userMapper;

  /**
   * 自定义查询用户集合
   *
   * @param page 分页参数
   * @param queryDTO 查询条件参数
   * @return 用户集合
   */
  @Override
  public Page<UserDO> listUser(Page<QueryDTO> page, QueryDTO queryDTO) {
    return userMapper.listUser(page, queryDTO);
  }

  /**
   * 自定义查询用户及其商品集合
   *
   * @param page 分页参数
   * @param queryDTO 查询条件参数
   * @return 用户集合
   */
  @Override
  public Page<UserInfoBO> listUserAndGoods(Page<QueryDTO> page, QueryDTO queryDTO) {
    return userMapper.listUserAndGoods(page, queryDTO);
  }
}
