package com.demo.xstream.domain;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 笔记实体类
 *
 * @author yueyang
 * @since 2022-06-08 09:39:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {

  @XStreamImplicit(itemFieldName = "list")
  private List<Detail> details;
}
