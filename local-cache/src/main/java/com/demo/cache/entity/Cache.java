package com.demo.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 缓存实体类
 *
 * @author yueyang
 * @since 2022-06-23 13:44:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cache {

  /** 缓存对象 */
  private Object cacheValue;

  /** 缓存过期时间 */
  private Long ttlTime;
}
