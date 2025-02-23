package com.demo.alllearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.alllearning.common.util.ValidatorUtils;
import com.demo.alllearning.domain.common.PageQuery;
import com.demo.alllearning.domain.common.PageResult;
import com.demo.alllearning.domain.dto.UserDTO;
import com.demo.alllearning.domain.dto.UserQueryDTO;
import com.demo.alllearning.domain.entity.UserDO;
import com.demo.alllearning.mapper.UserMapper;
import com.demo.alllearning.service.UserService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-03-08 19:57:00
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  /**
   * 新增
   *
   * @param userDTO 用户传输实体类
   * @return 新增行数
   */
  @Override
  public int save(UserDTO userDTO) {
    UserDO userDO = new UserDO();
    // 对象拷贝
    BeanUtils.copyProperties(userDTO, userDO);
    // 新增
    return userMapper.insert(userDO);
  }

  /**
   * 更新
   *
   * @param id 用户ID
   * @param userDTO 用户传输实体类
   * @return 更新行数
   */
  @Override
  public int update(Long id, UserDTO userDTO) {
    UserDO userDO = new UserDO();
    // 对象拷贝
    BeanUtils.copyProperties(userDTO, userDO);
    userDO.setId(id);
    // 更新
    return userMapper.updateById(userDO);
  }

  /**
   * 删除
   *
   * @param id 用户ID
   * @return 删除行数
   */
  @Override
  public int delete(Long id) {
    return userMapper.deleteById(id);
  }

  /**
   * @param pageQuery 通用分页查询实体
   * @return 用户集合
   */
  @Override
  public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
    log.info("未使用缓存");
    // 校验参数
    ValidatorUtils.validate(pageQuery);

    // 构造参数
    Page<UserDO> userDoPage = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
    UserDO query = new UserDO();
    BeanUtils.copyProperties(pageQuery.getQuery(), query);
    QueryWrapper<UserDO> userDoQueryWrapper = new QueryWrapper<>(query);

    // 查询
    Page<UserDO> page = userMapper.selectPage(userDoPage, userDoQueryWrapper);

    // 结果解析
    PageResult<List<UserDTO>> userDoPageResult = new PageResult<>();
    userDoPageResult.setPageNo((int) page.getCurrent());
    userDoPageResult.setPageSize((int) page.getSize());
    userDoPageResult.setTotalNum(page.getTotal());
    userDoPageResult.setTotalPage((int) page.getPages());
    List<UserDTO> userDTOList =
        Optional.ofNullable(page.getRecords()).stream()
            .flatMap(Collection::stream)
            .map(
                userDO -> {
                  UserDTO userDTO = new UserDTO();
                  BeanUtils.copyProperties(userDO, userDTO);
                  return userDTO;
                })
            .collect(Collectors.toList());
    userDoPageResult.setData(userDTOList);
    return userDoPageResult;
  }
}
