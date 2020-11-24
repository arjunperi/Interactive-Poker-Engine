package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an object that is able to receive and hold cards.
 */
public abstract class CardRecipient {

  protected List<Card> cardsList;
  protected List<Card> newCardList;

  public CardRecipient() {
    cardsList = new ArrayList<>();
    newCardList = new ArrayList<>();
  }

  abstract void receiveCard(Card card);

  /**
   * Clears the list of new cards added
   */
  public void clearNewCards() {
    newCardList.clear();
  }

  protected void addNewCards(Card card) {
    newCardList.add(card);
  }

  /**
   * New cards are cards that have been received in the current round. Returns a List of the card objects that have been added this round
   * @return List of new cards
   */
  public List<Card> getNewCards() {
    return newCardList;
  }
}
