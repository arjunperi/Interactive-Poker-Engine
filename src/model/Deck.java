package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Deck extends Stack<Card> {

  private final Stack<Card> deck;
  private final List<String> suits;
  private final List<Integer> ranks;
  private final Map<String, Card> stringToCardMap;

    /*public Deck() {
        deck = new Stack();
        createDeck();
    }*/

  public Deck(List<String> suits, List<Integer> ranks) {
    this.suits = suits;
    this.ranks = ranks;
    stringToCardMap = new HashMap<>();
    deck = new Stack<>();
    createDeck2();

    Collections.shuffle(deck);
  }

  public Card getTopCard() {
    return deck.pop();
  }

    /*public void createDeck(){
        for (Suit suit : Suit.values()){
            for (int r = 2; r<15; r++){
                Card card = new Card(r,suit);
                deck.add(card);
            }
        }
    }*/

  public void createDeck2() {
    for (String suit : suits) {
      for (int rank : ranks) {
        Card card = new Card(rank, suit);
        deck.add(card);
        stringToCardMap.put(card.toString(), card);
      }
    }
  }


  public void replaceTopCard(Card card) {
    deck.push(card);
  }

  //temporary, just for print methods
  public Card peekTopCard() {
    return deck.peek();
  }

  public boolean isEmpty() {
    return deck.isEmpty();
  }

  public void shuffle() {
    Collections.shuffle(deck);
  }

  public Card StringToCard(String cardString) {
    return stringToCardMap.get(cardString);
  }
}
