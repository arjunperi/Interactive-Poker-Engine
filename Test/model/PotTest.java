package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotTest {

  @Test
  void testAddToPot() {
    Pot pot = new Pot();
    CommunityCards communityCards = new CommunityCards();
    Player player = new Player("Arjun", 100, communityCards, pot);
    assertEquals(0, pot.getPotTotal().getValue());
    pot.addToPot(10);
    assertEquals(10, pot.getPotTotal().getValue());
    pot.addToPot(10);
    assertEquals(20, pot.getPotTotal().getValue());
    pot.dispersePot(player, pot.getPotTotal().getValue());
    pot.clearPot();
    assertEquals(0, pot.getPotTotal().getValue());
  }

  @Test
  public void dispersePot() {
    Pot pot = new Pot();
    CommunityCards communityCards = new CommunityCards();
    Player player = new Player("Arjun", 100, communityCards, pot);
    pot.addToPot(10);
    pot.dispersePot(player, pot.getPotTotal().getValue());
    assertEquals(110, player.getBankroll().getValue());
  }

  @Test
  public void getPotTotal() {
    Pot pot = new Pot();
    pot.addToPot(10);
    assertEquals(10, pot.getPotTotal().getValue());
  }
}
