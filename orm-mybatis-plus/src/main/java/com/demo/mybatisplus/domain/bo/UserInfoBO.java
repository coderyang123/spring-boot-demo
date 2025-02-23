package com.demo.mybatisplus.domain.bo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户业务实体类
 *
 * @author yueyang
 * @since 2022-04-05 14:55:00
 */
@Data
public class UserInfoBO {
  /** 主键ID */
  private Long id;

  /** 创建时间 */
  private LocalDateTime createTime;

  /** 商品名 */
  private String goodsName;
}
