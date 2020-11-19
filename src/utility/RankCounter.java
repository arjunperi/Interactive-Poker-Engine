package utility;

import model.Card;
import model.Hand;

public final class RankCounter {

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
