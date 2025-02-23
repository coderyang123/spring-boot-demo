package com.demo.alllearning.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 公共元数据处理器
 *
 * @author yueyang
 * @since 2021-03-08 22:30:00
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
    // 填充创建时间
    this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    // 填充修改时间
    this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    // 填充创建人（真实项目从上下文环境获取创建人，这里先用固定字符串代替）
    this.strictInsertFill(metaObject, "creator", String.class, "jack");
    // 填充操作人（真实项目从上下文环境获取操作人，这里先用固定字符串代替）
    this.strictInsertFill(metaObject, "operator", String.class, "jack");
    // 填充状态
    this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
    // 填充版本号
    this.strictInsertFill(metaObject, "version", Long.class, 1L);
  }

  /**
   * 修改时，填充系统字段
   *
   * @param metaObject 元数据
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    // 填充修改时间
    this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    // 填充操作人（真实项目从上下文环境获取操作人，这里先用固定字符串代替）
    this.strictUpdateFill(metaObject, "operator", String.class, "jack");
  }
}
