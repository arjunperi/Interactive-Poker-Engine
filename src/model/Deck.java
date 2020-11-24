package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Represents the deck that holds the cards to be dealt
 */
public class Deck extends Stack<Card> {

  private final Stack<Card> deck;
  private final List<String> suits;
  private final List<Integer> ranks;
  private final Map<String, Card> stringToCardMap;


  public Deck(List<String> suits, List<Integer> ranks) {
    this.suits = suits;
    this.ranks = ranks;
    stringToCardMap = new HashMap<>();
    deck = new Stack<>();
    createDeck();

    Collections.shuffle(deck);
  }

  /**
   * Returns the top card in the deck, i.e the next card to be dealt
   * @return Card object
   */
  public Card getTopCard() {
    return deck.pop();
  }

  private void createDeck() {
    for (String suit : suits) {
      for (int rank : ranks) {
        Card card = new Card(rank, suit);
        deck.add(card);
        stringToCardMap.put(card.toString(), card);
      }
    }
  }

  /**
   * Puts the card passed in on the top of the deck
   * @param card Card object
   */
  public void replaceTopCard(Card card) {
    deck.push(card);
  }

  /**
   * If the deck is empty, returns true, returns false otherwise
   * @return Boolean
   */
  public boolean isEmpty() {
    return deck.isEmpty();
  }

  /**
   * Converts the String passed into a Card object in the deck
   * @param cardString String format of card to be converted into Card object. Must be in format "Rank Suit".
   * @return Card object
   */
  public Card StringToCard(String cardString) {
    return stringToCardMap.get(cardString);
  }
}
