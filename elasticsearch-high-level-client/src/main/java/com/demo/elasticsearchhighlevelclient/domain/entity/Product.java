package com.demo.elasticsearchhighlevelclient.domain.entity;

import lombok.Data;

/**
 * 商品实体类
 *
 * @author yueyang
 * @since 2022-01-25 10:08:00
 */
@Data
public class Product {

  /** ID */
  private Integer id;

  /** 标题 */
  private String title;

  /** 价格 */
  private Double price;

  /** 描述 */
  private String description;
}
