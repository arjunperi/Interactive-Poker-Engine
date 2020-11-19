package model;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.HandCombiner;
import utility.HandEvaluator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HandEvaluatorTest {

  private Map<Integer, String> handStrengths;
  private JSONReader reader;


  @BeforeEach
  void setUp() {
    reader = new JSONReader();
    reader.parse("/cardSettings.json");
    handStrengths = reader.getStrengths();
  }

  @Test
  void testIsFiveCardHand() {
    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isFiveCardHand(hand1));
    hand1.add(card5);
    assertTrue(HandEvaluator.isFiveCardHand(hand1));

  }

  @Test
  void testIsFlush() {
    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(HandEvaluator.isFlush(hand1));

    Card card6 = new Card(10, "DIAMONDS");
    Hand hand2 = new Hand();
    hand2.add(card1);
    hand2.add(card2);
    hand2.add(card3);
    hand2.add(card4);
    hand2.add(card6);
    hand2 = hand2.sortHand();
    assertTrue(!HandEvaluator.isFlush(hand2));
  }


  @Test
  void testIsStraight() {
    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(11, "CLUBS");
    Card card4 = new Card(7, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(HandEvaluator.isStraight(hand1));

    Card card6 = new Card(1, "DIAMONDS");
    Hand hand2 = new Hand();
    hand2.add(card1);
    hand2.add(card2);
    hand2.add(card3);
    hand2.add(card4);
    hand2.add(card6);
    hand2 = hand2.sortHand();
    assertTrue(!HandEvaluator.isStraight(hand2));

    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(13, "CLUBS");
    Card card9 = new Card(12, "CLUBS");
    Card card10 = new Card(11, "CLUBS");
    Card card11 = new Card(10, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(HandEvaluator.isStraight(hand3));

    Card card12 = new Card(5, "CLUBS");
    Card card13 = new Card(4, "CLUBS");
    Card card14 = new Card(3, "CLUBS");
    Card card15 = new Card(2, "CLUBS");
    Hand hand4 = new Hand();
    hand4.add(card7);
    hand4.add(card12);
    hand4.add(card13);
    hand4.add(card14);
    hand4.add(card15);
    hand4 = hand4.sortHand();
    assertTrue(HandEvaluator.isStraight(hand4));
  }

  @Test
  void testIsStraightFlush() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(13, "CLUBS");
    Card card9 = new Card(12, "CLUBS");
    Card card10 = new Card(11, "CLUBS");
    Card card11 = new Card(10, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(HandEvaluator.isStraightFlush(hand3));
  }

  @Test
  void testIsFourOfAKind() {

    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(14, "SPADES");
    Card card10 = new Card(14, "HEARTS");
    Card card11 = new Card(10, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(HandEvaluator.isFourOfAKind(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(10, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(10, "HEARTS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(HandEvaluator.isFourOfAKind(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "CLUBS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(!HandEvaluator.isFourOfAKind(hand1));
  }

  @Test
  void testIsFullHouse() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(14, "SPADES");
    Card card10 = new Card(10, "HEARTS");
    Card card11 = new Card(10, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(HandEvaluator.isFullHouse(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(14, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(10, "HEARTS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(true == HandEvaluator.isFullHouse(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "CLUBS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isFullHouse(hand1));
  }


  @Test
  void testIsThreeOfAKind() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(14, "SPADES");
    Card card10 = new Card(10, "HEARTS");
    Card card11 = new Card(9, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(true == HandEvaluator.isThreeOfAKind(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(12, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(10, "HEARTS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(true == HandEvaluator.isThreeOfAKind(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "CLUBS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isThreeOfAKind(hand1));
  }


  @Test
  void testIsTwoPair() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(1, "SPADES");
    Card card10 = new Card(10, "HEARTS");
    Card card11 = new Card(10, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(true == HandEvaluator.isTwoPair(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(14, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(10, "HEARTS");
    Card card5 = new Card(3, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(true == HandEvaluator.isTwoPair(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "CLUBS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isTwoPair(hand1));
  }

  @Test
  void testIsPair() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(1, "SPADES");
    Card card10 = new Card(10, "HEARTS");
    Card card11 = new Card(9, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(true == HandEvaluator.isPair(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(12, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(10, "HEARTS");
    Card card5 = new Card(1, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(true == HandEvaluator.isPair(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "HEARTS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isPair(hand1));
  }

  @Test
  void testIsHighCard() {
    Card card7 = new Card(14, "CLUBS");
    Card card8 = new Card(12, "DIAMONDS");
    Card card9 = new Card(4, "SPADES");
    Card card10 = new Card(10, "HEARTS");
    Card card11 = new Card(1, "CLUBS");
    Hand hand3 = new Hand();
    hand3.add(card7);
    hand3.add(card8);
    hand3.add(card9);
    hand3.add(card10);
    hand3.add(card11);
    hand3 = hand3.sortHand();
    assertTrue(true == HandEvaluator.isHighCard(hand3));

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(12, "DIAMONDS");
    Card card3 = new Card(10, "SPADES");
    Card card4 = new Card(4, "HEARTS");
    Card card5 = new Card(5, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(true == HandEvaluator.isHighCard(hand1));

    card1 = new Card(8, "CLUBS");
    card2 = new Card(9, "CLUBS");
    card3 = new Card(2, "CLUBS");
    card4 = new Card(4, "CLUBS");
    card5 = new Card(10, "CLUBS");
    hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    assertTrue(false == HandEvaluator.isPair(hand1));
  }

  @Test
  void testHandStrength() {
    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(3, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    int[] ret = HandEvaluator.handStrength(hand1);
    assertEquals(5, ret[0]);
  }

  @Test
  void testGetBestHand() {
    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(13, "CLUBS");
    Card card3 = new Card(12, "CLUBS");
    Card card4 = new Card(11, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Card card6 = new Card(4, "SPADES");
    Card card7 = new Card(4, "CLUBS");

    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1.add(card6);
    hand1.add(card7);
    hand1 = hand1.sortHand();

    int n = hand1.getHandSize();
    int r = 5;
    // A temporary array to store all combination one by one

    Card data[] = new Card[r];

    // Print all combination using temprary array 'data[]'

    List<Hand> hands = HandCombiner.getAllHands(hand1);

    List<Hand> bestHands = HandEvaluator.getBestHands(hands);
    assertTrue(true == HandEvaluator.isFlush(bestHands.get(0)));
    assertEquals(4, bestHands.get(0).get(4).getRank());
  }

  @Test
  void testHighCardWin() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(9, "CLUBS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }


  @Test
  void testPairWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(10, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(9, "CLUBS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player1, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testPairWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(-1, "CLUBS");
    Card card4 = new Card(14, "CLUBS");
    Card card5 = new Card(14, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(9, "CLUBS");
    Card card8 = new Card(14, "CLUBS");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testTwoPairWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(10, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(8, "HEARTS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(14, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testTwoPairWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(2, "CLUBS");
    Card card3 = new Card(8, "HEARTS");
    Card card4 = new Card(10, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(2, "HEARTS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(14, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testThreeOfAKindWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(4, "HEARTS");
    Card card2 = new Card(10, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(4, "HEARTS");
    Card card8 = new Card(4, "SPADES");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testThreeOfAKindWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(12, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(9, "HEARTS");
    Card card4 = new Card(9, "DIAMONDS");
    Card card5 = new Card(14, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(2, "CLUBS");
    Card card8 = new Card(14, "CLUBS");
    Card card9 = new Card(14, "DIAMONDS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }


  @Test
  void testStraightWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(4, "CLUBS");
    Card card3 = new Card(4, "DIAMONDS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(14, "CLUBS");
    Card card7 = new Card(5, "CLUBS");
    Card card8 = new Card(4, "CLUBS");
    Card card9 = new Card(3, "CLUBS");
    Card card10 = new Card(2, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testStraightWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(14, "CLUBS");
    Card card2 = new Card(5, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(3, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(10, "CLUBS");
    Card card7 = new Card(11, "CLUBS");
    Card card8 = new Card(12, "CLUBS");
    Card card9 = new Card(13, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testFlushWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(7, "CLUBS");
    Card card4 = new Card(6, "CLUBS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(9, "CLUBS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "CLUBS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testFlushWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(9, "CLUBS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(4, "CLUBS");
    Card card10 = new Card(14, "CLUBS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testHFullHouseWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(8, "HEARTS");
    Card card8 = new Card(8, "SPADES");
    Card card9 = new Card(14, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testFullHouseWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(8, "SPADES");
    Card card3 = new Card(8, "DIAMONDS");
    Card card4 = new Card(14, "CLUBS");
    Card card5 = new Card(14, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(10, "CLUBS");
    Card card7 = new Card(10, "DIAMONDS");
    Card card8 = new Card(2, "CLUBS");
    Card card9 = new Card(10, "HEARTS");
    Card card10 = new Card(2, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testFourOfAKindWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(8, "HEARTS");
    Card card3 = new Card(8, "SPADES");
    Card card4 = new Card(8, "DIAMONDS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(8, "DIAMONDS");
    Card card8 = new Card(8, "HEARTS");
    Card card9 = new Card(14, "HEARTS");
    Card card10 = new Card(14, "CLUBS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player1, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testFourOfAKindWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(8, "DIAMONDS");
    Card card3 = new Card(8, "SPADES");
    Card card4 = new Card(8, "HEARTS");
    Card card5 = new Card(14, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "CLUBS");
    Card card7 = new Card(14, "SPADES");
    Card card8 = new Card(14, "DIAMONDS");
    Card card9 = new Card(14, "CLUBS");
    Card card10 = new Card(14, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testStraightFlushWin1() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(8, "HEARTS");
    Card card3 = new Card(8, "DIAMONDS");
    Card card4 = new Card(8, "SPADES");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(3, "CLUBS");
    Card card7 = new Card(4, "CLUBS");
    Card card8 = new Card(5, "CLUBS");
    Card card9 = new Card(14, "CLUBS");
    Card card10 = new Card(2, "CLUBS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testStraightFlushWin2() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(4, "CLUBS");
    Card card2 = new Card(3, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(14, "CLUBS");
    Card card5 = new Card(5, "CLUBS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(11, "CLUBS");
    Card card7 = new Card(10, "CLUBS");
    Card card8 = new Card(12, "CLUBS");
    Card card9 = new Card(13, "CLUBS");
    Card card10 = new Card(14, "CLUBS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(player2, HandEvaluator.getBestPlayers(playerList, false).get(0));
  }

  @Test
  void testIsTie() {
    Pot pot = new Pot();
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
    Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "DIAMONDS");
    Card card3 = new Card(12, "CLUBS");
    Card card4 = new Card(12, "DIAMONDS");
    Card card5 = new Card(10, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1 = hand1.sortHand();
    player1.setHand(hand1);
    player1.updateTotalHand();

    Card card6 = new Card(8, "DIAMONDS");
    Card card7 = new Card(9, "HEARTS");
    Card card8 = new Card(12, "DIAMONDS");
    Card card9 = new Card(12, "HEARTS");
    Card card10 = new Card(10, "HEARTS");
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1, player2)));

    assertEquals(2, HandEvaluator.getBestPlayers(playerList, false).size());
  }

  @Test
  void testGetBestPlayerHand() {
    Player player1 = new Player("Jimmy", 100, new CommunityCards(), new Pot());

    Card card1 = new Card(8, "CLUBS");
    Card card2 = new Card(9, "CLUBS");
    Card card3 = new Card(2, "CLUBS");
    Card card4 = new Card(4, "CLUBS");
    Card card5 = new Card(10, "CLUBS");
    Card card6 = new Card(14, "HEARTS");
    Hand hand1 = new Hand();
    hand1.add(card1);
    hand1.add(card2);
    hand1.add(card3);
    hand1.add(card4);
    hand1.add(card5);
    hand1.add(card6);
    player1.setHand(hand1);
    player1.updateTotalHand();
    assertTrue(HandEvaluator.isFlush(HandEvaluator.getBestPlayerHand(player1)));
  }

  @Test
  void testGetHandStrengthRank() {
    assertEquals(0, HandEvaluator.getHandStrengthRank("High Card"));
    assertEquals(1, HandEvaluator.getHandStrengthRank("Pair"));
    assertEquals(2, HandEvaluator.getHandStrengthRank("Two Pair"));
    assertEquals(3, HandEvaluator.getHandStrengthRank("Three Of A Kind"));
    assertEquals(4, HandEvaluator.getHandStrengthRank("Straight"));
    assertEquals(5, HandEvaluator.getHandStrengthRank("Flush"));
    assertEquals(6, HandEvaluator.getHandStrengthRank("Full House"));
    assertEquals(7, HandEvaluator.getHandStrengthRank("Four Of A Kind"));
    assertEquals(8, HandEvaluator.getHandStrengthRank("Straight Flush"));

  }
}


