package main;

import java.util.ArrayList;
import java.util.List;

public class Calculations {
    // returns the winning hand of 2 hands
    public static int betterHand(ArrayList<Integer> hand0, ArrayList<Integer> hand1) {
        int sum0 = handSum(hand0);
        int sum1 = handSum(hand1);
        if (Math.abs(sum0) < Math.abs(sum1)) // check if hand0 is closer to zero
            return 0;
        if (Math.abs(sum0) > Math.abs(sum1)) // check if hand 1 is closer to 0
            return 1;
        if (sum0 > sum1) // positve wins
            return 0;
        if(sum0 < sum1) 
            return 1;
        if(sum0 == 0 && sum1 == 0) { // If both have sabacc
            if(hand0.get(0) == 0 && hand0.get(1) == 0 && hand0.size() == 2) // Pure sabacc
                return 0;
            if(hand1.get(0) == 0 && hand1.get(1) == 0 && hand1.size() == 2) 
                return 1;
            
            // Full sabacc
            if (hand0.contains(0) && xOfAKind(hand0, 4) == 10)
                return 0;
            if (hand1.contains(0) && xOfAKind(hand1, 4) == 10)
                return 1;
            // Player 0 Fleet
            if (xOfAKind(hand0, 4) != 11 && hand0.contains(0)) {
                // Both have fleet
                if (xOfAKind(hand1, 4) != 11 && hand1.contains(0)) {
                    if (xOfAKind(hand0, 4) < xOfAKind(hand1, 4))
                        return 0;
                    if (xOfAKind(hand0,4) > xOfAKind(hand1, 4))
                        return 1;
                }
                return 0; // only player 0 has a fleet
            }
            // Player 1 fleet   
            if (hand1.contains(0) && xOfAKind(hand1, 4) != 11)
                return 1;

            // Player 0 Yee-Haw
            if (hand0.contains(0) && hand0.size() == 3 && xOfAKind(hand0, 2) != 11) {
                // Both have Yee-Haw
                if (xOfAKind(hand1, 2) != 11 && hand1.contains(0)) {
                    if (xOfAKind(hand0, 2) < xOfAKind(hand1, 2))
                        return 0;
                    if (xOfAKind(hand0,2) > xOfAKind(hand1, 2))
                        return 1;
                }
            }
            // Player 1 Yee-Haw
            if (xOfAKind(hand1, 2) != 11 && hand1.contains(0) && hand1.size() == 3)
                return 1;

            // Ryhlet 
            if (rhylet(hand0) < rhylet(hand1))
                return 0;
            if (rhylet(hand0) > rhylet(hand1))
                return 1;
            
            // Squdron
            if (xOfAKind(hand0,4) < xOfAKind(hand1, 4))
                return 0;
            if (xOfAKind(hand0, 4) > xOfAKind(hand1, 4)) 
                return 1;
    
            // Gee Whiz!
            if (geeWhiz(hand0) && !geeWhiz(hand1))
                return 0;
            if (!geeWhiz(hand0) && geeWhiz(hand1))
                return 1;
            
            // Straight Kyhron
            if (straightKyhron(hand0) < straightKyhron(hand1))
                return 0;
            if (straightKyhron(hand0) > straightKyhron(hand1))
                return 1;

            // Banthas wild
            if (xOfAKind(hand0,3) < xOfAKind(hand1, 3))
                return 0;
            if (xOfAKind(hand0,3) < xOfAKind(hand1, 3))
                return 1;

            // Rule of Two
            if (ruleOfTwo(hand0) < ruleOfTwo(hand1))
                return 0;
            if (ruleOfTwo(hand0) < ruleOfTwo(hand1))
                return 1;

            // Sabacc (one pair)
            if (xOfAKind(hand0,2) < xOfAKind(hand1, 2))
                return 0;
            if (xOfAKind(hand0, 2) > xOfAKind(hand1, 2)) 
                return 1;

            // Sabacc with most cards
            if (hand0.size() > hand1.size())
                return 0;
            if (hand0.size() < hand1.size()) 
                return 1;

            // hand with highest value cards
            if (absSum(hand0) > absSum(hand1))
                return 0;
            if (absSum(hand0) < absSum(hand1)) 
                return 1;

            // Sabacc with highest single value cards
            if (highestCard(hand0) > highestCard(hand1))
                return 0;
            if (highestCard(hand0) > highestCard(hand0))
                return 1;
    }
        // NULRHEK with most cards
        if (hand0.size() > hand1.size())
                return 0;
        if (hand0.size() < hand1.size()) 
            return 1;                
        // NULRHEK with highest value cards
        if (absSum(hand0) > absSum(hand1))
            return 0;
        if (absSum(hand0) < absSum(hand1)) 
            return 1;
        // NULREK with highest value single card
        if (highestCard(hand0) > highestCard(hand1))
            return 0;
        if (highestCard(hand0) > highestCard(hand0))
            return 1;
        // single blind draw
            return (int)(Math.random()*2);
    } 

