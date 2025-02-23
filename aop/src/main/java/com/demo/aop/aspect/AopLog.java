package com.demo.aop.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.demo.aop.entity.Log;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.UserAgent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 使用APO切面记录请求日志信息
 *
 * @author yueyang
 * @since 2022-03-01 11:02:00
 */
@Aspect
@Component
@Slf4j
public class AopLog {
  private static final String UNKNOWN = "unknown";

  /** 切入点 */
  @Pointcut("execution(public * com.demo.aop.controller.*Controller.*(..))")
  public void log() {}

  /**
   * 环绕操作
   *
   * @param point 切入点
   * @return 原方法返回值
   * @throws Throwable 异常信息
   */
  @Around("log()")
  public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

    // 开始打印请求日志
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

    // 打印请求相关参数
    long startTime = System.currentTimeMillis();
    Object result = point.proceed();
    String header = request.getHeader("User-Agent");
    UserAgent userAgent = UserAgent.parseUserAgentString(header);

    Log l =
        Log.builder()
            .threadId(Long.toString(Thread.currentThread().getId()))
            .threadName(Thread.currentThread().getName())
            .ip(getIp(request))
            .url(request.getRequestURL().toString())
            .classMethod(
                String.format(
                    "%s.%s",
                    point.getSignature().getDeclaringTypeName(), point.getSignature().getName()))
            .httpMethod(request.getMethod())
            .requestParams(getNameAndValue(point))
            .result(result)
            .timeCost(System.currentTimeMillis() - startTime)
            .userAgent(header)
            .browser(userAgent.getBrowser().toString())
            .os(userAgent.getOperatingSystem().toString())
            .build();
    log.info("Request Log Info: {}", JSONUtil.toJsonStr(l));

    return result;
  }

  /** 获取ip地址 */
  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    String comma = ",";
    String localhost = "127.0.0.1";
    if (ip.contains(comma)) {
      ip = ip.split(",")[0];
    }
    if (localhost.equals(ip)) {
      // 获取客户端真正的ip地址
      try {
        ip = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        log.error("获取客户端请求IP错误", e);
      }
    }

    return ip;
  }

  /**
   * 获取方法参数名和参数值
   *
   * @param joinPoint 切点
   * @return 方法参数名和参数值
   */
  private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    String[] names = methodSignature.getParameterNames();
    Object[] args = joinPoint.getArgs();

    if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
      return Collections.emptyMap();
    }
    if (names.length != args.length) {
      log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
      return Collections.emptyMap();
    }
    Map<String, Object> map = Maps.newHashMapWithExpectedSize(names.length);
    for (int i = 0; i < names.length; i++) {
      map.put(names[i], args[i]);
    }

    return map;
  }
}
