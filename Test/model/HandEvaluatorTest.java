package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandEvaluatorTest {

    @Test
    void testIsFlush() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isFlush(hand1));

        Card card6 = new Card(10, Suit.DIAMONDS);
        Hand hand2 = new Hand();
        hand2.add(card1);
        hand2.add(card2);
        hand2.add(card3);
        hand2.add(card4);
        hand2.add(card6);
        hand2 = hand2.sortHand();
        assertTrue(false == evaluator.isFlush(hand2));
    }


    @Test
    void testIsStraight() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(11, Suit.CLUBS);
        Card card4 = new Card(7, Suit.CLUBS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isStraight(hand1));

        Card card6 = new Card(1, Suit.DIAMONDS);
        Hand hand2 = new Hand();
        hand2.add(card1);
        hand2.add(card2);
        hand2.add(card3);
        hand2.add(card4);
        hand2.add(card6);
        hand2 = hand2.sortHand();
        assertTrue(false == evaluator.isStraight(hand2));

        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(13, Suit.CLUBS);
        Card card9 = new Card(12, Suit.CLUBS);
        Card card10 = new Card(11, Suit.CLUBS);
        Card card11 = new Card(10, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isStraight(hand3));


        Card card12 = new Card(5, Suit.CLUBS);
        Card card13 = new Card(4, Suit.CLUBS);
        Card card14 = new Card(3, Suit.CLUBS);
        Card card15 = new Card(2, Suit.CLUBS);
        Hand hand4 = new Hand();
        hand4.add(card7);
        hand4.add(card12);
        hand4.add(card13);
        hand4.add(card14);
        hand4.add(card15);
        hand4 = hand4.sortHand();
        assertTrue(true == evaluator.isStraight(hand4));
    }

    @Test
    void testIsStraightFlush() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(13, Suit.CLUBS);
        Card card9 = new Card(12, Suit.CLUBS);
        Card card10 = new Card(11, Suit.CLUBS);
        Card card11 = new Card(10, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isStraightFlush(hand3));
    }

    @Test
    void testIsFourOfAKind() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card card9 = new Card(14, Suit.SPADES);
        Card card10 = new Card(14, Suit.HEARTS);
        Card card11 = new Card(10, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isFourOfAKind(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(10, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(10, Suit.HEARTS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isFourOfAKind(hand1));


        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.CLUBS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isFourOfAKind(hand1));
    }

    @Test
    void testIsFullHouse() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card card9 = new Card(14, Suit.SPADES);
        Card card10 = new Card(10, Suit.HEARTS);
        Card card11 = new Card(10, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isFullHouse(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(14, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(10, Suit.HEARTS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isFullHouse(hand1));

        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.CLUBS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isFullHouse(hand1));
    }


    @Test
    void testIsThreeOfAKind() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card card9 = new Card(14, Suit.SPADES);
        Card card10 = new Card(10, Suit.HEARTS);
        Card card11 = new Card(9, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isThreeOfAKind(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(12, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(10, Suit.HEARTS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isThreeOfAKind(hand1));

        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.CLUBS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isThreeOfAKind(hand1));
    }


    @Test
    void testIsTwoPair() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card card9 = new Card(1, Suit.SPADES);
        Card card10 = new Card(10, Suit.HEARTS);
        Card card11 = new Card(10, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isTwoPair(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(14, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(10, Suit.HEARTS);
        Card card5 = new Card(3, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isTwoPair(hand1));

        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.CLUBS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isTwoPair(hand1));
    }

    @Test
    void testIsPair() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card card9 = new Card(1, Suit.SPADES);
        Card card10 = new Card(10, Suit.HEARTS);
        Card card11 = new Card(9, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isPair(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(12, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(10, Suit.HEARTS);
        Card card5 = new Card(1, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isPair(hand1));

        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.HEARTS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isPair(hand1));
    }

    @Test
    void testIsHighCard() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card7 = new Card(14, Suit.CLUBS);
        Card card8 = new Card(12, Suit.DIAMONDS);
        Card card9 = new Card(4, Suit.SPADES);
        Card card10 = new Card(10, Suit.HEARTS);
        Card card11 = new Card(1, Suit.CLUBS);
        Hand hand3 = new Hand();
        hand3.add(card7);
        hand3.add(card8);
        hand3.add(card9);
        hand3.add(card10);
        hand3.add(card11);
        hand3 = hand3.sortHand();
        assertTrue(true == evaluator.isHighCard(hand3));

        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(12, Suit.DIAMONDS);
        Card card3 = new Card(10, Suit.SPADES);
        Card card4 = new Card(4, Suit.HEARTS);
        Card card5 = new Card(5, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(true == evaluator.isHighCard(hand1));

        card1 = new Card(8, Suit.CLUBS);
        card2 = new Card(9, Suit.CLUBS);
        card3 = new Card(2, Suit.CLUBS);
        card4 = new Card(4, Suit.CLUBS);
        card5 = new Card(10, Suit.CLUBS);
        hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        assertTrue(false == evaluator.isPair(hand1));
    }

    @Test
    void testHandStrength() {
        HandEvaluator evaluator = new HandEvaluator();
        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(3, Suit.CLUBS);
        Card card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        int[] ret = evaluator.handStrength(hand1);
        assertEquals(5, ret[0]);
    }

    @Test
    void testGetBestHand() {
        HandEvaluator evaluator = new HandEvaluator();
        HandCombiner comb = new HandCombiner();
        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(13, Suit.CLUBS);
        Card card3 = new Card(12, Suit.CLUBS);
        Card card4 = new Card(11, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Card card6 = new Card(4, Suit.SPADES);
        Card card7 = new Card(4, Suit.CLUBS);

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
        comb.clearAllHands();

        comb.makeAllPossibleHands(hand1, data, 0, n - 1, 0);
        ArrayList<Hand> hands = comb.getAllHands(hand1);

        List<Hand> bestHands = evaluator.getBestHands(hands);
        assertTrue(true == evaluator.isFlush(bestHands.get(0)));
        assertEquals(4,bestHands.get(0).get(4).getRank());
    }

    @Test
    void testHighCardWin(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(9, Suit.CLUBS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }


    @Test
    void testPairWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(10, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(9, Suit.CLUBS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player1,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testPairWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(-1, Suit.CLUBS);
        Card  card4 = new Card(14, Suit.CLUBS);
        Card card5 = new Card(14, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(9, Suit.CLUBS);
        Card card8 = new Card(14, Suit.CLUBS);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testTwoPairWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(10, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(8, Suit.HEARTS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(14, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testTwoPairWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(2, Suit.CLUBS);
        Card card3 = new Card(8, Suit.HEARTS);
        Card  card4 = new Card(10, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(2, Suit.HEARTS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(14, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testThreeOfAKindWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(4, Suit.HEARTS);
        Card card2 = new Card(10, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(4, Suit.HEARTS);
        Card card8 = new Card(4, Suit.SPADES);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testThreeOfAKindWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(12, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(9, Suit.HEARTS);
        Card  card4 = new Card(9, Suit.DIAMONDS);
        Card card5 = new Card(14, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(2, Suit.CLUBS);
        Card card8 = new Card(14, Suit.CLUBS);
        Card  card9 = new Card(14, Suit.DIAMONDS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }


    @Test
    void testStraightWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(4, Suit.CLUBS);
        Card card3 = new Card(4, Suit.DIAMONDS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(14, Suit.CLUBS);
        Card card7 = new Card(5, Suit.CLUBS);
        Card card8 = new Card(4, Suit.CLUBS);
        Card  card9 = new Card(3, Suit.CLUBS);
        Card card10 = new Card(2, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testStraightWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(5, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(3, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(10, Suit.CLUBS);
        Card card7 = new Card(11, Suit.CLUBS);
        Card card8 = new Card(12, Suit.CLUBS);
        Card  card9 = new Card(13, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testFlushWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(7, Suit.CLUBS);
        Card  card4 = new Card(6, Suit.CLUBS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(9, Suit.CLUBS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.CLUBS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testFlushWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(9, Suit.CLUBS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(4, Suit.CLUBS);
        Card card10 = new Card(14, Suit.CLUBS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testHFullHouseWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(4, Suit.CLUBS);
        Card card5 = new Card(10, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(8, Suit.HEARTS);
        Card card8 = new Card(8, Suit.SPADES);
        Card  card9 = new Card(14, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testFullHouseWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(8, Suit.SPADES);
        Card card3 = new Card(8, Suit.DIAMONDS);
        Card  card4 = new Card(14, Suit.CLUBS);
        Card card5 = new Card(14, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(10, Suit.CLUBS);
        Card card7 = new Card(10, Suit.DIAMONDS);
        Card card8 = new Card(2, Suit.CLUBS);
        Card  card9 = new Card(10, Suit.HEARTS);
        Card card10 = new Card(2, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testFourOfAKindWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(8, Suit.HEARTS);
        Card card3 = new Card(8, Suit.SPADES);
        Card  card4 = new Card(8, Suit.DIAMONDS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(8, Suit.DIAMONDS);
        Card card8 = new Card(8, Suit.HEARTS);
        Card  card9 = new Card(14, Suit.HEARTS);
        Card card10 = new Card(14, Suit.CLUBS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player1,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testFourOfAKindWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(8, Suit.DIAMONDS);
        Card card3 = new Card(8, Suit.SPADES);
        Card  card4 = new Card(8, Suit.HEARTS);
        Card card5 = new Card(14, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.CLUBS);
        Card card7 = new Card(14, Suit.SPADES);
        Card card8 = new Card(14, Suit.DIAMONDS);
        Card  card9 = new Card(14, Suit.CLUBS);
        Card card10 = new Card(14, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testStraightFlushWin1(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(8, Suit.HEARTS);
        Card card3 = new Card(8, Suit.DIAMONDS);
        Card  card4 = new Card(8, Suit.SPADES);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(3, Suit.CLUBS);
        Card card7 = new Card(4, Suit.CLUBS);
        Card card8 = new Card(5, Suit.CLUBS);
        Card  card9 = new Card(14, Suit.CLUBS);
        Card card10 = new Card(2, Suit.CLUBS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testStraightFlushWin2(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(4, Suit.CLUBS);
        Card card2 = new Card(3, Suit.CLUBS);
        Card card3 = new Card(2, Suit.CLUBS);
        Card  card4 = new Card(14, Suit.CLUBS);
        Card card5 = new Card(5, Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(11, Suit.CLUBS);
        Card card7 = new Card(10, Suit.CLUBS);
        Card card8 = new Card(12, Suit.CLUBS);
        Card  card9 = new Card(13, Suit.CLUBS);
        Card card10 = new Card(14, Suit.CLUBS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(player2,evaluator.getBestPlayers(playerList,false).get(0));
    }

    @Test
    void testIsTie(){
        HandEvaluator evaluator = new HandEvaluator();
        Pot pot = new Pot();
        Player player1 = new Player("Jimmy", 100, new CommunityCards(), pot);
        Player player2 = new Player("Dinna", 100, new CommunityCards(), pot);


        Card card1 = new Card(8, Suit.CLUBS);
        Card card2 = new Card(9, Suit.DIAMONDS);
        Card card3 = new Card(12, Suit.CLUBS);
        Card  card4 = new Card(12, Suit.DIAMONDS);
        Card card5 = new Card(10, Suit.HEARTS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        player1.setHand(hand1);
        player1.updateTotalHand();

        Card card6 = new Card(8, Suit.DIAMONDS);
        Card card7 = new Card(9, Suit.HEARTS);
        Card card8 = new Card(12, Suit.DIAMONDS);
        Card  card9 = new Card(12, Suit.HEARTS);
        Card card10 = new Card(10, Suit.HEARTS);
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));

        assertEquals(2,evaluator.getBestPlayers(playerList,false).size());
    }
}

