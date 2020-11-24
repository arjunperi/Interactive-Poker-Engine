package model;

import controller.exceptions.ModelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class is responsible for the flow of the game
 * of poker on the back end
 */
public class PokerModel {

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
  private final static String VISIBILITY_PROPERTY = "visibility";
  private final static String ACTION_PROPERTY = "action";
  private final static String COMMUNITY = "Community";
  private final static String PLAYERS = "Players";
  private final static String DEALING_ROUND_ERROR =  "Invalid dealing round inputs in file. Exit program and reconfigure file inputs";
  private final static String DECK_EMPTY_ERROR = "Deck will empty with specified number of players and cards being dealt in game.\nPlease choose a fewer number of players"
      + " or exit program and reconfigure file. \nThe number of total cards dealt can not be greater than 52.";
  private final static String TEN_CARDS_MAX_ERROR = "Can't deal more than 10 cards to a recipient. \nPlease exit exit program and reconfigure file.\nThe table "
      + "as well as each player can receive 10 cards maximum.";
  private final static String CONFIGURATION_ERROR = "File configuration error.\nPlease make sure the round totals, round recipients, visibility specifications, and action specifications line up.";

  public PokerModel(PlayerList players, CommunityCards communityCards, Dealer dealer,
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

  /**
   * This method sets the deal stats for the current round
   * @param currentRound - the number corresponding to what round the game
   *                     of poker is currently in
   */
  public void dealStats(int currentRound) {
    recipient = modelProperties.getProperty(String.valueOf(currentRound));
    String[] roundVisibility = modelProperties.getProperty(VISIBILITY_PROPERTY + currentRound).split(",");
    visibilityList.clear();
    faceDownCards = Integer.parseInt(roundVisibility[0]);
    populateVisibilityList(faceDownCards, false);
    faceUpCards = Integer.parseInt(roundVisibility[1]);
    numberOfCards = faceDownCards + faceUpCards;
    populateVisibilityList(faceUpCards, true);
  }

  /**
   * Method returns the string of the type of round the round number is
   * @param roundNumber - the round number that we wish to determine the type of round it is
   * @return a string of the round type from the properties file based on the round number.
   * Either a dealing round or an exchange round
   */
  public String getAction(int roundNumber) {
    return modelProperties.getProperty(ACTION_PROPERTY + roundNumber);
  }

  private void populateVisibilityList(int numberOfCards, boolean isVisible) {
    for (int i = 0; i < numberOfCards; i++) {
      visibilityList.add(isVisible);
    }
  }

  /**
   * Deals correctly in the back end to the correct card
   * recipient based on the current round number
   * @param currentRound - the number corresponding to what round the game
   *    *                     of poker is currently in
   */
  public void backEndDeal(int currentRound) {
    dealStats(currentRound);
    if (recipient.equals(COMMUNITY)) {
      dealer.dealCards(communityCards, visibilityList);
    } else if (recipient.equals(PLAYERS)) {
      for (Player player : activePlayerList) {
        dealer.dealCards(player, visibilityList);
      }
    } else {
      throw new ModelException(DEALING_ROUND_ERROR);
    }
  }

  /**
   * This method returns a string of the recipient
   * @return a string of the card recipient type
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * This method throws an exception if the calculated amount of cards needed exceed 52
   * based on the number of auto players and based on the amount of rounds
   * @param numAutoPlayers - the number of autoplayers in the hand
   * @param totalRounds - the total number of rounds in one hand of poker
   */
  public void checkInvalidNumberOfCards(int numAutoPlayers, int totalRounds) {
    try{
      for (int i = 1; i <= totalRounds; i++) {
        dealStats(i);
        if (recipient.equals(PLAYERS)) {
          totalGameCards += ((numAutoPlayers + 1) * numberOfCards);
          totalPlayerCards += numberOfCards;
        } else if (recipient.equals(COMMUNITY)) {
          totalGameCards += numberOfCards;
          totalCommunityCards += numberOfCards;
        }
      }
      if (totalGameCards > 52) {
        throw new ModelException(DECK_EMPTY_ERROR);
      }
      if (totalCommunityCards > 10 || totalPlayerCards > 10) {
        throw new ModelException(TEN_CARDS_MAX_ERROR);
      }
    }
    catch (NullPointerException e){
      throw new ModelException(CONFIGURATION_ERROR);
    }
  }
}
