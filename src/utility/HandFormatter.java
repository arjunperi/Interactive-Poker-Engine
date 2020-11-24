package utility;

import model.Card;
import model.Hand;

/**
 * This class formats the hands correctly based on the hand type
 * for computations in hand evaluator
 */
public final class HandFormatter {

  private static final int POKERHANDSIZE = 5;
  private static final int ACERANK = 14;


  /**
   * This method formats a hand into the correct format for a Straight flush
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatStraightFlush(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    if (hand.get(0).getRank() == ACERANK && hand.get(1).getRank() == 5) {
      String aceSuit = hand.get(0).getCardSuit();
      hand.remove(hand.get(0));
      hand.add(new Card(1, aceSuit));
    }
    for (int index = 0; index < orderedHand.length; index++) {
      orderedHand[index] = hand.get(index).getRank();
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a four of a kind
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatFourOfAKind(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    int index = 0;
    for (Card card : hand.getCards()) {
      if (RankCounter.getRankCount(hand, card.getRank()) == 4) {
        orderedHand[index] = card.getRank();
        index++;
      } else if (RankCounter.getRankCount(hand, card.getRank()) == 1) {
        orderedHand[4] = card.getRank();
      }
    }
    return orderedHand;
  }


  /**
   * This method formats a hand into the correct format for a full house
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatFullHouse(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    int index1 = 0;
    int index2 = 3;
    for (Card card : hand.getCards()) {
      if (RankCounter.getRankCount(hand, card.getRank()) == 3) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (RankCounter.getRankCount(hand, card.getRank()) == 2) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a  flush
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatFlush(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    for (int index = 0; index < orderedHand.length; index++) {
      orderedHand[index] = hand.get(index).getRank();
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a Straight
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatStraight(Hand hand) {
    return formatStraightFlush(hand);
  }


  /**
   * This method formats a hand into the correct format for a three of a kind hand
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatThreeOfAKind(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    int index1 = 0;
    int index2 = 3;
    for (Card card : hand.getCards()) {
      if (RankCounter.getRankCount(hand, card.getRank()) == 3) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (RankCounter.getRankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a Two Pair hand
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatTwoPair(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    int index1 = 0;
    int index2 = 4;
    for (Card card : hand.getCards()) {
      if (RankCounter.getRankCount(hand, card.getRank()) == 2) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (RankCounter.getRankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
      }
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a Pair hand
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatPair(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    int index1 = 0;
    int index2 = 2;
    for (Card card : hand.getCards()) {
      if (RankCounter.getRankCount(hand, card.getRank()) == 2) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (RankCounter.getRankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  /**
   * This method formats a hand into the correct format for a High Card hand
   * @param hand - Hand that needs to be formatted correctly
   * @return int array of hand in correct format for comparison computations
   */
  public static int[] formatHighCard(Hand hand) {
    return formatFlush(hand);
  }

}
