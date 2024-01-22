package com.example._02_Managing_Java_Objects.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    private GamingConsole game;

    public GameRunner(@Qualifier("superContraGameQualifier") GamingConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running the game..." + game.getClass().getSimpleName());
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
