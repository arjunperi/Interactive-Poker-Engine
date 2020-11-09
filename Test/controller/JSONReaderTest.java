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
    List<String> expectedSuits = List.of(
        "HEARTS",
        "SPADES",
        "CLUBS",
        "DIAMONDS");
    List<String> actualSuits = reader.getSuits();
    assertEquals(expectedSuits, actualSuits);
  }

  @Test
  void getRanks() {
    Map<String, Integer> expectedRanks = new HashMap<>();
    /*List<String> expectedRanks = List.of(
        "DEUCE",
        "THREE",
        "FOUR",
        "FIVE",
        "SIX",
        "SEVEN",
        "EIGHT",
        "NINE",
        "TEN",
        "JACK",
        "QUEEN",
        "KING",
        "ACE");*/
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
}