package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import players.Strategy;

public class Simulation {

    private Game game;
    private int winner;
    private int trials;
    private int numberOfPlayers;
    
    public Simulation(int trials, int numberOfPlayers) {
        this.trials = trials;
        this.numberOfPlayers = numberOfPlayers;
        deleteGamesFile();
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
        }
        try {
            FileWriter writer = new FileWriter("src\\logs\\results.txt", true);
            writer.write(String.format("Trials: %d%nPlayers: %d%n", trials, numberOfPlayers));
            for(Strategy s : Strategy.values()) {
                writer.write(String.format("%s: %%%.2f%n", s,s .getWins()/(double)s.getGamesPlayed()*100));
            }
            writer.write("-----------------------------------------------------------------------\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("Unable to write to file.");
        }
    }

    private void deleteGamesFile() {
        File file = new File("src\\logs\\games.txt");
        file.delete();
    }
}
