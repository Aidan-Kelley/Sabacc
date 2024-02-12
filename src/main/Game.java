package main;

import players.Player;
import players.VariablePlayer;

public class Game {

    // variable declarations
    private final int AMOUNT_OF_ROUNDS = 3;
    private SabaccDeck deck = new SabaccDeck();
    private Player[] players;
    public static int average = 0;

    public Game(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++) {
            players[i] = new VariablePlayer(deck);
        }        
    }

    // game loop
    public int runGame() {
        for(int i = 0; i < AMOUNT_OF_ROUNDS; i++) {
            for (Player p : players) {
                p.makeDecision();
            }
            if((int)(Math.random()*6) == 1)
                for (Player p : players)
                p.newHand(p.handSize()); //discard hands if doubles
        }
        return calcWinner(players);        
    }

    // takes a list of hands and returns the index of the winning hand
    private int calcWinner(Player[] array) {
        int winner = 0;
        final int OFFSET = Constants.LOWER_BOUND*-1;
        Simulation.attempts[array[0].getVariable()+OFFSET]++;
        for (int i = 1; i < array.length; i++) {
            Simulation.attempts[array[i].getVariable()+OFFSET]++;
            if (Calculations.betterHand(array[winner].getHand(), array[i].getHand()) == 1)
                winner = i;
        }
        Simulation.wins[array[winner].getVariable()+OFFSET]++;
        return winner;
    }

    public String toString() {
        String output = "";
        for (Player p : players) {
            output += p + ": " + Calculations.handSum(p.getHand())+ "\n";
        }
        return output;
    }
}
    


