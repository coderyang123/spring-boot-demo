package com.demo.valid.common.exception;

import com.demo.valid.common.enumeration.ErrorCodeEnum;
import lombok.Getter;

/**
 * 系统业务异常类
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
public class BusinessException extends RuntimeException {

  /** 错误编码 */
  @Getter private final String code;

  /**
   * 根据枚举构造业务类异常
   *
   * @param errorCodeEnum 错误编码枚举
   */
  public BusinessException(ErrorCodeEnum errorCodeEnum) {
    super(errorCodeEnum.getMessage());
    this.code = errorCodeEnum.getCode();
  }

  /**
   * 自定义消息体构造业务类异常
   *
   * @param errorCodeEnum 错误编码枚举
   * @param message 错误信息
   */
  public BusinessException(ErrorCodeEnum errorCodeEnum, String message) {
    super(message);
    this.code = errorCodeEnum.getCode();
  }

  /**
   * 根据系统级异常构造业务类异常
   *
   * @param errorCodeEnum 错误编码枚举
   * @param cause 系统级异常
   */
  public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
    super(cause);
    this.code = errorCodeEnum.getCode();
  }
}
