package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PotTest {

    @Test
    void testAddToPot(){
        Pot pot = new Pot();
        Player player = new Player();
        assertEquals(0,pot.getPotTotal());
        pot.addToPot(10);
        assertEquals(10,pot.getPotTotal());
        pot.addToPot(10);
        assertEquals(20,pot.getPotTotal());
        pot.dispersePot(player);
        assertEquals(0,pot.getPotTotal());



    }
}
