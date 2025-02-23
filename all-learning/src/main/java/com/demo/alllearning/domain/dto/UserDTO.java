package com.demo.alllearning.domain.dto;

import com.demo.alllearning.common.validate.InsertValidationGroup;
import com.demo.alllearning.common.validate.UpdateValidationGroup;
import java.io.Serializable;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户传输实体类
 *
 * @author yueyang
 * @since 2021-03-07 22:34:00
 */
@Data
public class UserDTO implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = -8768274390538463497L;

  /** 用户名 */
  @NotBlank(
      message = "用户名不能为空！",
      groups = {InsertValidationGroup.class})
  private String username;

  /** 密码 */
  @NotBlank(
      message = "密码不能为空！",
      groups = {InsertValidationGroup.class})
  @Length(min = 6, max = 18, message = "密码长度6~18位！")
  private String password;

  /** 年龄 */
  @NotNull(
      message = "年龄不能为空！",
      groups = {InsertValidationGroup.class})
  @Max(value = 60, message = "年龄不能大于150岁！")
  @Min(value = 18, message = "年龄不能小于18岁！")
  private Integer age;

  /** 邮箱 */
  @NotBlank(
      message = "邮箱不能为空！",
      groups = {InsertValidationGroup.class})
  @Email(message = "无效邮箱！")
  private String email;

  /** 手机号 */
  @NotBlank(
      message = "手机号不能为空！",
      groups = {InsertValidationGroup.class})
  private String phone;

  /** 版本号 */
  @NotNull(
      message = "版本号不能为空！",
      groups = {UpdateValidationGroup.class})
  private Long version;
}
