package model;

import java.util.ArrayList;
import java.util.List;

public class CommunityCards extends CardRecipient {

    public CommunityCards(){
       super();
    }

    @Override
    void receiveCard(Card card) {
        cardsList.add(card);
        addNewCards(card);
    }

    @Override
    void discardCard(Card card) {

    }

    public List<Card> getCommunityCardsList(){
        return cardsList;
    }
}
