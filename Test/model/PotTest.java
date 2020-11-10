package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PotTest {

    @Test
    void testAddToPot(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player = new Player("Arjun",100, communityCards);
        assertEquals(0,pot.getPotTotal());
        pot.addToPot(10);
        assertEquals(10,pot.getPotTotal());
        pot.addToPot(10);
        assertEquals(20,pot.getPotTotal());
        pot.dispersePot(player);
        assertEquals(0,pot.getPotTotal());
    }

    @Test
    public void dispersePot(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player = new Player("Arjun",100, communityCards);
        pot.addToPot(10);
        pot.dispersePot(player);
        assertEquals(110,player.getBankroll());
    }

    @Test
    public void getPotTotal(){
        Pot pot = new Pot();
        pot.addToPot(10);
        assertEquals(10,pot.getPotTotal());
    }
}
