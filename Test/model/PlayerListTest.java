package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayerListTest extends DukeApplicationTest {

  @Test
  public void testStudBettingOrder() {
    Pot pot = new Pot();
    CommunityCards communityCards = new CommunityCards();
    Player player1 = new Player("Jimmy", 100, communityCards, pot);
    Player player2 = new Player("Dinna", 100, communityCards, pot);

    Card card1 = new Card(8, "CLUBS");
    card1.makeBackEndVisible();
    Card card2 = new Card(8, "SPADES");
    card2.makeBackEndVisible();
    Card card3 = new Card(8, "DIAMONDS");
    card3.makeBackEndVisible();
    Card card4 = new Card(14, "CLUBS");
    card4.makeBackEndVisible();
    Card card5 = new Card(14, "HEARTS");
    card5.makeBackEndVisible();
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
    card6.makeBackEndVisible();
    Card card7 = new Card(10, "DIAMONDS");
    card7.makeBackEndVisible();
    Card card8 = new Card(2, "CLUBS");
    card8.makeBackEndVisible();
    Card card9 = new Card(10, "HEARTS");
    card9.makeBackEndVisible();
    Card card10 = new Card(2, "HEARTS");
    card10.makeBackEndVisible();
    Hand hand2 = new Hand();
    hand2.add(card6);
    hand2.add(card7);
    hand2.add(card8);
    hand2.add(card9);
    hand2.add(card10);
    hand2 = hand2.sortHand();
    player2.setHand(hand2);
    player2.updateTotalHand();

    PlayerList studList = new StudPlayerList(new ArrayList<>(List.of(player1, player2)));
    studList.updateActivePlayers();
    assertEquals(player2, studList.getActivePlayers().get(0));
  }


  @Test
  public void testUpdateStartingRoundOrder() {
    Pot pot = new Pot();
    CommunityCards communityCards = new CommunityCards();
    Player player1 = new Player("Jimmy", 100, communityCards, pot);
    Player player2 = new Player("Dinna", 100, communityCards, pot);
    Player player3 = new Player("Yasser", 100, communityCards, pot);
    PlayerList playerList = new StandardPlayerList(
        new ArrayList<>(List.of(player1, player2, player3)));
    assertEquals(player1, playerList.getActivePlayers().get(0));
    playerList.updateStartingRoundOrder();
    playerList.resetActivePlayers();
    assertEquals(player2, playerList.getActivePlayers().get(0));
    playerList.updateStartingRoundOrder();
    playerList.resetActivePlayers();
    assertEquals(player3, playerList.getActivePlayers().get(0));
  }
}
