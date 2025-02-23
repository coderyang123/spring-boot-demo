package com.demo.druid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.druid.domain.entity.UserDO;
import org.springframework.stereotype.Repository;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-05-28 10:50:00
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {}
