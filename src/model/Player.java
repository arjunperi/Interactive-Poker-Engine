package model;

import controller.exceptions.ModelException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Superclass of AutoPlayer and InteractivePlayer
 * Represents a general player within the game
 */
public class Player extends CardRecipient {

  private final String playerName;
  private boolean hasFolded;
  private Hand playerHand;
  private final List<Card> discardedCardList;
  private CommunityCards communityCards;
  private Hand totalHand;
  private Hand totalVisibleHand;
  private Pot pot;
  protected boolean isInteractive;
  private int totalBetAmount;
  private int currentBetAmount;
  private final IntegerProperty moneyAmount;
  private static final String DUMMY_SUIT = "CLUBS";
  private static final int DUMMY_RANK = -1;
  private static final String INVALID_BET = "Cannot bet more money than you have! You only have $";

  public Player(String name, int startingAmount, CommunityCards communityCards, Pot pot) {
    super();
    this.pot = pot;
    playerName = name;
    moneyAmount = new SimpleIntegerProperty(startingAmount);
    playerHand = new Hand();
    this.communityCards = communityCards;
    totalHand = new Hand();
    totalVisibleHand = new Hand();
    discardedCardList = new ArrayList<>();
  }


  /**
   * Returns the amount of money the player currently has in the format of an integer property
   * @return IntegerProperty
   */
  public IntegerProperty getBankroll() {
    return moneyAmount;
  }

  /**
   * If the player has no money remaining, returns true. Returns false otherwise
   * @return Boolean
   */
  public boolean isSolvent() {
    return moneyAmount.getValue() > 0;
  }

  /**
   * If the player has not folded in the current round, returns true
   * @return Boolean
   */
  public boolean isActive() {
    return !hasFolded;
  }

  /**
   * Enters the player into a new game by resetting their solvency indicator and assigning the new community cards and pot for the new round
   * @param communityCards community cards for new round
   * @param pot pot for new round
   */
  public void enterNewGame(CommunityCards communityCards, Pot pot) {
    if (isSolvent()) {
      hasFolded = false;
    }
    this.communityCards = communityCards;
    this.pot = pot;
  }

  /**
   * Discards the card passed in from the player's hand
   * @param card Card to be discarded
   */
  public void discardCard(Card card) {
    playerHand.remove(card);
    discardedCardList.add(card);
  }


  /**
   * Returns the list of cards the player has discarded this round
   * @return List of Cards
   */
  public List<Card> getDiscardedCards() {
    return discardedCardList;
  }

  /**
   * Clears the list of cards the player has exchanged this round
   */
  public void clearDiscardedCards() {
    discardedCardList.clear();
  }

  /**
   * Returns the hand of the player
   * @return Hand object
   */
  public Hand getHand() {
    return playerHand;
  }


  /**
   * Sets the players hand to the hand passed in
   * @param hand Hand object
   */
  public void setHand(Hand hand) {
    playerHand = hand;
  }

  /**
   * Updates player's bankroll by adding the amount passed in
   * @param amount amount to add to bankroll
   */
  public void updateBankroll(int amount) {
    moneyAmount.setValue(moneyAmount.getValue() + amount);
  }

  public void updateTotalHand() {
    totalHand.clear();
    for (Card playerCard : playerHand.getCards()) {
      totalHand.add(playerCard);
    }
    for (Card communityCard : communityCards.getCommunityCardsList()) {
      totalHand.add(communityCard);
    }
    addDummyCards(totalHand);
    totalHand = totalHand.sortHand();
  }

  public Hand getTotalBackendVisibleHand() {
    totalVisibleHand.clear();
    for (Card card : totalHand.getCards()) {
      if (card.isBackEndVisible()) {
        totalVisibleHand.add(card);
      }
    }
    addDummyCards(totalVisibleHand);
    totalVisibleHand = totalVisibleHand.sortHand();
    return totalVisibleHand;
  }

  private void addDummyCards(Hand hand) {
    int handSize = hand.getHandSize();
    if (handSize < 5) {
      int fiveCardHandDifference = 5 - handSize;
      for (int i = 0; i < fiveCardHandDifference; i++) {
        Card dummyCard = new Card(DUMMY_RANK, DUMMY_SUIT);
        hand.add(dummyCard);
      }
    }
  }

  public Hand getTotalHand() {
    return totalHand;
  }

  public void bet(int amountToBet) {
    if (amountToBet <= moneyAmount.getValue()) {
      currentBetAmount = amountToBet;
      totalBetAmount = totalBetAmount + currentBetAmount;
      pot.addToPot(currentBetAmount);
      updateBankroll(currentBetAmount * -1);
    } else {
      throw new ModelException(
         INVALID_BET + getBankroll().getValue());
    }
  }

  public int getTotalBetAmount() {
    return totalBetAmount;
  }

  public void clearBetAmount() {
    totalBetAmount = 0;
    currentBetAmount = 0;
  }

  public void fold() {
    hasFolded = true;
  }

  public void call(int lastBet) {
    int callAmount = lastBet - totalBetAmount;
    if (callAmount >= moneyAmount.getValue()) {
      bet(moneyAmount.getValue());
    } else {
      bet(callAmount);
    }
  }

  public boolean isInteractive() {
    return !isInteractive;
  }


  public String toString() {
    return playerName;
  }

  public void receiveCard(Card card) {
    if (isInteractive) {
      card.setInteractivePlayerCard();
    }
    playerHand.add(card);
    addNewCards(card);
    updateTotalHand();
  }

  public void clearHand() {
    playerHand.clear();
  }
}
