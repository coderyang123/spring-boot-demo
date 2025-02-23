package com.demo.alllearning.service;

import com.demo.alllearning.domain.common.PageQuery;
import com.demo.alllearning.domain.common.PageResult;
import com.demo.alllearning.domain.dto.UserDTO;
import com.demo.alllearning.domain.dto.UserQueryDTO;
import java.util.List;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2021-03-07 23:46:00
 */
public interface UserService {

  /**
   * 新增
   *
   * @param userDTO 用户传输实体类
   * @return 新增行数
   */
  int save(UserDTO userDTO);

  /**
   * 更新
   *
   * @param id 用户ID
   * @param userDTO 用户传输实体类
   * @return 更新行数
   */
  int update(Long id, UserDTO userDTO);

  /**
   * 删除
   *
   * @param id 用户ID
   * @return 删除行数
   */
  int delete(Long id);

  /**
   * 分页查询
   *
   * @param pageQuery 通用分页查询实体
   * @return 用户集合
   */
  PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
