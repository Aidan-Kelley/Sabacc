package main;
import players.AlwaysTwoCards;
import players.ManyCards;
import players.Player;

public class Game {

    // variable declarations
    private SabaccDeck deck = new SabaccDeck();
    private Player[] players = {new ManyCards(deck), new AlwaysTwoCards(deck)};;

    public Game(int numberOfPlayers) {
        // deck = new SabaccDeck();
        // players = new Player[numberOfPlayers];
        // for(int i = 0; i < numberOfPlayers; i++) {
        //     players[i] = new ManyCards(deck);
        // }        
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
    


