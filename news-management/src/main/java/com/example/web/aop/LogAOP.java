package com.example.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAOP {

    private static final Logger log = LoggerFactory.getLogger(LogAOP.class);

    @Pointcut("execution(* com.example.web..*(..))")
    public void pointcut() {}

    /**
     * 显示日志，哪个方法被调用了
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("{}运行了.", joinPoint.getSignature().getName());
    }

    /**
     * 显示日志，哪个方法调用完毕
     */
    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("{}运行返回.", joinPoint.getSignature().getName());
    }

    /**
     * 显示日志，哪个方法抛出错误
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("{}运行抛出异常.", joinPoint.getSignature().getName());
    }
}
