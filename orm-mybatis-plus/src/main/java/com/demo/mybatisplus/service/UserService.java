package com.demo.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mybatisplus.domain.bo.UserInfoBO;
import com.demo.mybatisplus.domain.dto.QueryDTO;
import com.demo.mybatisplus.domain.entity.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
public interface UserService extends IService<UserDO> {

  /**
   * 自定义查询用户集合
   *
   * @param page 分页对象
   * @param queryDTO 查询传输实体类
   * @return 用户集合
   */
  Page<UserDO> listUser(Page<QueryDTO> page, @Param("queryDTO") QueryDTO queryDTO);

  /**
   * 自定义查询用户及其商品集合
   *
   * @param page 分页对象
   * @param queryDTO 查询传输实体类
   * @return 用户集合
   */
  Page<UserInfoBO> listUserAndGoods(Page<QueryDTO> page, QueryDTO queryDTO);
}
