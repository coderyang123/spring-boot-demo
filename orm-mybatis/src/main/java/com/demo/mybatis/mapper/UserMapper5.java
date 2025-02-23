package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-26 22:55:00
 */
@Mapper
public interface UserMapper5 {

  /**
   * 新增（获取自增ID）
   *
   * @param userDO 用户信息
   * @return 删除结果
   */
  int insertUser(UserDO userDO);

  /**
   * 新增（获取自增ID）
   *
   * @param userDOList 用户信息
   * @return 删除结果
   */
  int batchInsertUserList(List<UserDO> userDOList);
}
