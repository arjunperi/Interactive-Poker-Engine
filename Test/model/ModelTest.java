package model;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import utility.PropertiesFileReader;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest extends DukeApplicationTest {

  @Test
  public void testBackEndDeal(){
    Properties modelProperties = PropertiesFileReader.getPropertyFile("Holdem");
    CommunityCards communityCards = new CommunityCards();
    Pot pot = new Pot();
    JSONReader reader = new JSONReader();
    reader.parse("/cardSettings.json");
    Deck deck = new Deck(reader.getSuitNames(), reader.getRankValues());
    Dealer dealer = new Dealer(deck);
    Player player1 = new Player("Jimmy", 100, communityCards, pot);
    Card card1 = deck.peekTopCard();
    PlayerList playerList = new StandardPlayerList(
        new ArrayList<>(List.of(player1)));
    Model model = new Model(playerList, communityCards, dealer, modelProperties);
    model.backEndDeal(1);
    assertTrue(player1.getHand().getCards().contains(card1));
  }

}
