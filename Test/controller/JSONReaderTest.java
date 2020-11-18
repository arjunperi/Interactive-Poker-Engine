package controller;

import static org.junit.jupiter.api.Assertions.*;

import controller.exceptions.SetUpException;
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
    reader.parse("/cardSettings.json");
  }

  @Test
  void getSuits() {
    Map<String, String> expectedSuits = new HashMap<>();
    expectedSuits.put("HEARTS", "/heart-suit.png");
    expectedSuits.put("SPADES", "/spade-suit.png");
    expectedSuits.put("CLUBS", "/club-suit.png");
    expectedSuits.put("DIAMONDS", "/diamond-suit.png");

    Map<String, String> actualSuits = reader.getSuits();
    assertEquals(expectedSuits, actualSuits);
  }

  @Test
  void getRanks() {
    Map<Integer, String> expectedRanks = new HashMap<>();
    expectedRanks.put(2, "DEUCE");
    expectedRanks.put(3, "THREE");
    expectedRanks.put(4, "FOUR");
    expectedRanks.put(5, "FIVE");
    expectedRanks.put(6, "SIX");
    expectedRanks.put(7, "SEVEN");
    expectedRanks.put(8, "EIGHT");
    expectedRanks.put(9, "NINE");
    expectedRanks.put(10, "TEN");
    expectedRanks.put(11, "JACK");
    expectedRanks.put(12, "QUEEN");
    expectedRanks.put(13, "KING");
    expectedRanks.put(14, "ACE");

    Map<Integer, String> actualRanks = reader.getRanks();
    assertEquals(expectedRanks, actualRanks);
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

  @Test
  void invalidFileNoSuitsTest() {
    assertThrows(SetUpException.class, () -> reader.parse("incomplete-file-no-suits.json"));
  }
}