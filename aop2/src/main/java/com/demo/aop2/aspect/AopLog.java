package com.demo.aop2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用APO切面记录请求日志信息
 *
 * @author yueyang
 * @since 2023-02-20 13:02:00
 */
@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopLog {

  /** 切入点 */
  @Pointcut("execution(public * com.demo.aop2.controller.*Controller.*(..))")
  public void log() {}

  @Bean
  public Advisor tranceAdvisor() {
    CustomizableTraceInterceptor traceInterceptor = new CustomizableTraceInterceptor();
    traceInterceptor.setEnterMessage(
        "进入方法，ClassName=$[targetClassName]：$[methodName]($[arguments])");
    traceInterceptor.setExitMessage("离开方法：$[methodName]()：$[returnValue]");
    traceInterceptor.setUseDynamicLogger(true);

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("com.demo.aop2.aspect.AopLog.log()");

    return new DefaultPointcutAdvisor(pointcut, traceInterceptor);
  }
}
