package utility;

import model.Card;
import model.Hand;

/**
 * This class is responsible for determining the class type of a five card hand
 */
public final class HandRankChecker {
  private static final int POKERHANDSIZE = 5;
  private static final int DUMMYCARDRANK = -1;
  private static final int HIGHESTCARDINDEX = 0;
  private static final int SECONDHIGHESTCARDINDEX = 1;


  /**
   * Returns if hand is five valid cards
   * @param hand - Hand of cards
   * @return wheter or not hand consist of five valid cards
   */
  public static boolean isFiveCardHand(Hand hand) {
    for (Card card : hand.getCards()) {
      if (card.getRank() < 1 || hand.getHandSize() != POKERHANDSIZE) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns boolean if hand is straight flush
   * @param hand - Hand of cards that is being checked
   * @return true if hand is straight flush
   */
  public static boolean isStraightFlush(Hand hand) {
    return isStraight(hand) && isFlush(hand);
  }
  /**
   * Returns boolean if hand is a four of a kind
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a four of a kind
   */
  public static boolean isFourOfAKind(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 4) {
      return false;
    }
    return RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 4
        || RankCounter.getRankCount(hand, hand.get(SECONDHIGHESTCARDINDEX).getRank()) == 4;
  }
  /**
   * Returns boolean if hand is a full house
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a full house
   */
  public static boolean isFullHouse(Hand hand) {
    if ((RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 3
        && RankCounter.getRankCount(hand, hand.get(3).getRank()) == 2) || (
        RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 2
            && RankCounter.getRankCount(hand, hand.get(2).getRank()) == 3)) {
      return isFiveCardHand(hand);
    }
    return false;
  }
  /**
   * Returns boolean if hand is flush
   * @param hand - Hand of cards that is being checked
   * @return true if hand is flush
   */
  public static boolean isFlush(Hand hand) {

    String flushSuit = hand.get(0).getCardSuit();
    for (Card card : hand.getCards()) {
      if (!card.getCardSuit().equals(flushSuit)) {
        return false;
      }
    }
    return isFiveCardHand(hand);
  }

  /**
   * Returns boolean if hand is straight
   * @param hand - Hand of cards that is being checked
   * @return true if hand is straight
   */
  public static boolean isStraight(Hand hand) {
    for (int i = 1; i < hand.getHandSize(); i++) {
      if (hand.get(i).getRank() != (hand.get(i - 1).getRank() - 1)) {

        if (hand.get(0).getRank() == 14) {
          String aceSuit = hand.get(0).getCardSuit();
          Hand tempHand = hand.copyHand();
          tempHand.remove(hand.get(0));
          tempHand.add(new Card(1, aceSuit));
          return isStraight(tempHand);
        }
        return false;
      }
    }
    return isFiveCardHand(hand);
  }
  /**
   * Returns boolean if hand is a three of a kind
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a three of a kind
   */
  public static boolean isThreeOfAKind(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 3) {
      return false;
    }
    return (RankCounter.getRankCount(hand, hand.get(0).getRank()) == 3) || (RankCounter.getRankCount(hand, hand.get(1).getRank())
        == 3) || (RankCounter.getRankCount(hand, hand.get(2).getRank()) == 3);
  }
  /**
   * Returns boolean if hand is a two pair
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a two pair
   */
  public static boolean isTwoPair(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 2) {
      return false;
    }
    return RankCounter.getRankCount(hand, hand.get(1).getRank()) == 2
        && RankCounter.getRankCount(hand, hand.get(3).getRank()) == 2;
  }
  /**
   * Returns boolean if hand is a pair hand
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a pair hand
   */
  public static boolean isPair(Hand hand) {
    for (Card card : hand.getCards()) {
      if ((RankCounter.getRankCount(hand, card.getRank())) == 2 && card.getRank() != DUMMYCARDRANK) {
        return true;
      }
    }
    return false;
  }
  /**
   * Returns boolean if hand is a high card hand
   * @param hand - Hand of cards that is being checked
   * @return true if hand is a high card hand
   */
  public static boolean isHighCard(Hand hand) {
    return true;
  }

}
