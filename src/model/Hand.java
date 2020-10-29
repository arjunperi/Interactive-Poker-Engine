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

    public int getHandTotal(){
        for (Card card: hand){
            handTotal += card.getCardValue();
        }
        return handTotal;
    }


}
