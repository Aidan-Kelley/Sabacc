package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class FourPlayerOptimal extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    private static final int STAND_AT = 1;
    private static final int DISCARD_AT = 2;
    private static final int SWAP_AT = 1;

    public FourPlayerOptimal(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        
        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard();
            boolean swapIsGoodEnough = Math.abs(sumIfSwap) < Math.abs(SWAP_AT) || sumIfSwap == SWAP_AT || sumIfSwap == -1*Math.abs(SWAP_AT);
            boolean improvesHand = Math.abs(sumIfSwap) < Math.abs(handSum) || sumIfSwap == -1*Math.abs(handSum);
            if (swapIsGoodEnough && improvesHand) {  
                swap(i);
                return;
            }
        }
        
        if (Math.abs(handSum) < Math.abs(STAND_AT) || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return;
        }

        if(Math.abs(handSum) >= Math.abs(DISCARD_AT)) {
            int largest = 0; 
                for (int i = 1; i < hand.size(); i++) { 
                    if (Math.abs(hand.get(i)) > Math.abs(hand.get(largest))) {
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
        return Strategy.FOUR_P_OPTIMAL;
    }
}
