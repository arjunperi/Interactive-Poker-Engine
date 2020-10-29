package model;

import java.util.ArrayList;
import java.util.List;

//isn't actually used right now since we don't have real hand evaulation
public class CommunityCards {
    private List<Card> communityCards;

    public CommunityCards(){
        communityCards = new ArrayList<>();
    }

    public void addToCommunityCards(Card card){
        communityCards.add(card);
    }
}
