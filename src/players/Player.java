package players;
import java.util.ArrayList;

import main.SabaccDeck;

public abstract class Player {
    
    private ArrayList<Integer> hand = new ArrayList<Integer>(); 
    private SabaccDeck deck;

    public abstract void makeDecision();

    public abstract Strategy getStrategy();

    // give the player a hand of 2 cards
    public Player(SabaccDeck deck) {
        this.deck = deck;
        hand.add(deck.drawCard());
        hand.add(deck.drawCard());
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
