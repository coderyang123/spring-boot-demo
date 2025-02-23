package com.demo.easyexcel.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.demo.easyexcel.domain.dto.StudentExportDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * EXCEL文件读取监听器
 *
 * @author yueyang
 * @since 2021-04-04 00:11:00
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<StudentExportDTO> {

  /**
   * 当每一条数据解析都会来调用
   *
   * @param data one row value. Is is same as {@link AnalysisContext#readRowHolder()}
   * @param context context
   */
  @Override
  public void invoke(StudentExportDTO data, AnalysisContext context) {
    log.info("解析到一条数据：{}", data);
  }

  /**
   * 当所有数据解析完成了时来调用
   *
   * @param context context
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    log.info("所有数据解析完成！");
  }
}
