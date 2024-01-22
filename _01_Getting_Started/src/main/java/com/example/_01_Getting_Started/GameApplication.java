package com.example._01_Getting_Started;

import com.example._01_Getting_Started.game.GameRunner;
import com.example._01_Getting_Started.game.MarioGame;
import com.example._01_Getting_Started.game.PacmanGame;
import com.example._01_Getting_Started.game.SuperContraGame;

public class GameApplication {
    public static void main(String[] args) {

        // MarioGame game = new MarioGame();
        // SuperContraGame game = new SuperContraGame();
        PacmanGame game = new PacmanGame();

        GameRunner gameRunner = new GameRunner(game);
        gameRunner.run();

    }
}
