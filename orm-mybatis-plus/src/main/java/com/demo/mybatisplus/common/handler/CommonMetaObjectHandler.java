package com.demo.mybatisplus.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 公共元数据处理器
 *
 * @author yueyang
 * @since 2022-07-21 15:30:00
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

  /**
   * 新增时，填充系统字段
   *
   * @param metaObject 元数据
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
  }

  /**
   * 修改时，填充系统字段
   *
   * @param metaObject 元数据
   */
  @Override
  public void updateFill(MetaObject metaObject) {}
}
