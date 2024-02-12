package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class BestPlayerV2 extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    public BestPlayerV2(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        // Stand if sabacc
        if (Math.abs(handSum) <= 2) { 
            return;
        }

        // Swap to obtain sabacc  
        for (int i = 0; i < hand.size(); i++) { 
            if (handSum - getCard(i) + deck.getDiscard() == 0) {  
                swap(i);
                return;
            }
        }

        if(handSum > 10) {
        int largest = 0; 
            for (int i = 0; i < hand.size(); i++) { 
                if (hand.get(i) > hand.get(largest)) {
                    largest = i;
                }
            }
            swap(largest);
            return;
        }
        gain();
        return;
    }

    public Strategy getStrategy() {
        return Strategy.MASTER_V2;
    }
}
