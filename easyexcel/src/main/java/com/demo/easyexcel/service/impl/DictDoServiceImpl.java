package com.demo.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.easyexcel.common.listener.DictImportListener;
import com.demo.easyexcel.domain.dto.DictExportDTO;
import com.demo.easyexcel.domain.entity.DictDO;
import com.demo.easyexcel.mapper.DictDoMapper;
import com.demo.easyexcel.service.DictDoService;
import com.demo.easyexcel.service.FileService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典管理
 *
 * @author yueyang
 * @since 2021-04-04 13:32:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DictDoServiceImpl extends ServiceImpl<DictDoMapper, DictDO> implements DictDoService {
  private final FileService fileService;

  /**
   * 导入Excel
   *
   * @param inputStream inputStream
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public void importExcel(InputStream inputStream) {
    // 这里调用监听器里的invoke方法将数据解析和入库
    EasyExcel.read(inputStream, DictDO.class, new DictImportListener(baseMapper)).sheet().doRead();
    log.info("Excel文件导入成功！");
  }

  /**
   * 获取字典集合
   *
   * @return 字典集合
   */
  @Override
  public List<DictDO> listDictDo() {
    return baseMapper.selectList(null);
  }

  /** 异步导出Excel */
  @Override
  @Async("exportThreadPoolExecutor")
  public void asyncExportExcel() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    log.info("开始导出数据");
    ExcelWriter excelWriter = EasyExcelFactory.write(outputStream, DictDO.class).build();
    int current = 0;
    Page<DictDO> dictDOPage;
    do {
      log.info("开始导出第[{}]页数据", current + 1);
      Page<DictDO> page = new Page<>(++current, 2);
      dictDOPage = baseMapper.selectPage(page, null);

      // 数据转换
      List<DictExportDTO> exportDTOList =
          Optional.ofNullable(dictDOPage.getRecords()).stream()
              .flatMap(Collection::stream)
              .map(
                  dictDO -> {
                    DictExportDTO dictExportDTO = new DictExportDTO();
                    BeanUtils.copyProperties(dictDO, dictExportDTO);
                    return dictExportDTO;
                  })
              .collect(Collectors.toList());

      // 模拟查询耗时
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      // 将数据写入到不同的sheet页中
      WriteSheet writeSheet =
          EasyExcelFactory.writerSheet(current, String.format("第%s页", current)).build();
      excelWriter.write(exportDTOList, writeSheet);
    } while (dictDOPage.getPages() > current);
    excelWriter.finish();

    // 将Excel文件写入服务器
    fileService.upload(new ByteArrayInputStream(outputStream.toByteArray()), "temp_dict.xlsx");
    log.info("数据导出完成");
  }
}
