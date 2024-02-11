package main;
import players.AlwaysTwoCards;
import players.ManyCards;
import players.Player;
import players.Strategy;

public class Game {

    // variable declarations
    private SabaccDeck deck = new SabaccDeck();
    private Player[] players;

    public Game(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++) {
            players[i] = choosePlayerType();
        }        
    }

    private Player choosePlayerType() {
        Strategy[] strategies = Strategy.values();
        Strategy choice = strategies[(int)(Math.random()*strategies.length)];
        choice.addPlay();
        switch(choice) {
            case TWO_CARDS:
                return new AlwaysTwoCards(deck);
            case MAX_CARDS:
                return new ManyCards(deck);
            default:
                return null;
        }
    } 

    // game loop
    public int runGame() {
        for(int i = 0; i < 3; i++) {
            for (Player p : players) {
                p.makeDecision();
            }
        }
        return calcWinner(players);        
    }

    // takes a list of hands and returns the index of the winning hand
    private int calcWinner(Player[] array) {
        int winner = 0;
        for (int i = 1; i < array.length; i++) {
            if (Calculations.betterHand(array[winner].getHand(), array[i].getHand()) == 1)
                winner = i;
        }
        array[winner].getStrategy().addWin();
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
    


