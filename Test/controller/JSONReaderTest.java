package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JSONReaderTest {

  private static JSONReader reader;

  @BeforeAll
  static void setUp() {
    reader = new JSONReader();
    reader.parse("/texas_holdem.json");
  }
  @Test
  void getSuits() {
    Map<String, String> expectedSuits = new HashMap<>();
    expectedSuits.put("HEARTS", "hearts.png");
    expectedSuits.put("SPADES", "spades.png");
    expectedSuits.put("CLUBS", "clubs.png");
    expectedSuits.put("DIAMONDS", "diamonds.png");

    Map<String, String> actualSuits = reader.getSuits();
    assertEquals(expectedSuits, actualSuits);
  }

  @Test
  void getRanks() {
    Map<String, Integer> expectedRanks = new HashMap<>();
    expectedRanks.put("DEUCE", 2);
    expectedRanks.put("THREE", 3);
    expectedRanks.put("FOUR", 4);
    expectedRanks.put("FIVE", 5);
    expectedRanks.put("SIX", 6);
    expectedRanks.put("SEVEN", 7);
    expectedRanks.put("EIGHT", 8);
    expectedRanks.put("NINE", 9);
    expectedRanks.put("TEN", 10);
    expectedRanks.put("JACK", 11);
    expectedRanks.put("QUEEN", 12);
    expectedRanks.put("KING", 13);
    expectedRanks.put("ACE", 14);

    Map<String, Integer> actualRanks = reader.getRanks();
    assertEquals(expectedRanks, actualRanks);
  }

  @Test
  void getNumberOfPlayers() {
    int expectedNumberOfPlayers = 3;
    int actualNumberOfPlayers = reader.getNumberOfPlayers();
    assertEquals(expectedNumberOfPlayers, actualNumberOfPlayers);
  }

  @Test
  void getSuitNames() {
    List<String> expectedSuitNames = List.of(
        "CLUBS",
        "DIAMONDS",
        "HEARTS",
        "SPADES");

    List<String> actualSuitNames = reader.getSuitNames();
    assertEquals(expectedSuitNames, actualSuitNames);
  }

  @Test
  void getRankValues() {
    List<Integer> expectedRankValues = List.of(
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14);

    List<Integer> actualRankValues = reader.getRankValues();
    assertEquals(expectedRankValues, actualRankValues);
  }
}