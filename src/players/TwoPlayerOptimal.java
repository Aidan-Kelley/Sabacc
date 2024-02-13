package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public class TwoPlayerOptimal extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    private final int STAND_AT = 1;
    private final int SWAP_AT = 1;
    private final int DISCARD_AT = 10;

    public TwoPlayerOptimal(SabaccDeck deck) {
        super(deck);
        super.STAND_AT = STAND_AT;
        super.SWAP_AT = SWAP_AT;
        super.DISCARD_AT = DISCARD_AT;

        this.deck = deck;
        hand = super.getHand();
    }

    public Strategy getStrategy() {
        return Strategy.TWO_P_OPTIMAL;
    }
}
