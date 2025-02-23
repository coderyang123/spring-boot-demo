package com.demo.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.easyexcel.domain.entity.DictDO;
import java.io.InputStream;
import java.util.List;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2021-04-04 13:17:00
 */
public interface DictDoService extends IService<DictDO> {

  /**
   * 导入Excel
   *
   * @param inputStream inputStream
   */
  void importExcel(InputStream inputStream);

  /**
   * 获取字典集合
   *
   * @return 字典集合
   */
  List<DictDO> listDictDo();

  /** 异步导出Excel */
  void asyncExportExcel();
}
