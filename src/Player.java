import java.util.ArrayList;

public class Player {
    
    private ArrayList<Integer> hand = new ArrayList<Integer>(); 
    private SabaccDeck deck;

    // give the player a hand of 2 cards
    public Player(SabaccDeck deck) {
        this.deck = deck;
        hand.add(deck.drawCard());
        hand.add(deck.drawCard());
    }

    // move card from player hand to discard pile
    public void discard(int index) {
        deck.discard(hand.get(index)); 
        hand.remove(index);
    }

    public void gain() {
        hand.add(deck.drawCard()); 
    }

    public void swap(int index) {
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
