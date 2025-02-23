package com.demo.easyexcel.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.demo.easyexcel.common.converter.LocalDateConverter;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生导出实体类
 *
 * @author yueyang
 * @since 2021-04-03 16:33:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExportDTO {

  /** 姓名 */
  @ExcelProperty("姓名")
  private String name;

  /** 出生日期 */
  @ExcelProperty(value = "出生日期", converter = LocalDateConverter.class)
  private LocalDate birthday;

  /** 工资 */
  @ExcelProperty("工资")
  private Double salary;
}
