package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class StudPlayerListTest {
    @Test
    public void testStudBettingOrder(){
        Pot pot = new Pot();
        CommunityCards communityCards = new CommunityCards();
        Player player1 = new Player("Jimmy", 100, communityCards, pot);
        Player player2 = new Player("Dinna", 100, communityCards, pot);

        Card card1 = new Card(8, Suit.CLUBS);
        card1.makeVisible();
        Card card2 = new Card(8, Suit.SPADES);
        card2.makeVisible();
        Card card3 = new Card(8, Suit.DIAMONDS);
        card3.makeVisible();
        Card  card4 = new Card(14, Suit.CLUBS);
        card4.makeVisible();
        Card card5 = new Card(14, Suit.HEARTS);
        card5.makeVisible();
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
        card6.makeVisible();
        Card card7 = new Card(10, Suit.DIAMONDS);
        card7.makeVisible();
        Card card8 = new Card(2, Suit.CLUBS);
        card8.makeVisible();
        Card  card9 = new Card(10, Suit.HEARTS);
        card9.makeVisible();
        Card card10 = new Card(2, Suit.HEARTS);
        card10.makeVisible();
        Hand hand2 = new Hand();
        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);
        hand2 = hand2.sortHand();
        player2.setHand(hand2);
        player2.updateTotalHand();

        PlayerList studList = new StudPlayerList(new ArrayList<>(List.of(player1,player2)));
        studList.updateActivePlayers();
        assertEquals(player2, studList.getActivePlayers().get(0));
    }
}
