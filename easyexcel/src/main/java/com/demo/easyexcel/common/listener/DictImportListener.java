package com.demo.easyexcel.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.demo.easyexcel.domain.entity.DictDO;
import com.demo.easyexcel.mapper.DictDoMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典导入EXCEL文件读取监听器
 *
 * @author yueyang
 * @since 2021-04-04 13:07:00
 */
@Slf4j
@RequiredArgsConstructor
public class DictImportListener extends AnalysisEventListener<DictDO> {
  private final DictDoMapper dictDoMapper;

  /** 每次批量插入的数据数量 */
  private static final int BATCH_SIZE = 10;

  /** 字典数据集合 */
  private final List<DictDO> dictDoList = new ArrayList<>();

  @Override
  public void invoke(DictDO data, AnalysisContext context) {
    dictDoList.add(data);
    if (dictDoList.size() >= BATCH_SIZE) {
      // 保存到数据库（满10条）
      saveData(dictDoList);
      dictDoList.clear();
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    // 保存到数据库（剩余的不满10条的）
    saveData(dictDoList);
    log.info("所有数据解析完成！");
  }

  /** 保存数据 */
  private void saveData(List<DictDO> dictDoList) {
    dictDoMapper.saveBatch(dictDoList);
    log.info("{}条数据被储存到数据库成功！", dictDoList.size());
  }
}
