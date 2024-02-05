package com.example.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
    @Pointcut("execution(* com.example.aop.*.*.*(..))")
    public void businessAndDataPackageConfig() {
    }

    @Pointcut("execution(* com.example.aop.business.*.*(..))")
    public void businessPackageConfig() {
    }

    @Pointcut("execution(* com.example.aop.data.*.*(..))")
    public void dataPackageConfig() {
    }

    @Pointcut("bean(*Service*)") // bean(*Service*) matches any bean with a name that ends with Service
    public void allPackageConfigUsingBean() {
    }

    @Pointcut("@annotation(com.example.aop.annotations.TrackTime)")
    public void trackTimeAnnotation() {
    }
}
