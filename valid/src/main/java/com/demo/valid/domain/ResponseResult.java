package com.demo.valid.domain;

import com.demo.valid.common.enumeration.ErrorCodeEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * 通用返回结果模型
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@Data
public class ResponseResult<T> implements Serializable {

  /** 序列化ID */
  private static final long serialVersionUID = 5708362261839734008L;

  /** 是否成功 */
  private Boolean success;

  /** 返回码 */
  private String code;

  /** 描述信息 */
  private String message;

  /** 返回结果 */
  private T result;

  /**
   * 请求成功
   *
   * @return 通用返回结果模型
   */
  public static ResponseResult<Void> success() {
    ResponseResult<Void> responseResult = new ResponseResult<>();
    responseResult.setSuccess(Boolean.TRUE);
    responseResult.setCode("1000");
    responseResult.setMessage("请求成功！");
    return responseResult;
  }

  /**
   * 请求成功
   *
   * @param result 返回结果
   * @param <T> 返回结果类型
   * @return 通用返回结果模型
   */
  public static <T> ResponseResult<T> success(T result) {
    ResponseResult<T> responseResult = new ResponseResult<>();
    responseResult.setSuccess(Boolean.TRUE);
    responseResult.setCode("1000");
    responseResult.setMessage("请求成功！");
    responseResult.setResult(result);
    return responseResult;
  }

  /**
   * 请求失败
   *
   * @param code 状态码
   * @param message 描述信息
   * @return 通用返回结果模型
   */
  public static ResponseResult<Void> failure(String code, String message) {
    ResponseResult<Void> responseResult = new ResponseResult<>();
    responseResult.setSuccess(Boolean.FALSE);
    responseResult.setCode(code);
    responseResult.setMessage(message);
    return responseResult;
  }

  /**
   * 请求失败
   *
   * @param errorCodeEnum 错误编码枚举
   * @return 通用返回结果模型
   */
  public static ResponseResult<Void> failure(ErrorCodeEnum errorCodeEnum) {
    ResponseResult<Void> responseResult = new ResponseResult<>();
    responseResult.setSuccess(Boolean.FALSE);
    responseResult.setCode(errorCodeEnum.getCode());
    responseResult.setMessage(errorCodeEnum.getMessage());
    return responseResult;
  }

  /**
   * 请求失败
   *
   * @param errorCodeEnum 错误编码枚举
   * @param th 异常
   * @return 通用返回结果模型
   */
  public static ResponseResult<Void> failure(ErrorCodeEnum errorCodeEnum, Throwable th) {
    ResponseResult<Void> responseResult = new ResponseResult<>();
    responseResult.setSuccess(Boolean.FALSE);
    responseResult.setCode(errorCodeEnum.getCode());
    responseResult.setMessage(th.getMessage());
    return responseResult;
  }
}
