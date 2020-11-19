package model;

//not sure if abstract class is the best way to handle this hierarchy

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Model {

  protected Dealer dealer;
  protected PlayerList playerList;
  protected CommunityCards communityCards;
  protected List<Player> activePlayerList;
  protected int numberOfCards;
  protected String recipient;
  protected int faceUpCards;
  protected int faceDownCards;
  protected List<Boolean> visibilityList;
  protected Properties modelProperties;
  private int totalPlayerCards;
  private int totalCommunityCards;
  private int totalGameCards;


  public Model(PlayerList players, CommunityCards communityCards, Dealer dealer,
      Properties modelProperties) {
    this.communityCards = communityCards;
    activePlayerList = players.getActivePlayers();
    this.dealer = dealer;
    this.modelProperties = modelProperties;
    visibilityList = new ArrayList<>();
    totalCommunityCards = 0;
    totalGameCards = 0;
    totalGameCards = 0;
  }

  public void dealStats(int currentRound) {
    recipient = modelProperties.getProperty(String.valueOf(currentRound));

    String[] roundVisibility = modelProperties.getProperty("visibility" + currentRound).split(",");
    visibilityList.clear();
    faceDownCards = Integer.parseInt(roundVisibility[0]);
    populateVisibilityList(faceDownCards, false);
    faceUpCards = Integer.parseInt(roundVisibility[1]);
    numberOfCards = faceDownCards + faceUpCards;
    populateVisibilityList(faceUpCards, true);
  }

  public String getAction(int roundNumber) {
    String action = modelProperties.getProperty("action" + roundNumber);
    return action;
  }

  private void populateVisibilityList(int numberOfCards, boolean isVisible) {
    for (int i = 0; i < numberOfCards; i++) {
      visibilityList.add(isVisible);
    }
  }

  public void backEndDeal(int currentRound) {
    //TODO: find a way around this conditional
    dealStats(currentRound);
    if (recipient.equals("Community")) {
      dealer.dealCards(communityCards, visibilityList);
    } else if (recipient.equals("Players")) {
      for (Player player : activePlayerList) {
        dealer.dealCards(player, visibilityList);
      }
    } else {
      throw new ModelException(
          "Invalid dealing round inputs in file. Exit program and reconfigure file inputs");
    }
  }

  public String getRecipient() {
    return recipient;
  }


  public void checkInvalidNumberOfCards(int numAutoPlayers, int totalRounds) {
    for (int i = 1; i <= totalRounds; i++) {
      dealStats(i);
      if (recipient.equals("Players")) {
        totalGameCards += ((numAutoPlayers + 1) * numberOfCards);
        totalPlayerCards += numberOfCards;
      } else if (recipient.equals("Community")) {
        totalGameCards += numberOfCards;
        totalCommunityCards += numberOfCards;
      }
    }
    if (totalGameCards > 52) {
      throw new ModelException("Deck will empty");
    }
    if (totalCommunityCards > 10 || totalPlayerCards > 10) {
      throw new ModelException("Can't deal more than 10 cards to a location");
    }
  }
}
