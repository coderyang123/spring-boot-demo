package com.demo.jpa.repository;

import com.demo.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 部门管理类
 *
 * @author yueyang
 * @since 2022-02-28 14:44:00
 */
@Repository
public interface UserDao extends JpaRepository<UserDO, Long> {}
