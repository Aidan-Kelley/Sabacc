// package players;
// import java.util.ArrayList;

// import main.Calculations;
// import main.SabaccDeck;

// public class AlwaysTwoCards extends Player {
    
//     ArrayList<Integer> hand;
//     SabaccDeck deck;
//     public AlwaysTwoCards(SabaccDeck deck) {
//         super(deck);
//         this.deck = deck;
//         hand = super.getHand();
//     }

//     @Override
//     public void makeDecision() {
//         // Stand if sabacc
//         if (Calculations.handSum(hand) == 0) { 
//             return;
//         }

//         // Swap to obtain sabacc  
//         for (int i = 0; i < hand.size(); i++) { 
//             if (Calculations.handSum(hand) - getCard(i) + deck.getDiscard() == 0) {  
//                 swap(i);
//                 return;
//             }
//         }

//         // discard highest card
//         int largest = 0; 
//         for (int i = 0; i < hand.size(); i++) { 
//             if (Math.abs(hand.get(i)) > Math.abs(hand.get(largest))) {
//                 largest = i;
//             }
//         }
//         discard(largest);
//         gain();
//         return;
//     }

//     public Strategy getStrategy() {
//         return Strategy.TWO_CARDS;
//     }
// }
