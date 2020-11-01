package model;

import java.util.ArrayList;
import java.util.List;

//isn't actually used right now since we don't have real hand evalation
public class CommunityCards implements CardRecipient {
    private List<Card> communityCardsList;


    public CommunityCards(){
        communityCardsList = new ArrayList<>();
    }

    @Override
    public void receiveCard(Card card){
        communityCardsList.add(card);
    }

    public List<Card> getCommunityCardsList(){
        return communityCardsList;
    }
}
