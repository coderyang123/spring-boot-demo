package com.demo.alllearning.domain.dto;

import com.demo.alllearning.common.validate.InsertValidationGroup;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户数据查询实体
 *
 * @author yueyang
 * @since 2021-03-08 12:59:00
 */
@Data
public class UserQueryDTO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -3911785456517956539L;

  /** 用户名 */
  @NotBlank(
      message = "用户名不能为空！",
      groups = {InsertValidationGroup.class})
  private String username;
}
