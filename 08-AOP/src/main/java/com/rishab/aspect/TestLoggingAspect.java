package com.rishab.aspect;

import com.rishab.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Aspect
@Component
@Order(3)
public class TestLoggingAspect {
    /*
     * * pointcut expression language
     *   execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)
     *
     * * parameter pattern wildcards
     * -> () matches a method that takes no arguments
     * -> (*) matches a method that takes one argument of any type
     * -> (..) matches a method that takes 0 or more arguments of any type
     */

    @Before("com.rishab.aspect.PointcutExpressions.daoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println(">>> Executing @Before advice on addAccount() method.");
        System.out.println("Method: " + joinPoint.getSignature());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("Argument: " + arg);
            if (arg instanceof Account account) {
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

    @AfterReturning(pointcut = "execution(* com.rishab.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        System.out.println(">>> Executing @AfterReturning advice on findAccounts() method.");
        System.out.println("Method: " + joinPoint.getSignature());
        System.out.println("Result: " + result);
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        Collections.unmodifiableList(result).forEach(account -> account.setName(account.getName().toUpperCase()));
    }

    @AfterThrowing(pointcut = "execution(* com.rishab.dao.AccountDAO.findAccounts(..))", throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
        System.out.println(">>> Executing @AfterThrowing advice on findAccounts() method.");
        System.out.println("Method: " + joinPoint.getSignature());
        System.out.println("Exception: " + exception);
    }

    @After("execution(* com.rishab.dao.AccountDAO.findAccounts(..))")
    public void afterFindAccountsAdvice(JoinPoint joinPoint) {
        System.out.println(">>> Executing @After advice on findAccounts() method.");
        System.out.println("Method: " + joinPoint.getSignature());
    }

    @Around("execution(* com.rishab.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(">>> Executing @Around advice on getFortune() method.");
        System.out.println("Method: " + proceedingJoinPoint.getSignature());

        long begin = System.currentTimeMillis();

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        }

        long end = System.currentTimeMillis();

        System.out.println("Duration: " + (end - begin) + "ms");
        return result;
    }
}
