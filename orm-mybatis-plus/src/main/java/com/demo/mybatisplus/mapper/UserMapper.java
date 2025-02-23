package com.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.domain.bo.UserInfoBO;
import com.demo.mybatisplus.domain.dto.QueryDTO;
import com.demo.mybatisplus.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserDO> {

  /**
   * 自定义查询用户集合
   *
   * @param page 分页参数
   * @param queryDTO 查询传输实体类
   * @return 用户集合
   */
  Page<UserDO> listUser(Page<QueryDTO> page, QueryDTO queryDTO);

  /**
   * 自定义查询用户及其商品集合
   *
   * @param page 分页参数
   * @param queryDTO 查询传输实体类
   * @return 用户集合
   */
  Page<UserInfoBO> listUserAndGoods(Page<QueryDTO> page, QueryDTO queryDTO);
}
