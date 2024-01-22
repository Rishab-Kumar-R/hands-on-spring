package com.example._02_Managing_Java_Objects.game;

import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole {
    @Override
    public void up() {
        System.out.println("PacmanGame: Up");
    }

    @Override
    public void down() {
        System.out.println("PacmanGame: Down");
    }

    @Override
    public void left() {
        System.out.println("PacmanGame: Left");
    }

    @Override
    public void right() {
        System.out.println("PacmanGame: Right");
    }
}
