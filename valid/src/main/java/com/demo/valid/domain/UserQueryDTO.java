package com.demo.valid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户数据查询传输实体
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@Data
public class UserQueryDTO {

  /** 用户名 */
  @NotBlank(message = "用户名不能为空！")
  private String username;

  /** 密码 */
  @NotBlank(message = "密码不能为空！")
  @Length(min = 6, max = 18, message = "密码长度6~18位！")
  private String password;

  /** 年龄 */
  @NotNull(message = "年龄不能为空！")
  @Max(value = 60, message = "年龄不能大于150岁！")
  @Min(value = 18, message = "年龄不能小于18岁！")
  private Integer age;

  /** 邮箱 */
  @NotBlank(message = "邮箱不能为空！")
  @Email(message = "无效邮箱！")
  private String email;

  /** 手机号 */
  @NotBlank(message = "手机号不能为空！")
  private String phone;

  @Null(message = "nullParam必须为空！")
  private String nullParam;

  @AssertTrue(message = "trueParam必须为true！")
  private Boolean trueParam;

  @AssertFalse(message = "booleanParam必须为false！")
  private Boolean falseParam;

  @DecimalMax(value = "100", message = "decimalMaxParam不能大于100！")
  private Integer decimalMaxParam;

  @DecimalMin(value = "10", message = "decimalMinParam不能小于10！")
  private Integer decimalMinParam;

  @Size(min = 2, max = 5, message = "sizeParam长度必须在2~5之间！")
  private String sizeParam;

  @Digits(integer = 3, fraction = 2, message = "digitsParam整数位不能超过3位，小数位不能超过2位！")
  private Double digitsParam;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @NotNull(message = "pastParam不能为空！")
  @Past(message = "pastParam必须是一个过去的时间！")
  private LocalDateTime pastTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @NotNull(message = "futureParam不能为空！")
  @Future(message = "futureParam必须是一个未来的时间！")
  private LocalDateTime futureTime;

  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "patternParam必须是字母或数字！")
  private String patternParam;
}
