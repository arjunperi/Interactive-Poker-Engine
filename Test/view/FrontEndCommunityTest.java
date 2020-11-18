package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FrontEndCommunityTest {

  @Test
  public void testFrontEndCommunityCreation() {
    GameDisplayRecipient player = new FrontEndCommunity(10, 10);
    assertEquals(player.getX(), 10);
  }
}
