package view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommunityCardGridTest {
  @Test
  void testGetLocation() {
    CommunityCardGrid communityCardGrid = new CommunityCardGrid(300, 300);
    int expectedCommunityCardGridX = 185;
    int expectedCommunityCardGridY = 245;

    int actualCommunityCardGridX = (int) communityCardGrid.getCommunityCardX();
    int actualCommunityCardGridY = (int) communityCardGrid.getCommunityCardY();

    assertEquals(expectedCommunityCardGridX, actualCommunityCardGridX);
    assertEquals(expectedCommunityCardGridY, actualCommunityCardGridY);

  }

}