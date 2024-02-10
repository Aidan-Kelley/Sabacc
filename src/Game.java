

public class Game {

    // variable declarations
    private SabaccDeck deck;
    private AlwaysTwoCards[] players;

    public Game(int numberOfPlayers) {
        deck = new SabaccDeck();
        players = new AlwaysTwoCards[numberOfPlayers]; // creates an array of players 
        for(int i = 0; i < numberOfPlayers; i++) {
            players[i] = new AlwaysTwoCards(deck);
        }        
    }

    // game loop
    public int runGame() {
        for(int i = 0; i < 3; i++) {
            for (AlwaysTwoCards p : players) {
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
        for (AlwaysTwoCards p : players) {
            output += p + ": " + Calculations.handSum(p.getHand())+ "\n";
        }
        return output;
    }
}
    


