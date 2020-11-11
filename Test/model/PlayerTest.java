package model;

import controller.JSONReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends DukeApplicationTest {
    private Player player;
    private Deck deck;
    private JSONReader reader;

    @BeforeEach
    void setUp(){
        reader = new JSONReader();
        reader.parse("/texas_holdem.json");
        List<String> suitNames = new ArrayList<>();
        List<Integer> rankValues = new ArrayList<>();
        rankValues.addAll(reader.getRanks().keySet());
        suitNames.addAll(reader.getSuits().keySet());
        deck = new Deck(suitNames, rankValues);
    }
    @Test
    public void testGetBankroll(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        assertEquals(100, player.getBankroll());
    }

    @Test
    public void testUpdateBankroll(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        player.updateBankroll(-100);
        assertEquals(0, player.getBankroll());
    }

    @Test
    public void testIsSolvent(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        player.updateBankroll(-100);
        assertFalse(player.isSolvent());
    }

    @Test
    public void testFold(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        assertTrue(player.isActive());
        player.exitHand();
        assertFalse(player.isActive());
    }

    @Test
    public void testAddingToHand(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        Card testCard = new Card(2,"CLUBS");
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
    }

    @Test
    public void testDiscard(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        Card testCard = new Card(2,"CLUBS");
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
        player.discardCard(testCard);
        assertEquals(0, player.getHand().getCards().size());
    }


    @Test
    public void testToString(){
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        assertEquals("Player", player.toString());
    }

    @Test
    public void testExchangeCards(){
        Card card1 = deck.peekTopCard();
        Dealer dealer = new Dealer(deck);
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        player = new Player("Player", 100, communityCards, pot);
        dealer.exchangeCards(player, (List.of("14 DIAMONDS")));
        assertEquals(card1, player.getHand().getCards().get(0));
        assertEquals(card1, player.getHand().getCards().get(0));
    }

}
