package utility;

import model.Card;
import model.Hand;

/**
 * This class calculates the number of times that a card with a specified
 * rank occur in a given hand
 */
public final class RankCounter {

  /**
   * calculates the number of times that a card with a specified
   * rank occur in a given hand
   * @param hand - The hand in which we are counting how often the rank occurs
   * @param rank - the rank that we are counting
   * @return how many cards in the hand have the given rank
   */
  public static int getRankCount(Hand hand, int rank) {
    int rankCount = 0;
    for (Card card : hand.getCards()) {
      if (card.getRank() == rank) {
        rankCount++;
      }
    }
    return rankCount;
  }
}
