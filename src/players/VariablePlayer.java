package players;

import java.util.ArrayList;
import java.util.Random;
import main.Calculations;
import main.Constants;
import main.SabaccDeck;
import main.Constants.*;

public class VariablePlayer extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    public final int STAND_AT = FourPlayerConstants.STAND_AT;
    public final int SWAP_AT = FourPlayerConstants.SWAP_AT;
    public int DISCARD_AT = FourPlayerConstants.DISCARD_AT;

    public VariablePlayer(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
        setConstants();
    }

    public int getVariable() {
        return DISCARD_AT += 0;
    }

    public static String getVariableName() {
        return "DISCARD_AT";
    }

    private void setConstants() {
        Random r = new Random();
        DISCARD_AT = r.nextInt(Constants.LOWER_BOUND,Constants.UPPER_BOUND);
    }
    @Override

    public Decision makeDecision() {
        int handSum = Calculations.handSum(hand); 
        
        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard();
            boolean swapIsGoodEnough = Math.abs(sumIfSwap) < Math.abs(SWAP_AT) || sumIfSwap == SWAP_AT || sumIfSwap == -1*Math.abs(SWAP_AT);
            boolean improvesHand = Math.abs(sumIfSwap) < Math.abs(handSum) || sumIfSwap == -1*Math.abs(handSum);
            if (swapIsGoodEnough && improvesHand) {  
                swap(i);
                return Decision.SWAP;
            }
        }
        
        if (Math.abs(handSum) < Math.abs(STAND_AT) || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return Decision.STAND;
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
                return Decision.DISCARD;
        }

        // else gain
        gain();
        return Decision.GAIN;
    }
}
