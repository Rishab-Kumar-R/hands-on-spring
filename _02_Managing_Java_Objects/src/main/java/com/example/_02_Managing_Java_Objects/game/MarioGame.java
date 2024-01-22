package com.example._02_Managing_Java_Objects.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MarioGame implements GamingConsole {
    @Override
    public void up() {
        System.out.println("MarioGame: Up");
    }

    @Override
    public void down() {
        System.out.println("MarioGame: Down");
    }

    @Override
    public void left() {
        System.out.println("MarioGame: Left");
    }

    @Override
    public void right() {
        System.out.println("MarioGame: Right");
    }
}
