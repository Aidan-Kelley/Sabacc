package players;
import java.util.ArrayList;

import main.Calculations;
import main.SabaccDeck;

public abstract class Player {
    
    private ArrayList<Integer> hand = new ArrayList<Integer>(5); 
    private SabaccDeck deck;
    public int STAND_AT = 1;
    public int SWAP_AT = 1;
    public int DISCARD_AT = 10;

    public abstract Strategy getStrategy();

    public Player(SabaccDeck deck) {
        this.deck = deck;
        hand.add(deck.drawCard());
        hand.add(deck.drawCard());
    }

    public enum Action {
        STAND, SWAP, DISCARD, GAIN;
    }

    public void newHand(int size) {
        for (int i = 0; i < size; i++) {
            hand.set(i,deck.drawCard());
        }
    }

    // move card from player hand to discard pile
    protected void discard(int index) {
        deck.discard(hand.get(index)); 
        hand.remove(index);
    }

    protected void gain() {
        hand.add(deck.drawCard()); 
    }

    protected void swap(int index) {
        hand.add(deck.getDiscard());
        discard(index);
    }
    
    public int handSize() {
        return hand.size();
    }

    public int getCard(int index) {
        return hand.get(index);
    }

    public ArrayList<Integer> getHand() {
        return hand;
    }

    public String toString() {
        return hand.toString();
    }

    public Action makeDecision(int STAND_AT, int SWAP_AT, int DISCARD_AT) {
        int handSum = Calculations.handSum(hand); 
        
        // when to swap
        for (int i = 0; i < hand.size(); i++) { 
            int sumIfSwap = handSum - getCard(i) + deck.getDiscard();
            boolean swapIsGoodEnough = Math.abs(sumIfSwap) < Math.abs(SWAP_AT) || sumIfSwap == SWAP_AT || sumIfSwap == -1*Math.abs(SWAP_AT);
            boolean improvesHand = Math.abs(sumIfSwap) < Math.abs(handSum) || sumIfSwap == -1*Math.abs(handSum);
            if (swapIsGoodEnough && improvesHand) {  
                swap(i);
                return Action.SWAP;
            }
        }
        
        if (Math.abs(handSum) < Math.abs(STAND_AT) || handSum == STAND_AT || handSum == -1*Math.abs(STAND_AT)) { 
            return Action.STAND;
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
                return Action.DISCARD;
        }

        // else gain
        gain();
        return Action.GAIN;
    }
}
