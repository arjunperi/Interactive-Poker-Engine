package model;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends DukeApplicationTest {
    private Player player;

    @Test
    public void testGetBankroll(){
        player = new Player("Player", 100);
        assertEquals(100, player.getBankroll());
    }

    @Test
    public void testUpdateBankroll(){
        player = new Player("Player", 100);
        player.updateBankroll(-100);
        assertEquals(0, player.getBankroll());
    }

    @Test
    public void testIsSolvent(){
        player = new Player("Player", 100);
        player.updateBankroll(-100);
        assertFalse(player.isSolvent());
    }

    @Test
    public void testFold(){
        player = new Player("Player", 100);
        assertTrue(player.isActive());
        player.exitHand();
        assertFalse(player.isActive());
    }

    @Test
    public void testPerformAction(){}

    @Test
    public void testAddingToHand(){
        player = new Player("Player", 100);
        Card testCard = new Card(2,Suit.CLUBS);
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
    }

    @Test
    public void testDiscard(){
        player = new Player("Player", 100);
        Card testCard = new Card(2,Suit.CLUBS);
        player.receiveCard(testCard);
        assertEquals(testCard, player.getHand().getCards().get(0));
        player.discardCard(testCard);
        assertEquals(0, player.getHand().getCards().size());
    }


    @Test
    public void testToString(){
        player = new Player("Player", 100);
        assertEquals("Player", player.toString());
    }

}
