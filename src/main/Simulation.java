package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import players.Strategy;

public class Simulation {

    private Game game;
    private int winner;
    private int trials;

    public Simulation(int trials, int numberOfPlayers) {
        File file = new File("src\\logs\\games.txt");
        file.delete();
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            game = new Game(numberOfPlayers);
            winner = game.runGame();
            logGame(game.toString(), winner);
        }
        logResults();
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

    private void logResults() {
        for(Strategy s : Strategy.values()) {
            System.out.printf("%s: %%%.2f%n",s,s.getWins()/(double)s.getGamesPlayed()*100);
            // TODO: log result in results,txt with detials
        }
    }
}
