package com.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mybatisplus.domain.entity.TimeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 时间管理
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@Mapper
@Repository
public interface TimeMapper extends BaseMapper<TimeDO> {}
