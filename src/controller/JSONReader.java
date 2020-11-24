package controller;

import controller.exceptions.SetUpException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.*;

/**
 * This class is responsible for reading the cardSettings.json file
 */
public class JSONReader {

  private final Map<String, String> suits;
  private final Map<Integer, String> ranks;
  private final Map<Integer, String> strengths;
  private JSONObject jo;
  private String cardBack;

  public JSONReader() {
    suits = new HashMap<>();
    ranks = new HashMap<>();
    strengths = new HashMap<>();
  }

  /**
   * This method parses the file and initializes all of the maps
   * @param directory - the string of the name of the file
   */
  public void parse(String directory) {
    try {
      InputStream is = JSONReader.class.getResourceAsStream(directory);
      JSONTokener tokener = new JSONTokener(is);
      jo = new JSONObject(tokener);
      parseCardSuits();
      parseCardRanks();
      parseHandStrengths();
      parseGameSettings();

    } catch (Exception e) {
      throw new SetUpException();
    }
  }

  private void parseGameSettings() {
    JSONObject gameSettings = jo.getJSONObject("gameSettings");
    parseCardBack(gameSettings);
  }


  private void parseCardBack(JSONObject gameSettings) {
    cardBack = (String) gameSettings.get("cardBack");
  }

  //TODO Refactor all parse methods into one that uses Generics
  private void parseCardSuits() {
    JSONObject cardSuits = jo.getJSONObject("suits");
    for (Iterator<String> it = cardSuits.keys(); it.hasNext(); ) {
      String suit = it.next();
      suits.put(suit, (String) cardSuits.get(suit));
    }
  }

  private void parseCardRanks() {
    JSONObject cardRanks = jo.getJSONObject("ranks");
    for (Iterator<String> it = cardRanks.keys(); it.hasNext(); ) {
      String rank = it.next();
      ranks.put(Integer.parseInt(rank), (String) cardRanks.get(rank));
    }
  }

  private void parseHandStrengths() {
    JSONObject handStrengths = jo.getJSONObject("hand strengths");
    for (Iterator<String> it = handStrengths.keys(); it.hasNext(); ) {
      String strength = it.next();
      strengths.put(Integer.parseInt(strength), (String) handStrengths.get(strength));
    }
  }

  /**
   * This method gets the map of the suit name to the name of the
   * image that corresponds with that suit name
   * @return the map of string suit name to string of picture name
   */
  public Map<String, String> getSuits() {
    return suits;
  }

  /**
   * This method gets the map of the integer of the rank of a suit to the
   * corresponding string that will be displayed on the card
   * @return - the map of the integer rank to the string of what is displayed
   * on the card
   */
  public Map<Integer, String> getRanks() {
    return ranks;
  }

  /**
   * This method returns the map of the integer of the rank of the hand type
   * to the string of the hand type
   * @return - the map of the hand rank integer to the hand rank string
   */
  public Map<Integer, String> getStrengths() {
    return strengths;
  }

  /**
   * This method returns a list of the suit names
   * @return a list of the suit names
   */
  public List<String> getSuitNames() {
    List<String> suitNames = new ArrayList<>(getSuits().keySet());
    Collections.sort(suitNames);
    return suitNames;
  }
  /**
   * This method returns a list of the rank values
   * @return a list of the rank values
   */
  public List<Integer> getRankValues() {
    List<Integer> rankValues = new ArrayList<>(getRanks().keySet());
    Collections.sort(rankValues);
    return rankValues;
  }
  /**
   * This method returns a list of the hand strength values
   * @return a list of the hand strength value in descending order
   */
  public List<Integer> getStrengthValues() {
    List<Integer> strengthValues = new ArrayList<>(getStrengths().keySet());
    strengthValues.sort(Collections.reverseOrder());
    return strengthValues;
  }
  /**
   * This method returns a list of hand types
   * @return a list of the hand in descending order based on the strength
   * values
   */
  public List<String> getHandTypes() {
    List<Integer> strengthValues = getStrengthValues();
    List<String> handTypes = new ArrayList<>();
    for(int strength : strengthValues){
      handTypes.add(strengths.get(strength));
    }
    return handTypes;
  }

  /**
   * This method returns a string of the name of the image file for the card back
   * @return the string associated with the name of the card back image
   */
  public String getCardBack() {
    return cardBack;
  }

}
