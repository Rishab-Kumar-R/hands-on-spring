package com.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/*
 * Terminologies:
 * Compile-time
 *   1. Advice: What action is taken and when it should be applied (e.g. before, after, around, after-returning, after-throwing)
 *   2. Pointcut: A predicate that matches join points. A join point is a point in the execution of the application where an aspect can be plugged in. (e.g. all methods in a package, all methods annotated with a specific annotation)
 *   3. Aspect: A module which has a set of APIs providing cross-cutting requirements. For example, a logging module would be called AOP aspect for logging. (combination of advice and pointcut)
 *   4. Weaver: The process of linking aspects with other application types or objects to create an advised object. This can be done at compile time, load time, or at runtime.
 * 
 * Runtime
 *   1. Join point: A point during the execution of a program, such as the execution of a method or the handling of an exception.   
 */

@Configuration
@Aspect
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // Pointcut to match all methods in the business package
    // syntax: execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?) ? means optional
    @Before("com.example.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
    public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
        logger.info("Before Aspect: {} is called with arguments {}", joinPoint, joinPoint.getArgs());
        // Before Aspect - Method called - execution(int com.example.aop.business.BusinessService1.calculateMax()) - this is the output if package is com.example.aop.business
        // Before Aspect - Method called - execution(int[] com.example.aop.data.DataService1.retrieveData()) - this is the output if package is com.example.aop.data

        // this is the output if package is com.example.aop.*.*.* (all packages)
        // Before Aspect - Method called - execution(int com.example.aop.business.BusinessService1.calculateMax())
        // Before Aspect - Method called - execution(int[] com.example.aop.data.DataService1.retrieveData())
    }

    @After("com.example.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
    public void logMethodCallAfterExecution(JoinPoint joinPoint) {
        logger.info("After Aspect: {} has executed", joinPoint);
    }

    @AfterThrowing(pointcut = "com.example.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()", throwing = "exception")
    public void logMethodCallAfterThrowingException(JoinPoint joinPoint, Exception exception) {
        logger.error("After Throwing Aspect: {} has thrown an exception {}", joinPoint, exception.getMessage());
    }

    @AfterReturning(pointcut = "com.example.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()", returning = "result")
    public void logMethodCallAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After Returning Aspect: {} has returned {}", joinPoint, result);
    }
}
