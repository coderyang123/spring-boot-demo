package com.demo.valid.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误编码枚举
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

  /** 成功状态码 */
  SUCCESS("1000", "登录成功"),

  /** 失败状态码-参数错误 */
  PARAM_IS_INVALID("1001", "参数无效"),
  PARAM_IS_BLANK("1002", "参数为空"),
  PARAM_TYPE_BIND_ERROR("1003", "参数类型错误"),
  PARAM_NOT_COMPLETE("1004", "参数缺失"),

  /** 失败状态码-用户错误 */
  USER_NOT_LOGGED_IN("2001", "用户未登录，访问的路径需要验证，请登录"),
  USER_LOGIN_ERROR("2002", "账号不存在或密码错误"),
  USER_ACCOUNT_FORBIDDEN("2003", "账号已被禁用"),
  USER_NOT_EXIST("2004", "用户不存在"),
  USER_HAS_EXISTED("2005", "用户已存在"),
  USER_NO_PERMISSION("2006", "用户无权限访问"),
  USER_REGISTER_FAIL("2007", "用户注册失败"),
  USER_UPDATE_FAIL("2008", "用户更新失败"),
  USER_DELETE_FAIL("2009", "用户删除失败"),

  /** 失败状态码-token错误 */
  TOKEN_NOT_EXIST("3001", "请求头未携带token信息，无法认证"),
  TOKEN_IS_INVALID("3002", "token错误或者token已过期"),

  /** 失败状态码-系统错误 */
  SYSTEM_OPERATION_ERROR("5001", "系统操作错误"),
  SYSTEM_UNKNOWN_ERROR("5002", "系统未知错误"),
  SERVLET_REQUEST_ERROR("5003", "Servlet请求错误"),

  /** 失败状态码-文件操作错误 */
  FILE_UPLOAD_ERROR("6001", "文件上传失败"),
  FILE_SAVE_FAIL("6002", "文件信息保存失败"),

  /** 失败状态码-Elasticsearch查询错误 */
  ELASTICSEARCH_INDEX_DOES_NOT_EXIST("7001", "索引不存在");

  /** 错误码 */
  private final String code;

  /** 错误信息 */
  private final String message;
}
