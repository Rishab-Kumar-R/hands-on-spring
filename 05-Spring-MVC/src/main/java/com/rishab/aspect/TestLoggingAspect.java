package com.rishab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TestLoggingAspect {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.rishab.controller.*.*(..))")
    private void controllerPackage() {
    }

    @Pointcut("execution(* com.rishab.service.*.*(..))")
    private void servicePackage() {
    }

    @Pointcut("execution(* com.rishab.dao.*.*(..))")
    private void daoPackage() {
    }

    @Pointcut("controllerPackage() || servicePackage() || daoPackage()")
    private void appFlow() {
    }

    @Before("appFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info(">>> in @Before: calling method: " + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info(">>> argument: " + arg);
        }
    }

    @AfterReturning(pointcut = "appFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info(">>> in @AfterReturning: calling method: " + method);
        logger.info(">>> result: " + result);
    }
}
