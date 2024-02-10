import java.util.ArrayList;

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
}
