package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    @Test
    void testCardValue() {
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(2, card.getCardRank());
    }

    @Test
    void testCardSuit() {
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(Suit.CLUBS, card.getCardSuit());
    }

    @Test
    void testCardVisibility() {
        Card card = new Card(2, Suit.CLUBS);
        assertTrue(false == card.getCardVisibility());
        card.makeVisible();
        assertTrue(true == card.getCardVisibility());

    }

    @Test
    void testCardSymbol() {
        Card card = new Card(11, Suit.CLUBS);
        assertEquals("J", card.getCardSymbol());
    }
}
