package com.example._01_Getting_Started.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class CreatingBeanApplication {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class)) {
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));

            System.out.println(context.getBean("person"));
            System.out.println(context.getBean("personAddress"));
            System.out.println(context.getBean(Address.class)); // Same as above
            System.out.println(context.getBean("personWithNameAgeAndAddress"));
            System.out.println(context.getBean("personWithParameters"));

            System.out.println(context.getBean("personWithPrimaryAddress"));
            System.out.println(context.getBean("personWithQualifierAddress"));

            System.out.println("--------------------");
            // lists all the beans created by Spring
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }
    }
}
