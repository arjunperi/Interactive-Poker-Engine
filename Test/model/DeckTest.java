package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

  private Deck deck;
  private JSONReader reader;

  @BeforeEach
  void setUp() {
    reader = new JSONReader();
    reader.parse("/cardSettings.json");
    List<String> suitNames = new ArrayList<>();
    List<Integer> rankValues = new ArrayList<>();
    rankValues.addAll(reader.getRanks().keySet());
    suitNames.addAll(reader.getSuits().keySet());
    deck = new Deck(suitNames, rankValues);
  }

  @Test
  void testNewDeck() {
    Card card1 = new Card(11, "CLUBS");
    Card card2 = new Card(4, "DIAMONDS");
    Card card3 = new Card(2, "SPADES");
    deck.replaceTopCard(card1);
    deck.replaceTopCard(card2);
    deck.replaceTopCard(card3);
    assertEquals(2, deck.getTopCard().getRank());
    assertEquals("DIAMONDS", deck.getTopCard().getCardSuit());
  }


  @Test
  public void testTopCard() {
    Card card1 = new Card(11, "CLUBS");
    Card card2 = new Card(4, "DIAMONDS");
    Card card3 = new Card(2, "SPADES");
    deck.replaceTopCard(card1);
    deck.replaceTopCard(card2);
    deck.replaceTopCard(card3);
    assertEquals(2, deck.getTopCard().getRank());
    assertEquals("DIAMONDS", deck.getTopCard().getCardSuit());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(deck.isEmpty());
  }
}