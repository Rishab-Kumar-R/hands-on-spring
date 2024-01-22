package com.example._02_Managing_Java_Objects.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example._02_Managing_Java_Objects.game")
public class GamingAppLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {
            context.getBean(GameRunner.class).run();
        }
    }
}
