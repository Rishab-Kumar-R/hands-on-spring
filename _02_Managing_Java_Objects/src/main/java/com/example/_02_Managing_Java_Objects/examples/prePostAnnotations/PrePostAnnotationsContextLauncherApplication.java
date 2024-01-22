package com.example._02_Managing_Java_Objects.examples.prePostAnnotations;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass {
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency) {
        super();
        this.someDependency = someDependency;
        System.out.println("All dependencies are injected");
    }

    @PostConstruct
    public void initialize() {
        someDependency.getReady();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Some class is destroyed");
    }
}

@Component
class SomeDependency {
    public void getReady() {
        System.out.println("Some dependency is ready");
    }
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
