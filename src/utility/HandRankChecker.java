package utility;

import model.Card;
import model.Hand;

public final class HandRankChecker {
  private static final int POKERHANDSIZE = 5;
  private static final int DUMMYCARDRANK = -1;
  private static final int HIGHESTCARDINDEX = 0;
  private static final int SECONDHIGHESTCARDINDEX = 1;




  public static boolean isFiveCardHand(Hand hand) {
    for (Card card : hand.getCards()) {
      if (card.getRank() < 1 || hand.getHandSize() != POKERHANDSIZE) {
        return false;
      }
    }
    return true;
  }

  public static boolean isStraightFlush(Hand hand) {
    return isStraight(hand) && isFlush(hand);
  }

  public static boolean isFourOfAKind(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 4) {
      return false;
    }
    return RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 4
        || RankCounter.getRankCount(hand, hand.get(SECONDHIGHESTCARDINDEX).getRank()) == 4;
  }

  public static boolean isFullHouse(Hand hand) {
    if ((RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 3
        && RankCounter.getRankCount(hand, hand.get(3).getRank()) == 2) || (
        RankCounter.getRankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 2
            && RankCounter.getRankCount(hand, hand.get(2).getRank()) == 3)) {
      return isFiveCardHand(hand);
    }
    return false;
  }

  public static boolean isFlush(Hand hand) {

    String flushSuit = hand.get(0).getCardSuit();
    for (Card card : hand.getCards()) {
      if (!card.getCardSuit().equals(flushSuit)) {
        return false;
      }
    }
    return isFiveCardHand(hand);
  }


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

  public static boolean isThreeOfAKind(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 3) {
      return false;
    }
    return (RankCounter.getRankCount(hand, hand.get(0).getRank()) == 3) || (RankCounter.getRankCount(hand, hand.get(1).getRank())
        == 3) || (RankCounter.getRankCount(hand, hand.get(2).getRank()) == 3);
  }

  public static boolean isTwoPair(Hand hand) {
    if (RankCounter.getRankCount(hand, DUMMYCARDRANK) == 2) {
      return false;
    }
    return RankCounter.getRankCount(hand, hand.get(1).getRank()) == 2
        && RankCounter.getRankCount(hand, hand.get(3).getRank()) == 2;
  }

  public static boolean isPair(Hand hand) {
    for (Card card : hand.getCards()) {
      if ((RankCounter.getRankCount(hand, card.getRank())) == 2 && card.getRank() != DUMMYCARDRANK) {
        return true;
      }
    }
    return false;
  }

  public static boolean isHighCard(Hand hand) {
    return true;
  }

}
