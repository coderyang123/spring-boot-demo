package com.demo.valid.common.exception;

import com.demo.valid.common.enumeration.ErrorCodeEnum;
import com.demo.valid.domain.ResponseResult;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理类
 *
 * @author yueyang
 * @since 2023-05-07 22:12:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 拦截Multipart文件上传
   *
   * @param m Multipart文件上传异常
   */
  @ExceptionHandler(MultipartException.class)
  public ResponseResult<Void> multipartExceptionHandle(MultipartException m) {
    log.error("捕捉到Multipart文件上传异常：", m);
    return ResponseResult.failure(ErrorCodeEnum.FILE_UPLOAD_ERROR, m);
  }

  /**
   * 拦截业务异常
   *
   * @param b 业务异常
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseResult<Void> businessExceptionHandle(BusinessException b) {
    log.error("捕捉到业务异常：", b);
    return ResponseResult.failure(b.getCode(), b.getMessage());
  }

  /**
   * 拦截参数无效异常
   *
   * @param e 参数无效异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseResult<Void> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("捕捉到参数无效异常：", e);
    return ResponseResult.failure(
        ErrorCodeEnum.PARAM_IS_INVALID.getCode(),
        e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }

  /**
   * 拦截参数无效异常
   *
   * @param e 参数无效异常
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseResult<Void> constraintViolationException(ConstraintViolationException e) {
    log.error("捕捉到参数无效异常：", e);
    ConstraintViolation<?>[] array =
        e.getConstraintViolations().toArray(new ConstraintViolation[0]);
    String message = array[0].getConstraintDescriptor().getAttributes().get("message").toString();
    return ResponseResult.failure(ErrorCodeEnum.PARAM_IS_INVALID.getCode(), message);
  }

  /**
   * 拦截Servlet请求异常
   *
   * @param e Servlet请求异常
   */
  @ExceptionHandler({
    NoHandlerFoundException.class,
    HttpRequestMethodNotSupportedException.class,
    HttpMediaTypeNotSupportedException.class,
    MissingPathVariableException.class,
    MissingServletRequestParameterException.class,
    TypeMismatchException.class,
    HttpMessageNotReadableException.class,
    HttpMessageNotWritableException.class,
    HttpMediaTypeNotAcceptableException.class,
    ServletRequestBindingException.class,
    ConversionNotSupportedException.class,
    MissingServletRequestPartException.class,
    AsyncRequestTimeoutException.class
  })
  public ResponseResult<Void> servletException(Exception e) {
    log.error("捕捉到Servlet请求异常：", e);
    return ResponseResult.failure(ErrorCodeEnum.SERVLET_REQUEST_ERROR, e);
  }

  /**
   * 拦截运行时异常
   *
   * @param e 运行时异常
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseResult<Void> runTimeExceptionHandle(RuntimeException e) {
    log.error("捕捉到运行时异常：", e);
    return ResponseResult.failure(ErrorCodeEnum.SYSTEM_UNKNOWN_ERROR, e);
  }

  /**
   * 拦截系统级异常
   *
   * @param th 系统级异常
   */
  @ExceptionHandler(Throwable.class)
  public ResponseResult<Void> throwableHandle(Throwable th) {
    log.error("捕捉到系统级异常：", th);
    return ResponseResult.failure(ErrorCodeEnum.SYSTEM_UNKNOWN_ERROR, th);
  }
}
