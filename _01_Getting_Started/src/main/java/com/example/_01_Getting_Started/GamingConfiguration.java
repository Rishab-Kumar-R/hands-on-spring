package com.example._01_Getting_Started;

import com.example._01_Getting_Started.game.GameRunner;
import com.example._01_Getting_Started.game.GamingConsole;
import com.example._01_Getting_Started.game.PacmanGame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {

    @Bean
    public GamingConsole game() {
        return new PacmanGame();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        return new GameRunner(game);
    }

}
