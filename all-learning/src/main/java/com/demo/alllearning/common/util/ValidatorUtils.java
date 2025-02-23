package com.demo.alllearning.common.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.util.CollectionUtils;

/**
 * 参数校验工具类
 *
 * @author yueyang
 * @since 2021-03-09 20:52:00
 */
public class ValidatorUtils {

  /** 全局校验器 */
  private static final Validator VALIDATOR =
      Validation.buildDefaultValidatorFactory().getValidator();

  /**
   * 参数校验
   *
   * @param object 校验对象
   * @param groups 校验分组
   * @param <T> 校验对象类型
   */
  public static <T> void validate(T object, Class<?>... groups) {
    Set<ConstraintViolation<T>> validate = VALIDATOR.validate(object, groups);
    // 如果校验结果不为空，则将异常信息收集
    if (!CollectionUtils.isEmpty(validate)) {
      StringBuilder exceptionMessage = new StringBuilder();
      validate.forEach(
          constraintViolation -> exceptionMessage.append(constraintViolation.getMessage()));
      throw new RuntimeException(exceptionMessage.toString());
    }
  }
}
