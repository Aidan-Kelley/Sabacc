package main;

import java.io.FileWriter;
import java.io.IOException;

public class Simulation {

    private Game game;
    private int winner;

    public Simulation(int trials) {

        for (int i = 0; i < trials; i++) {
            game = new Game(2);
            winner = game.runGame();
            logGame(game.toString(), winner);
        }
    }

    

    private void logGame(String hands, int winner) {
        try {
            FileWriter writer = new FileWriter("src\\logs\\games.txt", true);
            writer.write(hands);
            writer.write("Winner: " + Integer.toString(winner));
            writer.write("\n\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }
}
