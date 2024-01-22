package com.example._02_Managing_Java_Objects.examples.lazyInitialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class A {
}

@Component
@Lazy
class B {
    private A a;

    public B(A a) {
        System.out.println("Some initialization logic");
        this.a = a;
    }

    public void doSomething() {
        System.out.println("Doing something");
    }
}

@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)) {
            System.out.println("Context created");
            context.getBean(B.class).doSomething();
        }
    }
}
