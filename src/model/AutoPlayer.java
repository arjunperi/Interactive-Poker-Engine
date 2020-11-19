package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Computer-controlled poker player
 */
public class AutoPlayer extends Player {

  private static final int DEFAULTBETAMOUNT = 10;
  private static final int PLAY_THRESHOLD = 10;
  private static final int HAND_RANK_THRESHOLD = 0;
  private static final int EXCHANGE_THRESHOLD = 7;
  private static final int MAX_EXCHANGE = 3;
  private static final int BET_INCREMENT = 5;
  private final HandEvaluator handEvaluator;
  private final HandCombiner handCombiner;
  private String action;


  public AutoPlayer(String name, int startingAmount, CommunityCards communityCards, Pot pot) {
    super(name, startingAmount, communityCards, pot);
    isInteractive = false;
    handEvaluator = new HandEvaluator();
    handCombiner = new HandCombiner();
  }

  public void decideAction(int lastBet) {
    boolean isHighEnough = false;
    Hand bestHand = handEvaluator.getBestHands(handCombiner.getAllHands(this.getTotalHand()))
        .get(0);
    int handStrength = handEvaluator.handStrength(bestHand)[0];
    for (int rank : handEvaluator.handStrength(bestHand)) {
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

  public String getAction() {
    return action;
  }

  public List<Card> decideExchange() {
    List<Hand> allHands = handCombiner.getAllHands(this.getTotalHand());
    Hand bestHand = handEvaluator.getBestHands(allHands).get(0).sortHand();
    int handStrength = handEvaluator.handStrength(bestHand)[0];
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


  private int computerBetAmount(int defaultBetAmount) {
    int betAmount = defaultBetAmount;
    if (betAmount > this.getBankroll().getValue()) {
      betAmount = this.getBankroll().getValue();
    }
    return betAmount;
  }
}
