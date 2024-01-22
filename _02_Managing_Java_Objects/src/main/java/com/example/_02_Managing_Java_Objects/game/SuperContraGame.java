package com.example._02_Managing_Java_Objects.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("superContraGameQualifier")
public class SuperContraGame implements GamingConsole {
    @Override
    public void up() {
        System.out.println("SuperContraGame: Jump");
    }

    @Override
    public void down() {
        System.out.println("SuperContraGame: Down");
    }

    @Override
    public void left() {
        System.out.println("SuperContraGame: Left");
    }

    @Override
    public void right() {
        System.out.println("SuperContraGame: Right");
    }
}
