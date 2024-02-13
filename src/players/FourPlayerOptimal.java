package players;
import java.util.ArrayList;

import main.SabaccDeck;

public class FourPlayerOptimal extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    private final int STAND_AT = 1;
    private final int SWAP_AT = 1;
    private final int DISCARD_AT = 2;

    public FourPlayerOptimal(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        super.STAND_AT = STAND_AT;
        super.SWAP_AT = SWAP_AT;
        super.DISCARD_AT = DISCARD_AT;
        hand = super.getHand();
    }

    public Strategy getStrategy() {
        return Strategy.FOUR_P_OPTIMAL;
    }
}
