package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    //for temporary hand evaluation logic -> won't exist later
    private int handTotal;

    public Hand(){
        hand = new ArrayList<>();
        handTotal = 0;
    }

    public void add(Card card){
        hand.add(card);
    }

    public List<Card> getCards(){
        return hand;
    }

    //community cards more of a hand then a player

    //need a class that combines cards with player hand and cards from community

    //combiner (PokerHand)
        //make permutations, pass them into handEval

    public int getHandTotal(){
        for (Card card: hand){
            handTotal += card.getCardValue();
        }
        return handTotal;
    }


}
