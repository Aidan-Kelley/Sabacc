import java.util.ArrayList;
// used for testing method
// may change later
public class Tester {
    
    public Tester(long tests) {
        for (int i = 0; i < tests; i++) {
            SabaccDeck deck = new SabaccDeck();
            ArrayList<Integer> hand0 = new ArrayList<Integer>();
            ArrayList<Integer> hand1 = new ArrayList<Integer>();
            
            for (int j = 0; j<(int)(Math.random()*4)+2; j++)
                hand0.add(deck.drawCard());
            for (int j = 0; j<(int)(Math.random()*4)+2; j++)
                hand1.add(deck.drawCard());
            System.out.println("Player 1: " + hand0.toString());
            System.out.println("Player 2: " + hand1.toString());
            System.out.println(Calculations.betterHand(hand0, hand1)+1);
        }
    }
}
