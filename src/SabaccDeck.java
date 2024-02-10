import java.util.ArrayList;

public class SabaccDeck {

    private ArrayList<Integer> deck = new ArrayList<Integer>();
    private int discard;
    public SabaccDeck() {
        initDeck();
    }
    
    private void initDeck() {
        // add 3 suits of positive and negative 1-10
        for(byte i = 0; i < 3; i++) {
            for(int j = 1; j <= 10; j++) {
                deck.add(j);
                deck.add(j*-1);
            }
        }
        deck.add(0);
        deck.add(0);
        discard = drawCard();
    }

    public int drawCard() {
        if (deck.size() == 0)
            initDeck();
        int index = (int)(Math.random()*deck.size());
        int card = deck.get(index);
        deck.remove(index);
        return card;
    }

    public void discard(int card) {
        discard = card;
    }

    public int getDiscard() {
        return discard;
    }
}
