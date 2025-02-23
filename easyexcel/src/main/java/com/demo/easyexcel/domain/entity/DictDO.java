package com.demo.easyexcel.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 字典实体类
 *
 * @author yueyang
 * @since 2021-04-04 12:58:00
 */
@Data
@TableName("dict")
public class DictDO {
  @ExcelProperty("id")
  private Long id;

  @ExcelProperty("上级id")
  private Long parentId;

  @ExcelProperty("名称")
  private String name;

  @ExcelProperty("值")
  private Integer value;

  @ExcelProperty("编码")
  private String dictCode;
}
