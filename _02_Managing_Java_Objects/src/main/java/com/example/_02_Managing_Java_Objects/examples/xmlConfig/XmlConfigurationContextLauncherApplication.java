package com.example._02_Managing_Java_Objects.examples.xmlConfig;

import com.example._02_Managing_Java_Objects.game.GameRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class XmlConfigurationContextLauncherApplication {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("pacmanGame"));
            context.getBean(GameRunner.class).run();
        }
    }
}
