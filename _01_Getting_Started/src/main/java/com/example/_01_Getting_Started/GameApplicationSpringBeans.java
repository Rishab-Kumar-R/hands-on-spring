package com.example._01_Getting_Started;

import com.example._01_Getting_Started.game.GameRunner;
import com.example._01_Getting_Started.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GameApplicationSpringBeans {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            context.getBean(GamingConsole.class).up();

            context.getBean(GameRunner.class).run();
        }

    }
}
