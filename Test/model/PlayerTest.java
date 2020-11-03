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
}
