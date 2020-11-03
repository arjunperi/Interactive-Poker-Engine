package model;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends DukeApplicationTest {
    private Player player;

    @Test
    public void testGetBankroll(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        assertEquals(100, player.getBankroll());
    }

    @Test
    public void testUpdateBankroll(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        player.updateBankroll(-100);
        assertEquals(0, player.getBankroll());
    }

    @Test
    public void testIsSolvent(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        player.updateBankroll(-100);
        assertFalse(player.isSolvent());
    }

    @Test
    public void testFold(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        assertTrue(player.isActive());
        player.exitHand();
        assertFalse(player.isActive());
    }

    @Test
    public void testPerformAction(){}

    @Test
    public void testAddingToHand(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        Card testCard = new Card(2,Suit.CLUBS);
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
    }

    @Test
    public void testDiscard(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        Card testCard = new Card(2,Suit.CLUBS);
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
        player.discard(testCard);
        assertEquals(0, player.getHand().getCards().size());
    }


    @Test
    public void testChooseExchangeCards(){}

    @Test
    public void testToString(){
        Pot pot = new Pot();
        player = new Player("Player", 100, pot);
        assertEquals("Player", player.toString());
    }


}
