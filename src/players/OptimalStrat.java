package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class OptimalStrat extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    private static final int STAND_AT = -2;
    private static final int DISCARD_AT = 9;
    private static final int SWAP_AT = 1;

    public OptimalStrat(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        // When to stand
        if (Math.abs(handSum) < Math.abs(STAND_AT) || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return;
        }

        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard();
            if (Math.abs(sumIfSwap) < Math.abs(SWAP_AT) || sumIfSwap == SWAP_AT || sumIfSwap == -1*Math.abs(SWAP_AT)) {  
                swap(i);
                return;
            }
        }

        // when to discard
        if(Math.abs(handSum) > Math.abs(DISCARD_AT)) {
            int largest = 0; 
                for (int i = 0; i < hand.size(); i++) { 
                    if (hand.get(i) > hand.get(largest)) {
                        largest = i;
                    }
                }
                discard(largest);
                gain();
                return;
        }

        // else gain
        gain();
        return;
    }

    public Strategy getStrategy() {
        return Strategy.OPTIMAL;
    }
}
