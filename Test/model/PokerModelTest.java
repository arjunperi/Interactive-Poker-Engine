package model;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import utility.PropertiesFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerModelTest extends DukeApplicationTest {

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
    PlayerList playerList = new StandardPlayerList(
        new ArrayList<>(List.of(player1)));
    PokerModel pokerModel = new PokerModel(playerList, communityCards, dealer, modelProperties);
    pokerModel.backEndDeal(1);
    assertEquals(2, player1.getHand().getCards().size());
  }

}
