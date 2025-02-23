package com.demo.easyexcel.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式转换器
 *
 * @author yueyang
 * @since 2021-04-03 16:55:00
 */
public class LocalDateConverter implements Converter<LocalDate> {
  @Override
  public Class<LocalDate> supportJavaTypeKey() {
    return LocalDate.class;
  }

  @Override
  public CellDataTypeEnum supportExcelTypeKey() {
    return CellDataTypeEnum.STRING;
  }

  @Override
  public LocalDate convertToJavaData(
      CellData cellData,
      ExcelContentProperty excelContentProperty,
      GlobalConfiguration globalConfiguration) {
    return LocalDate.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  @Override
  public CellData<LocalDate> convertToExcelData(
      LocalDate localDate,
      ExcelContentProperty excelContentProperty,
      GlobalConfiguration globalConfiguration) {
    return new CellData<>(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
  }
}
