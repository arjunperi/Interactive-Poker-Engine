package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utility.HandCombiner;
import utility.HandEvaluator;

/**
 * This class represents a computer-controlled poker player
 */
public class AutoPlayer extends Player {

  private static final int DEFAULTBETAMOUNT = 10;
  private static final int PLAY_THRESHOLD = 10;
  private static final int HAND_RANK_THRESHOLD = 0;
  private static final int EXCHANGE_THRESHOLD = 7;
  private static final int MAX_EXCHANGE = 3;
  private static final int BET_INCREMENT = 5;
  private String action;


  public AutoPlayer(String name, int startingAmount, CommunityCards communityCards, Pot pot) {
    super(name, startingAmount, communityCards, pot);
    isInteractive = false;
  }

  /**
   * Decides what action the AutoPlayer should make during a betting round using an algorithm based on hand strength.
   * After deciding, it carries out this action.
   * @param lastBet - the last bet made in the game, needed in order to complete bet or call action
   */
  public void decideAction(int lastBet) {
    boolean isHighEnough = false;
    Hand bestHand = HandEvaluator.getBestHands(HandCombiner.getAllHands(this.getTotalHand()))
        .get(0);
    int handStrength = HandEvaluator.handStrength(bestHand)[0];
    for (int rank : HandEvaluator.handStrength(bestHand)) {
      if (rank > PLAY_THRESHOLD) {
        isHighEnough = true;
      }
    }
    if (handStrength > HAND_RANK_THRESHOLD || isHighEnough) {
      if (lastBet == 0) {
        int betAmount = computerBetAmount(DEFAULTBETAMOUNT + (BET_INCREMENT * (handStrength - 1)));
        bet(betAmount);
        action = " bet " + betAmount;
      } else {
        call(lastBet);
        action = " called";
      }
    } else {
      fold();
      action = " folded";
    }
  }

  /**
   * Returns the last action that the player decided
   * @return String of action
   */
  public String getAction() {
    return action;
  }

  /**
   *  Decides if the player should exchange cards or not based on algorithm. The AutoPlayer chooses to exchange
   *  if they do not have at least a pair (hand of rank 2). In this case, the AutoPlayer attempts to exchange up to 3 cards with values less than 8,
   *  beginning with the lowest valued card.
   *  If it has none, it does not exchange.
   * @return List<Card> List of card objects to exchange for new cards
   */
  public List<Card> decideExchange() {
    List<Hand> allHands = HandCombiner.getAllHands(this.getTotalHand());
    Hand bestHand = HandEvaluator.getBestHands(allHands).get(0).sortHand();
    int handStrength = HandEvaluator.handStrength(bestHand)[0];
    List<Card> exchangeCards = new ArrayList<>();
    if (handStrength <= HAND_RANK_THRESHOLD) { // if hand strength is less than pair
      List<Card> handCopy = bestHand.getCards();
      Collections.reverse(handCopy);
      int numberExchanged = 0;
      for (Card card : handCopy) {
        if ((card.getRank() > 0) && (card.getRank() < EXCHANGE_THRESHOLD) && !(numberExchanged
            == MAX_EXCHANGE)) { // if card rank is lower than threshold to exchange it
          exchangeCards.add(card); // why does exchange take in a string instead of card??
          numberExchanged++;
        }
      }
    }
    return exchangeCards;
  }

  private int computerBetAmount(int desiredBetAmount) {
    int betAmount = desiredBetAmount;
    if (betAmount > this.getBankroll().getValue()) {
      betAmount = this.getBankroll().getValue();
    }
    return betAmount;
  }
}
