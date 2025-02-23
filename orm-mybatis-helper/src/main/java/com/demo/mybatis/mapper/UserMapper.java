package com.demo.mybatis.mapper;

import com.demo.mybatis.entity.UserDO;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 用户管理
 *
 * @author yueyang
 * @since 2022-02-27 22:55:00
 */
@Repository
public interface UserMapper extends Mapper<UserDO>, MySqlMapper<UserDO> {

  /**
   * 查询用户数据
   *
   * @return 用户数据
   */
  List<UserDO> getUserByLoginTime();
}
