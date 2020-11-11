package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunityCardsTest {
    @Test
    public void testAddToCommunity(){
        CommunityCards communityCards = new CommunityCards();
        Deck deck = new Deck();
        Card card1 = deck.peekTopCard();
        communityCards.receiveCard(card1);
        assertEquals(card1, communityCards.getCommunityCardsList().get(0));
    }
}
