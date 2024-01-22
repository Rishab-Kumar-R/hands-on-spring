package com.example._02_Managing_Java_Objects.examples.typesOfDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BusinessClass {

    // Field injection
    // @Autowired
    Dependency1 dependency1;

    // @Autowired
    Dependency2 dependency2;

    // Constructor injection (@Autowired is optional)
    @Autowired
    public BusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        super();
        System.out.println("Constructor injection from BusinessClass");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    // Setter injection
    // @Autowired
    // public void setDependency1(Dependency1 dependency1) {
    //     System.out.println("Setter injection for dependency1");
    //     this.dependency1 = dependency1;
    // }

    // @Autowired
    // public void setDependency2(Dependency2 dependency2) {
    //     System.out.println("Setter injection for dependency2");
    //     this.dependency2 = dependency2;
    // }

    @Override
    public String toString() {
        return "BusinessClass{" +
            "dependency1=" + dependency1 +
            ", dependency2=" + dependency2 +
            '}';
    }
}

@Component
class Dependency1 {
}

@Component
class Dependency2 {
}

@Configuration
// Scans the current package and all its sub-packages
@ComponentScan
public class DependencyInjectionLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependencyInjectionLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(BusinessClass.class));
        }
    }
}
