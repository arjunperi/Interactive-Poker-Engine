package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

  @Test
  void testNewHand() {
    Hand hand = new Hand();
    Card card1 = new Card(11, "CLUBS");
    Card card2 = new Card(4, "DIAMONDS");
    Card card3 = new Card(2, "SPADES");
    hand.add(card1);
    hand.add(card2);
    hand.add(card3);
    assertEquals(3, hand.getCards().size());
  }
}