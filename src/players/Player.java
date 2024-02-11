package players;
import java.util.ArrayList;

import main.SabaccDeck;

public abstract class Player {
    
    private ArrayList<Integer> hand = new ArrayList<Integer>(5); 
    private SabaccDeck deck;

    public abstract void makeDecision();

    public abstract Strategy getStrategy();

    public Player(SabaccDeck deck) {
        this.deck = deck;
        hand.add(deck.drawCard());
        hand.add(deck.drawCard());
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
}
