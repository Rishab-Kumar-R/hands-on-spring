package com.rishab.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutExpressions {
    @Pointcut("execution(* com.rishab.dao.*.*(..))")
    private void daoPackage() {
    }

    @Pointcut("execution(* com.rishab.dao.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* com.rishab.dao.*.set*(..))")
    public void setter() {
    }

    @Pointcut("daoPackage() && !(getter() || setter())")
    public void daoPackageNoGetterSetter() {
    }
}
