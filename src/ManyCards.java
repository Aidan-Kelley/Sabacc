import java.util.ArrayList;

public class ManyCards extends Player implements PInterface {

    ArrayList<Integer> hand;
    SabaccDeck deck;
    public ManyCards(SabaccDeck deck) {
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

        // draw card
        gain();
        return;
    } 
}
