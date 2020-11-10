package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    @Test
    public void testToString(){
        Card card = new Card(2, "CLUBS");
        assertEquals("CLUBS 2", card.toString());
    }

    @Test
    public void testCardRank(){
        Card card = new Card(2, "CLUBS");
        assertEquals(2, card.getRank());
    }


    @Test
    void testCardValue() {
        Card card = new Card(2, "CLUBS");
        assertEquals(2, card.getRank());
    }

    @Test
    void testCardSuit() {
        Card card = new Card(2, "CLUBS");
        assertEquals("CLUBS", card.getCardSuit());
    }

    @Test
    void testCardVisibility() {
        Card card = new Card(2, "CLUBS");
        assertTrue(false == card.getCardVisibility());
        card.makeVisible();
        assertTrue(true == card.getCardVisibility());

    }

    @Test
    void testCardSymbol() {
        Card card = new Card(11, "CLUBS");
        assertEquals("J", card.getCardSymbol());
    }
}
