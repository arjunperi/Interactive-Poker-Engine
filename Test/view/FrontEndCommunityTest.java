package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontEndCommunityTest {

  @Test
  public void testFrontEndCommunityCreation() {
    GameDisplayRecipient player = new FrontEndCommunity(10, 10);
    assertEquals(player.getX(), 10);
  }
}
