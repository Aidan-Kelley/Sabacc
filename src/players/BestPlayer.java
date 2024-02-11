package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class BestPlayer extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    public BestPlayer(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        // Stand if sabacc
        if (Calculations.handSum(hand) == 0) { 
            return;
        }

        // Swap to obtain sabacc  
        for (int i = 0; i < hand.size(); i++) { 
            if (Calculations.handSum(hand) - getCard(i) + deck.getDiscard() == 0) {  
                swap(i);
                return;
            }
        }

        // discard highest card if total > 10
        if(Calculations.handSum(hand) > 10) {
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
        return Strategy.MASTER;
    }
}
