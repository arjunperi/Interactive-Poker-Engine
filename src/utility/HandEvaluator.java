package utility;

import controller.JSONReader;
import model.Card;
import model.Hand;
import model.Player;
import model.PlayerList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class HandEvaluator {

  private static final int POKERHANDSIZE = 5;
  private static final int DUMMYCARDRANK = -1;
  private static final int ACERANK = 14;
  private static final int HIGHESTCARDINDEX = 0;
  private static final int SECONDHIGHESTCARDINDEX = 1;
  private static final int STRENGTHINDEX = 0;


  public static boolean isFiveCardHand(Hand hand) {
    for (Card card : hand.getCards()) {
      if (card.getRank() < 1 || hand.getHandSize() != POKERHANDSIZE) {
        return false;
      }
    }
    return true;
  }

  //hands need to be sorted in descending order for the booleans

  public static boolean isStraightFlush(Hand hand) {
    return isStraight(hand) && isFlush(hand);
  }

  public static boolean isFourOfAKind(Hand hand) {
    if (rankCount(hand, DUMMYCARDRANK) == 4) {
      return false;
    }
    return rankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 4
        || rankCount(hand, hand.get(SECONDHIGHESTCARDINDEX).getRank()) == 4;
  }

  public static boolean isFullHouse(Hand hand) {
    if ((rankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 3
        && rankCount(hand, hand.get(3).getRank()) == 2) || (
        rankCount(hand, hand.get(HIGHESTCARDINDEX).getRank()) == 2
            && rankCount(hand, hand.get(2).getRank()) == 3)) {
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
    if (rankCount(hand, DUMMYCARDRANK) == 3) {
      return false;
    }
    return (rankCount(hand, hand.get(0).getRank()) == 3) || (rankCount(hand, hand.get(1).getRank())
        == 3) || (rankCount(hand, hand.get(2).getRank()) == 3);
  }

  public static boolean isTwoPair(Hand hand) {
    if (rankCount(hand, DUMMYCARDRANK) == 2) {
      return false;
    }
    return rankCount(hand, hand.get(1).getRank()) == 2
        && rankCount(hand, hand.get(3).getRank()) == 2;
  }

  public static boolean isPair(Hand hand) {
    for (Card card : hand.getCards()) {
      if ((rankCount(hand, card.getRank())) == 2 && card.getRank() != DUMMYCARDRANK) {
        return true;
      }
    }
    return false;
  }

  public static boolean isHighCard(Hand hand) {
    return true;
  }

  //put in a hand and a rank and returns how many times that rank was in the hand
  private static int rankCount(Hand hand, int rank) {
    int rankCount = 0;
    for (Card card : hand.getCards()) {
      if (card.getRank() == rank) {
        rankCount++;
      }
    }
    return rankCount;
  }

//Hands need to be sorted in descending order before put into formatting methods

  private static int[] formatStraightFlush(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
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

  private static int[] formatFourOfAKind(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    int index = 0;
    for (Card card : hand.getCards()) {
      if (rankCount(hand, card.getRank()) == 4) {
        orderedHand[index] = card.getRank();
        index++;
      } else if (rankCount(hand, card.getRank()) == 1) {
        orderedHand[4] = card.getRank();
      }
    }
    return orderedHand;
  }


  private static int[] formatFullHouse(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    int index1 = 0;
    int index2 = 3;
    for (Card card : hand.getCards()) {
      if (rankCount(hand, card.getRank()) == 3) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (rankCount(hand, card.getRank()) == 2) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  private static int[] formatFlush(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    for (int index = 0; index < orderedHand.length; index++) {
      orderedHand[index] = hand.get(index).getRank();
    }
    return orderedHand;
  }

  private static int[] formatStraight(Hand hand) {
    return formatStraightFlush(hand);
  }


  private static int[] formatThreeOfAKind(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    int index1 = 0;
    int index2 = 3;
    for (Card card : hand.getCards()) {
      if (rankCount(hand, card.getRank()) == 3) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (rankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  private static int[] formatTwoPair(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    int index1 = 0;
    int index2 = 4;
    for (Card card : hand.getCards()) {
      if (rankCount(hand, card.getRank()) == 2) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (rankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
      }
    }
    return orderedHand;
  }

  private static int[] formatPair(Hand hand) {
    int[] orderedHand = makeNewArray(POKERHANDSIZE);
    int index1 = 0;
    int index2 = 2;
    for (Card card : hand.getCards()) {
      if (rankCount(hand, card.getRank()) == 2) {
        orderedHand[index1] = card.getRank();
        index1++;
      } else if (rankCount(hand, card.getRank()) == 1) {
        orderedHand[index2] = card.getRank();
        index2++;
      }
    }
    return orderedHand;
  }

  private static int[] formatHighCard(Hand hand) {
    return formatFlush(hand);
  }

  // handStrength returns int array of length 6: first int is the strength of the hand and the next five ints are
//the ranks of the hand formatted in the correct order based on the hand strength
// the comparison method that picks the best hand needs the hand to be in this format for comparing
// hands to one another
  public static int[] handStrength(Hand hand) {
    int[] handRank = makeNewArray(6);
    if (isStraightFlush(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Straight Flush");
      int[] formattedHand = formatStraightFlush(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isFourOfAKind(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Four Of A Kind");
      int[] formattedHand = formatFourOfAKind(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isFullHouse(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Full House");
      int[] formattedHand = formatFullHouse(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isFlush(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Flush");
      int[] formattedHand = formatFlush(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isStraight(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Straight");
      int[] formattedHand = formatStraight(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isThreeOfAKind(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Three Of A Kind");
      int[] formattedHand = formatThreeOfAKind(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isTwoPair(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Two Pair");
      int[] formattedHand = formatTwoPair(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isPair(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("Pair");
      int[] formattedHand = formatPair(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    if (isHighCard(hand)) {
      handRank[STRENGTHINDEX] = getHandStrengthRank("High Card");
      int[] formattedHand = formatHighCard(hand);
      for (int index = 0; index < formattedHand.length; index++) {
        handRank[index + 1] = formattedHand[index];
      }
      return handRank;
    }
    return handRank;
  }

  public static List<Hand> getBestHands(List<Hand> hands) {
    int[] bestHand = new int[6];
    boolean isSame;
    int index;
    ArrayList<Hand> bestHands = new ArrayList<>();
    for (Hand hand : hands) {
      index = 0;
      isSame = true;
      while (isSame && index < 6) {
        int[] handStrength = handStrength(hand);
        if (handStrength[index] > bestHand[index]) {
          bestHand = handStrength;
          isSame = false;
        } else if (handStrength[index] == bestHand[index]) {
          index++;
        } else if (handStrength[index] < bestHand[index]) {
          isSame = false;
        }
      }
    }
    for (Hand hand : hands) {
      boolean shouldAdd = true;
      int[] handStrength = handStrength(hand);
      for (int j = 0; j < handStrength.length; j++) {
        if (handStrength[j] != bestHand[j]) {
          shouldAdd = false;
        }
      }
      if (shouldAdd) {
        bestHands.add(hand);
      }
    }
    return bestHands;
  }


  //maybe sort here instead of when updating the total hands
  public static List<Player> getBestPlayers(PlayerList playerList, boolean isVisibleHand) {
    Map<Hand, Player> bestHandMapping = new HashMap<>();
    {
      List<Player> bestPlayers = new ArrayList<>();
      for (Player player : playerList.getActivePlayers()) {
        Hand playerHand;
        if (isVisibleHand) {
          playerHand = getBestHands(HandCombiner.getAllHands(player.getTotalBackendVisibleHand()))
              .get(0);
        } else {
          playerHand = getBestHands(HandCombiner.getAllHands(player.getTotalHand())).get(0);
        }
        bestHandMapping.put(playerHand, player);
      }
      List<Hand> bestHands = getBestHands(new ArrayList<>(bestHandMapping.keySet()));
      for (Hand bestHand : bestHands) {
        bestPlayers.add(bestHandMapping.get(bestHand));
      }
      return bestPlayers;
    }
  }

  public static Hand getBestPlayerHand(Player player) {
    HandCombiner combiner = new HandCombiner();
    return getBestHands(combiner.getAllHands(player.getTotalHand())).get(0);

  }

  private static int[] makeNewArray(int size) {
    return new int[size];
  }

//    public <K, V> Stream<Object> getHandStrengthRank( V value) {
//        return handStrength
//                .entrySet()
//                .stream()
//                .filter(entry -> value.equals(entry.getValue()))
//                .map(Map.Entry::getKey);
//    }

  public static int getHandStrengthRank(String value) {
    JSONReader reader = new JSONReader();
    reader.parse("/cardSettings.json");
    Map<Integer, String> handStrength = reader.getStrengths();
    List<Integer> keys = handStrength.keySet().stream()
        .filter(s -> handStrength.get(s).equals(value))
        .collect(Collectors.toList());
    return keys.get(0);
  }

}
