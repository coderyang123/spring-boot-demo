package com.demo.alllearning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.alllearning.domain.entity.UserDO;
import org.springframework.stereotype.Repository;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-03-07 22:50:00
 */
// 将类标识为 Spring Bean
@Repository
public interface UserMapper extends BaseMapper<UserDO> {}
