package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayerTest extends DukeApplicationTest {

  private Player player;
  private Deck deck;
  private JSONReader reader;


  @BeforeEach
  void setUp() {
    reader = new JSONReader();
    reader.parse("/cardSettings.json");
    List<String> suitNames = new ArrayList<>();
    List<Integer> rankValues = new ArrayList<>();
    rankValues.addAll(reader.getRanks().keySet());
    suitNames.addAll(reader.getSuits().keySet());
    deck = new Deck(suitNames, rankValues);
  }

  @Test
  public void testGetBankroll() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    assertEquals(100, player.getBankroll().getValue());
  }

  @Test
  public void testUpdateBankroll() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    player.updateBankroll(-100);
    assertEquals(0, player.getBankroll().getValue());
  }

  @Test
  public void testIsSolvent() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    player.updateBankroll(-100);
    assertFalse(player.isSolvent());
  }

  @Test
  public void testFold() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    assertTrue(player.isActive());
    player.fold();
    assertFalse(player.isActive());
  }

  @Test
  public void testCall1() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    Player autoPlayer = new AutoPlayer("APlayer", 100, communityCards, pot);
    Player interactivePlayer = new InteractivePlayer("IPlayer", 100, communityCards, pot);
    PlayerList playerList = new StandardPlayerList(
        new ArrayList<>(List.of(autoPlayer, interactivePlayer)));

    interactivePlayer.bet(20);
    assertTrue(true == playerList.raiseMade(interactivePlayer));
    autoPlayer.call(playerList.getLastBet());
    playerList.raiseShift();
    assertEquals(80, autoPlayer.getBankroll().getValue());
    assertTrue(false == playerList.raiseMade(autoPlayer));

  }

  @Test
  public void testCall2() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    Player autoPlayer = new AutoPlayer("APlayer", 100, communityCards, pot);
    Player interactivePlayer = new InteractivePlayer("IPlayer", 100, communityCards, pot);
    PlayerList playerList = new StandardPlayerList(
        new ArrayList<>(List.of(autoPlayer, interactivePlayer)));

    interactivePlayer.bet(10);
    assertTrue(true == playerList.raiseMade(interactivePlayer));
    autoPlayer.bet(20);
    assertTrue(true == playerList.raiseMade(autoPlayer));

    interactivePlayer.call(playerList.getLastBet());
    playerList.raiseShift();
    assertEquals(80, interactivePlayer.getBankroll().getValue());
    assertTrue(false == playerList.raiseMade(interactivePlayer));

  }

  @Test
  public void testAddingToHand() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    Card testCard = new Card(2, "CLUBS");
    player.receiveCard(testCard);
    assertEquals(testCard, player.getHand().getCards().get(0));
  }

  @Test
  public void testDiscard() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    Card testCard = new Card(2, "CLUBS");
    player.receiveCard(testCard);
    assertEquals(testCard, player.getHand().getCards().get(0));
    player.discardCard(testCard);
    assertEquals(0, player.getHand().getCards().size());
  }


  @Test
  public void testToString() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    assertEquals("Player", player.toString());
  }

  @Test
  public void testInteractiveExchangeCards() {
    Dealer dealer = new Dealer(deck);
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    dealer.exchangeCards(player, (List.of(new Card(14, "DIAMOND"))));
    assertEquals(1, player.getHand().getCards().size());
  }

  @Test
  public void testAutoPlayerExchangeZeroCard() {
    Card card1 = deck.getTopCard();
    Dealer dealer = new Dealer(deck);
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    AutoPlayer player = new AutoPlayer("Player", 100, communityCards, pot);

    Card testCard = deck.StringToCard("11 CLUBS");
    Card testCard2 = deck.StringToCard("8 CLUBS");
    Card testCard3 = deck.StringToCard("10 HEARTS");
    Card testCard4 = deck.StringToCard("7 DIAMONDS");
    Card testCard5 = deck.StringToCard("14 SPADES");

    player.receiveCard(testCard);
    player.receiveCard(testCard2);
    player.receiveCard(testCard3);
    player.receiveCard(testCard4);
    player.receiveCard(testCard5);

    deck.removeAll(player.getHand().getCards());

    assertTrue(player.getHand().getCards().contains(testCard));
    List<Card> cardsToExchange = player.decideExchange();
    dealer.exchangeCards(player, cardsToExchange);
    assertTrue(player.getHand().getCards().contains(testCard));
    assertTrue(player.getHand().getCards().contains(testCard2));
    assertTrue(player.getHand().getCards().contains(testCard3));
    assertTrue(player.getHand().getCards().contains(testCard4));
    assertTrue(player.getHand().getCards().contains(testCard5));

  }

  @Test
  public void testAutoPlayerExchangeOneCard() {
    Card card1 = deck.getTopCard();
    Dealer dealer = new Dealer(deck);
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    AutoPlayer player = new AutoPlayer("Player", 100, communityCards, pot);

    Card testCard = deck.StringToCard("2 CLUBS");
    Card otherCard1 = deck.StringToCard("8 CLUBS");
    Card otherCard2 = deck.StringToCard("10 HEARTS");
    Card otherCard3 = deck.StringToCard("7 DIAMONDS");
    Card otherCard4 = deck.StringToCard("9 SPADES");

    player.receiveCard(testCard);
    player.receiveCard(otherCard1);
    player.receiveCard(otherCard2);
    player.receiveCard(otherCard3);
    player.receiveCard(otherCard4);

    deck.removeAll(player.getHand().getCards());

    assertTrue(player.getHand().getCards().contains(testCard));
    List<Card> cardsToExchange = player.decideExchange();
    dealer.exchangeCards(player, cardsToExchange);
    assertFalse(player.getHand().getCards().contains(testCard));
  }

  @Test
  public void testAutoPlayerExchangeTwoCards() {
    Card card1 = deck.getTopCard();
    Dealer dealer = new Dealer(deck);
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    AutoPlayer player = new AutoPlayer("Player", 100, communityCards, pot);

    Card testCard = deck.StringToCard("2 CLUBS");
    Card testCard2 = deck.StringToCard("6 CLUBS");
    Card otherCard2 = deck.StringToCard("10 HEARTS");
    Card otherCard3 = deck.StringToCard("7 DIAMONDS");
    Card otherCard4 = deck.StringToCard("9 SPADES");

    player.receiveCard(testCard);
    player.receiveCard(testCard2);
    player.receiveCard(otherCard2);
    player.receiveCard(otherCard3);
    player.receiveCard(otherCard4);

    deck.removeAll(player.getHand().getCards());

    assertTrue(player.getHand().getCards().contains(testCard));
    List<Card> cardsToExchange = player.decideExchange();
    dealer.exchangeCards(player, cardsToExchange);
    assertFalse(player.getHand().getCards().contains(testCard));
  }


  @Test
  public void testAutoPlayerBetAmount() {

    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    AutoPlayer player = new AutoPlayer("Player", 100, communityCards, pot);

    Card testCard = deck.StringToCard("8 CLUBS");
    Card testCard2 = deck.StringToCard("8 CLUBS");
    Card testCard3 = deck.StringToCard("8 HEARTS");
    Card otherCard3 = deck.StringToCard("8 DIAMONDS");
    Card otherCard4 = deck.StringToCard("9 SPADES");

    player.receiveCard(testCard);
    player.receiveCard(testCard2);
    player.receiveCard(testCard3);
    player.receiveCard(otherCard3);
    player.receiveCard(otherCard4);
    player.decideAction(0);
    assertEquals(40, pot.getPotTotal().getValue());
  }


  @Test
  public void testClearHand() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    Card testCard = new Card(2, "CLUBS");
    player.receiveCard(testCard);
    player.clearHand();
    assertEquals(0, player.getHand().getCards().size());
  }

  @Test
  public void testEnterNewGame() {
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    player = new Player("Player", 100, communityCards, pot);
    player.fold();
    assertFalse(player.isActive());
    player.enterNewGame(communityCards, pot);
    assertTrue(player.isActive());
  }

}
