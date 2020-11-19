package model;

import java.util.ArrayList;
import java.util.List;

public abstract class CardRecipient {

  protected List<Card> cardsList;
  protected List<Card> newCardList;

  public CardRecipient() {
    cardsList = new ArrayList<>();
    newCardList = new ArrayList<>();
  }

  abstract void receiveCard(Card card);

  public void clearNewCards() {
    newCardList.clear();
  }

  protected void addNewCards(Card card) {
    newCardList.add(card);
  }

  public List<Card> getNewCards() {
    return newCardList;
  }
}
