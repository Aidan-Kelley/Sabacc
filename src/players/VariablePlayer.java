package players;

import java.util.ArrayList;
import java.util.Random;

import main.Calculations;
import main.Constants;
import main.SabaccDeck;
import main.Constants.TwoPlayerConstants;

public class VariablePlayer extends Player {
    
    ArrayList<Integer> hand;
    SabaccDeck deck;
    public final int STAND_AT = TwoPlayerConstants.STAND_AT;
    public final int DISCARD_AT = TwoPlayerConstants.DISCARD_AT;
    public int SWAP_AT;

    public VariablePlayer(SabaccDeck deck) {
        super(deck);
        this.deck = deck;
        hand = super.getHand();
        setConstants();
    }

    public int getVariable() {
        return SWAP_AT += 0;
    }

    public static String getVariableName() {
        return "SWAP_AT";
    }
    private void setConstants() {
        Random r = new Random();
        SWAP_AT = r.nextInt(Constants.LOWER_BOUND,Constants.UPPER_BOUND);
    }
    @Override
    public void makeDecision() {
        int handSum = Calculations.handSum(hand); 
        // When to stand
        if (Math.abs(handSum) < Math.abs(STAND_AT) || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return;
        }

        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard();
            if (Math.abs(sumIfSwap) < Math.abs(SWAP_AT) || sumIfSwap == SWAP_AT || sumIfSwap == -1*Math.abs(SWAP_AT)) {  
                swap(i);
                return;
            }
        }

        // when to discard
        if(Math.abs(handSum) > Math.abs(DISCARD_AT)) {
            int largest = 0; 
                for (int i = 0; i < hand.size(); i++) { 
                    if (hand.get(i) > hand.get(largest)) {
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
}
