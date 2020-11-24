package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hand to be held by a player
 */
public class Hand {

  private final List<Card> hand;

  private int handSize;


  public Hand() {
    hand = new ArrayList<>();
    handSize = 0;
  }

  /**
   * Adds a card to the hand
   * @param card Card object to be added to hand
   */
  public void add(Card card) {
    hand.add(card);
    handSize++;
  }

  /**
   * Clears all cards from the hand
   */
  public void clear() {
    hand.clear();
    handSize = 0;
  }

  /**
   * removes the card passed in from the hand
   * @param card Card to be removed
   */
  public void remove(Card card) {
    hand.remove(card);
    handSize -= 1;
  }

  /**
   * Returns all the cards in the hand
   * @return List of card objects
   */
  public List<Card> getCards() {
    return hand;
  }

  /**
   * Returns the size of the hand
   * @return int size
   */
  public int getHandSize() {
    return handSize;
  }

  /**
   * Gets the card at the index of the hand passed in
   * @param index index to get card at
   * @return Card object
   */
  public Card get(int index) {
    if (index < this.getHandSize()) {
      int i = 0;
      for (Card card : this.getCards()) {
        if (i == index) {
          return card;
        }
        i++;
      }
    }
    return null;
  }


  /**
   * Sorts the hand according to card rank in decreasing order, returns a new sorted hand
   * @return Hand object
   */
  public Hand sortHand() {
    Hand sortedHand = new Hand();
    while (handSize > 0) {
      int max = -2;
      Card maxCard = null;
      for (Card card : this.getCards()) {
        if (card.getRank() > max) {
          max = card.getRank();
        }
      }
      for (Card card : this.getCards()) {
        if (card.getRank() == max) {
          maxCard = card;
        }
      }
      sortedHand.add(maxCard);
      this.remove(maxCard);
    }
    return sortedHand;
  }

  /**
   * Copies the hand it is called on
   * @return Hand object
   */
  public Hand copyHand() {
    Hand copyHand = new Hand();
    for (Card card : this.getCards()) {
      copyHand.add(card);
    }
    copyHand = copyHand.sortHand();
    return copyHand;
  }


}


