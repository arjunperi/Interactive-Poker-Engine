package utility;

import controller.JSONReader;
import controller.exceptions.ControllerException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Hand;
import controller.exceptions.ModelException;
import model.Player;
import model.PlayerList;

/**
 * This class evaluates the strength of a hand and determines which hand and
 * player is best amongst other hands or players
 */
public final class HandEvaluator {

  /**
   * This class returns an int array of the hand in the correct format with
   * the first digit corresponding to the strength of the given hand
   * @param hand - A five card hand
   * @return an int array: the first digit corresponds to the strength of the hand type
   * and the next five digits correspond to the ranks of the cards in the hand formatted in the correct
   * configuration for each hand type.
   */
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
          for (int index = 0; index < formattedHand.length; index++) {
            handRank[index + 1] = formattedHand[index];
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

  /**
   * This method returns a list of the best hands from a list of hands
   * @param hands - a list of hands that are being compared to each other
   * @return -  a list of the best hands in the parameter hands list. List exceeds
   * length one when hands are of equal strength and ranks. This is done by comparing
   * hand strength int arrays index by index
   */
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
          break;
        }
      }
      if (shouldAdd) {
        bestHands.add(hand);
      }
    }
    return bestHands;
  }

  /**
   * This method returns the player or players with the best hand
   * @param playerList - the list of players of whose hands are being compared
   * @param isVisibleHand - boolean that relates to if we are determining the strength of a players entire hand or
   *                      just their visible hand (used for stud games to determine betting order)
   * @return - A list of players with the best hand. Multiple players in list if they have the same hand.
   */
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

  /**
   * Returns a players best hand
   * @param player - the player for who we are finding their best hand
   * @return - the best hand that player has from all the possible five card hands
   * they could have
   */
  public static Hand getBestPlayerHand (Player player){
    return getBestHands(HandCombiner.getAllHands(player.getTotalHand())).get(0);

  }

  /**
   * This method gets the strength of a hand type from the string of the hand type
   * @param value - The string of the name of the hand type
   * @return - the strength of the hand type that corresponds with the string name (from JSON)
   */
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
