package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    @Test
    public void testToString(){
        Card card = new Card(2, Suit.CLUBS);
        assertEquals("CLUBS 2", card.toString());
    }

    @Test
    public void testCardRank(){
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(2, card.getRank());
    }


    @Test
    void testCardValue() {
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(2, card.getRank());
    }

    @Test
    void testCardSuit() {
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(Suit.CLUBS, card.getCardSuit());
    }

    @Test
    void testCardVisibility() {
        Card card = new Card(2, Suit.CLUBS);
        assertTrue(false == card.isVisible());
        card.makeVisible();
        assertTrue(true == card.isVisible());
    }

    @Test
    void testCardSymbol() {
        Card card = new Card(11, Suit.CLUBS);
        assertEquals("J", card.getCardSymbol());
    }
}
