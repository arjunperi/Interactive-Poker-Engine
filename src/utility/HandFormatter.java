package utility;

import model.Card;
import model.Hand;

public final class HandFormatter {

  private static final int POKERHANDSIZE = 5;
  private static final int DUMMYCARDRANK = -1;
  private static final int ACERANK = 14;
  private static final int HIGHESTCARDINDEX = 0;
  private static final int SECONDHIGHESTCARDINDEX = 1;
  private static final int STRENGTHINDEX = 0;


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

  public static int[] formatFlush(Hand hand) {
    int[] orderedHand = new int[POKERHANDSIZE];
    for (int index = 0; index < orderedHand.length; index++) {
      orderedHand[index] = hand.get(index).getRank();
    }
    return orderedHand;
  }

  public static int[] formatStraight(Hand hand) {
    return formatStraightFlush(hand);
  }


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

  public static int[] formatHighCard(Hand hand) {
    return formatFlush(hand);
  }

}
