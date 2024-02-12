package players;

import java.util.ArrayList;
import main.Calculations;
import main.SabaccDeck;

public class VariablePlayer extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    private int STAND_AT = -1;
    private int DISCARD_AT = 10;
    private int SWAP_AT = 0;

    public VariablePlayer(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        // When to stand
        if (Math.abs(handSum) < STAND_AT || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return;
        }

        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard()
            if (Math.abs(sumIfSwap) < STAND_AT || sumIfSwap == STAND_AT || sumIfSwap == -1*Math.abs(STAND_AT)) {  
                swap(i);
                return;
            }
        }

        // when to discard
        if(handSum > DISCARD_AT) {
        int largest = 0; 
            for (int i = 0; i < hand.size(); i++) { 
                if (hand.get(i) > hand.get(largest)) {
                    largest = i;
                }
            }
            swap(largest);
            return;
        }

        // else gain
        gain();
        return;
    }

    public Strategy getStrategy() {
        return Strategy.VARIABLE;
    }
}
