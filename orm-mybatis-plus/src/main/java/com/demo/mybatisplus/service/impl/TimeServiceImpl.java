package com.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mybatisplus.domain.entity.TimeDO;
import com.demo.mybatisplus.mapper.TimeMapper;
import com.demo.mybatisplus.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 时间管理
 *
 * @author yueyang
 * @since 2023-01-14 00:05:00
 */
@Service
@RequiredArgsConstructor
public class TimeServiceImpl extends ServiceImpl<TimeMapper, TimeDO> implements TimeService {}
