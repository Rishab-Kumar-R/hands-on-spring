package com.example._02_Managing_Java_Objects.examples.beanScopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass {
}

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeClass {
}

@Configuration
@ComponentScan
public class BeanScopeLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopeLauncherApplication.class)) {
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }
    }
}
