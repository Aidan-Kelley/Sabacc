package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import players.Strategy;
import players.VariablePlayer;

public class Simulation {

    private Game game;
    private int winner;
    private int trials;
    private int numberOfPlayers;
    public static int[] wins = new int[Constants.UPPER_BOUND - Constants.LOWER_BOUND];
    public static int[] attempts = new int[Constants.UPPER_BOUND - Constants.LOWER_BOUND];
    
    public Simulation(int trials, int numberOfPlayers) {
        this.trials = trials;
        this.numberOfPlayers = numberOfPlayers;
        deleteGamesFile();
        for(int j = 0; j < 100; j++) {
            for (int i = 0; i < trials/100; i++) {
                game = new Game(numberOfPlayers);
                winner = game.runGame();
                // logGame(game.toString(), winner);
            }
            System.out.println("%" + j);
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
        try {
            FileWriter writer = new FileWriter("src\\logs\\results.txt", true);
            writer.write(String.format("Testing: %s%nTrials: %d%nPlayers: %d%n", VariablePlayer.getVariableName(),trials, numberOfPlayers));
            for (int i = 0; i < wins.length; i++) {
                writer.write(i + Constants.LOWER_BOUND + ": " + (double)wins[i]/attempts[i] + "\n");
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
