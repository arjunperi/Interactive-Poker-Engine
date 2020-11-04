package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JSONReaderTest {

  private static JSONReader reader;

  @BeforeAll
  static void setUp() {
    reader = new JSONReader();
    reader.parse("resources/texas_holdem.json");
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
    List<String> expectedRanks = List.of(
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
        "ACE");
    List<String> actualRanks = reader.getRanks();
    assertEquals(expectedRanks, actualRanks);
  }
}