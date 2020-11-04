package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {

    @Test
    void testNewDeck() {
        Deck deck = new Deck();
        Card card1 = new Card(11,Suit.CLUBS);
        Card card2 = new Card(4,Suit.DIAMONDS);
        Card card3 = new Card(2,Suit.SPADES);
        deck.replaceTopCard(card1);
        deck.replaceTopCard(card2);
        deck.replaceTopCard(card3);
        assertEquals(2, deck.getTopCard().getCardRank());
        assertEquals(Suit.DIAMONDS, deck.getTopCard().getCardSuit());
        assertEquals("J", deck.getTopCard().getCardSymbol());
    }
}