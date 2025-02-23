package com.demo.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.springsecurity.domain.entity.UserDO;
import org.springframework.stereotype.Repository;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-04-26 22:02:00
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {}
