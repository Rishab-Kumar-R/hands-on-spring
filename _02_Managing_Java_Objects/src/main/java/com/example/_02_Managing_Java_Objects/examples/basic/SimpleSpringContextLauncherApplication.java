package com.example._02_Managing_Java_Objects.examples.basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)) {

            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }
    }
}
