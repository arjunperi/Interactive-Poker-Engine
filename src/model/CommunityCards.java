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

    public List<Card> getCommunityCardsList(){
        return cardsList;
    }
}