    public static int handSum(ArrayList<Integer> hand) {
        int sum = 0;
        for (int card : hand) {
            sum += card; 
        }
        return sum;
    }

    public static int xOfAKind(ArrayList<Integer> hand, int x) {
        for(int i = 0; i <= hand.size() - x; i++) {
            int count = 1;
            for(int j = i+1; j < hand.size(); j++) {
                if (Math.abs(hand.get(i)) == Math.abs(hand.get(j)))
                    count++;
            }
            if (count == x)
                return Math.abs(hand.get(i));
        }
        return 11; // returns 11 if false 
    }

    // assumes sum is 0
    public static int rhylet(ArrayList<Integer> hand) {
        int threeOfAKind = xOfAKindSigned(hand, 3);
        if (threeOfAKind != 11) // check for three of a kind
            // check for pair
            for(int i = 0; i <= hand.size() - 2; i++) {
                if (hand.get(i) != threeOfAKind) {
                    int count = 1;
                    for(int j = i+1; j < hand.size(); j++) {
                        if (hand.get(i) == hand.get(j))
                            count++;
                    }
                    if (count == 2)
                        return Math.abs(threeOfAKind);
                }
        }
        return 11; // false
    }

    // used in checkRhylet
    private static int xOfAKindSigned(ArrayList<Integer> hand, int x) {
        for(int i = 0; i <= hand.size() - x; i++) {
            int count = 1;
            for(int j = i+1; j < hand.size(); j++) {
                if (hand.get(i) == hand.get(j))
                    count++;
            }
            if (count == x)
                return hand.get(i);
        }
        return 11;
    }

    // assumes sum is 0
    public static boolean geeWhiz(ArrayList<Integer> hand) {
        int[] gheeWhizCards = {1,2,3,4,10};
        for (int card : gheeWhizCards) {
            if (!(hand.contains(card) || hand.contains(card*-1)))
                return false;
        }
        return true;
    }

    /* assumes sum is 0
    returns the lowest value in the straight
    or 11 if false  
    */
    public static int straightKyhron(List<Integer> hand) {
        hand.sort((Integer x, Integer y) -> Integer.compare(Math.abs(x), Math.abs(y))); // sorts ignoring sign
        if(hand.size() != 4)
            return 11;

        for(int i = 0; i < hand.size() - 1; i++)
            if (Math.abs(hand.get(i)) + 1 != Math.abs(hand.get(i+1)))
                return 11;

        return Math.abs(hand.get(0));
    }

    /* returns the lowest pair or 11 if false 
    ex: for a pair of 3s and 5s, 3 will be returned
    */
    public static int ruleOfTwo(ArrayList<Integer> hand) {
        int firstPair = xOfAKind(hand, 2);
        if (firstPair == 11)
            return 11; 
        for(int i = 0; i <= hand.size() - 2; i++) {
            if (firstPair == Math.abs(hand.get(i)))
                continue;
            int count = 1;
            for(int j = i+1; j < hand.size(); j++) {
                if (firstPair != Math.abs(hand.get(i)) && Math.abs(hand.get(i)) == Math.abs(hand.get(j)))
                    count++;
            }
            if (count == 2)
                return Math.min(firstPair, Math.abs(hand.get(i)));
        }
        return 11; // returns 11 if false 


    }

    public static int absSum(ArrayList<Integer> hand) {
        int sum = 0;
        for (int card : hand)
            sum += Math.abs(card);
        return sum;
    }

    public static int highestCard(ArrayList<Integer> hand) {
        int greatest = hand.get(0);
        for (int i = 1; i < hand.size(); i++)
            if (hand.get(i) > greatest)
                greatest = hand.get(i);
        return greatest;
    }
} 