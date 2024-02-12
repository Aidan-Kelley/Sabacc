package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class AlwaysTwoCards extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    public AlwaysTwoCards(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
    }

    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        // Stand if sabacc
        if (handSum == 0) { 
            return;
        }

        // Swap to obtain sabacc  
        for (int i = 0; i < hand.size(); i++) { 
            if (handSum - getCard(i) + deck.getDiscard() == 0) {  
                swap(i);
                return;
            }
        }

        // discard highest card
        int largest = 0; 
        for (int i = 0; i < hand.size(); i++) { 
            if (hand.get(i) > hand.get(largest)) {
                largest = i;
            }
        }
        swap(largest);
        return;
    }

    public Strategy getStrategy() {
        return Strategy.TWO_CARDS;
    }
}
