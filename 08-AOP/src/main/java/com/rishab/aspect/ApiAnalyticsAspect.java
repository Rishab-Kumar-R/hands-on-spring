package com.rishab.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ApiAnalyticsAspect {
    @Before("com.rishab.aspect.PointcutExpressions.daoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println(">>> Performing API analytics.");
    }
}
