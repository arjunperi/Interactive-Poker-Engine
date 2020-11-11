package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class RoundManagerTest {
    @Test
    public void testWinOnFold(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player1 = new Player("Jimmy", 100, communityCards, pot);
        Player player2 = new Player("Dinna", 100, communityCards, pot);
        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));
        player2.bet(50);
        player2.fold();
        RoundManager roundManager = new RoundManager(pot);
        roundManager.checkOnePlayerRemains(playerList);
        assertEquals(player1.getBankroll(), 150);
    }

    @Test
    public void testWinOnShowdown(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player1 = new Player("Jimmy", 100, communityCards, pot);
        Player player2 = new Player("Dinna", 100, communityCards, pot);
        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));
        player2.bet(50);

        Card card1 = new Card(8, "CLUBS");
        Card card2 = new Card(8, "SPADES");
        Card card3 = new Card(8, "DIAMONDS");
        Card  card4 = new Card(14, "CLUBS");
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
        Card  card9 = new Card(10, "HEARTS");
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

        player1.bet(50);
        RoundManager roundManager = new RoundManager(pot);
        roundManager.checkShowDown(playerList, 5, 5);
        assertEquals(player2.getBankroll(), 150);
    }

    @Test
    public void testTieOnShowdown(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player1 = new Player("Jimmy", 100, communityCards, pot);
        Player player2 = new Player("Dinna", 100, communityCards, pot);
        PlayerList playerList = new StandardPlayerList(new ArrayList<>(List.of(player1,player2)));
        player2.bet(50);

        Card card1 = new Card(8, "CLUBS");
        Card card2 = new Card(8, "SPADES");
        Card card3 = new Card(8, "DIAMONDS");
        Card  card4 = new Card(14, "CLUBS");
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

        Hand hand2 = new Hand();
        hand2.add(card1);
        hand2.add(card2);
        hand2.add(card3);
        hand2.add(card4);
        hand2.add(card5);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        player1.bet(50);
        RoundManager roundManager = new RoundManager(pot);
        roundManager.checkShowDown(playerList, 5, 5);
        assertEquals(player1.getBankroll(), 100);
        assertEquals(player2.getBankroll(), 100);
    }
}
