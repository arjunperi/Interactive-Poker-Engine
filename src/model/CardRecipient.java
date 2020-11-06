package model;

import java.util.ArrayList;
import java.util.List;

public abstract class CardRecipient {
    protected List<Card> cardsList;
    protected List<Card> newCardList;
    protected List<Card> discardedCardList;

    public CardRecipient(){
        cardsList = new ArrayList<>();
        newCardList = new ArrayList<>();
        discardedCardList = new ArrayList<>();
    }

    abstract void receiveCard(Card card);

    abstract void discardCard(Card card);

    protected void addDiscardedCard(Card card){
        discardedCardList.add(card);
    }

    public List<Card> getDiscardedCardList(){
        return discardedCardList;
    }

    protected void clearNewCards() {
        newCardList.clear();
    }

    protected void addNewCards(Card card) {
        newCardList.add(card);
    }

    public List<Card> getNewCards(){
        return newCardList;
    }

}
