package utility;

import controller.JSONReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.ControllerException;
import model.Hand;
import model.ModelException;
import model.Player;
import model.PlayerList;

public final class HandEvaluator {




  //put in a hand and a rank and returns how many times that rank was in the hand


//Hands need to be sorted in descending order before put into formatting methods

  // handStrength returns int array of length 6: first int is the strength of the hand and the next five ints are
//the ranks of the hand formatted in the correct order based on the hand strength
// the comparison method that picks the best hand needs the hand to be in this format for comparing
// hands to one another
  public static int[] handStrength(Hand hand) {
    int[] handRank = new int[6];
    JSONReader reader = new JSONReader();
    reader.parse("/cardSettings.json");
    List<String> handTypes = reader.getHandTypes();
    for (String handType : handTypes) {
      try {
        Class<?> c = Class.forName("utility.HandRankChecker");
        Class<?> f = Class.forName("utility.HandFormatter");
        Method handChecker = c.getDeclaredMethod("is" + handType.replaceAll("\\s", ""), Hand.class);
        Boolean isHand = (Boolean) handChecker.invoke(HandRankChecker.class, hand);
        if(isHand){
          handRank[0] = getHandStrengthRank(handType);
          Method format = f
              .getDeclaredMethod("format" + handType.replaceAll("\\s", ""), Hand.class);
          int[] formattedHand = (int[]) format.invoke(HandFormatter.class, hand);
//          List formattedHandList = Arrays.asList(formattedHandObject);
//          Object[] formattedHandList = (Object[])formattedHandObject;
//          List<Object> formattedHandList = List.class.cast(formattedHandObject);
          for (int index = 0; index < formattedHand.length; index++) {
            handRank[index + 1] = (Integer) formattedHand[index];
          }
          return handRank;
        }
      } catch (
          ModelException e) {
        throw new ControllerException(e.getCause().getMessage());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      return handRank;
  }

//      if (isStraightFlush(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Straight Flush");
//        int[] formattedHand = formatStraightFlush(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isFourOfAKind(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Four Of A Kind");
//        int[] formattedHand = formatFourOfAKind(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isFullHouse(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Full House");
//        int[] formattedHand = formatFullHouse(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isFlush(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Flush");
//        int[] formattedHand = formatFlush(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isStraight(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Straight");
//        int[] formattedHand = formatStraight(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isThreeOfAKind(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Three Of A Kind");
//        int[] formattedHand = formatThreeOfAKind(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isTwoPair(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Two Pair");
//        int[] formattedHand = formatTwoPair(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isPair(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("Pair");
//        int[] formattedHand = formatPair(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      if (isHighCard(hand)) {
//        handRank[STRENGTHINDEX] = getHandStrengthRank("High Card");
//        int[] formattedHand = formatHighCard(hand);
//        for (int index = 0; index < formattedHand.length; index++) {
//          handRank[index + 1] = formattedHand[index];
//        }
//        return handRank;
//      }
//      return handRank;


    public static List<Hand> getBestHands (List < Hand > hands) {
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
    public static List<Player> getBestPlayers (PlayerList playerList,boolean isVisibleHand){
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

    public static Hand getBestPlayerHand (Player player){
      return getBestHands(HandCombiner.getAllHands(player.getTotalHand())).get(0);

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
