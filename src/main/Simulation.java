package main;

import java.io.FileWriter;
import java.io.IOException;
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
}
