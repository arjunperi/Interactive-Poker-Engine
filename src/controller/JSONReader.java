package controller;

import controller.exceptions.SetUpException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.*;


public class JSONReader {

  private Map<String, String> suits;
  private Map<Integer, String> ranks;
  private Map<Integer, String> strengths;
  private JSONObject jo;
  private String cardBack;

  public JSONReader() {
    suits = new HashMap<>();
    ranks = new HashMap<>();
    strengths = new HashMap<>();
  }

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

  public Map<String, String> getSuits() {
    return suits;
  }

  public Map<Integer, String> getRanks() {
    return ranks;
  }

  public Map<Integer, String> getStrengths() {
    return strengths;
  }

  /*public <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch(ClassCastException e) {
      return null;
    }
  }*/

  public List<String> getSuitNames() {
    List<String> suitNames = new ArrayList<>(getSuits().keySet());
    Collections.sort(suitNames);
    return suitNames;
  }

  public List<Integer> getRankValues() {
    List<Integer> rankValues = new ArrayList<>(getRanks().keySet());
    Collections.sort(rankValues);
    return rankValues;
  }

  public List<Integer> getStrengthValues() {
    List<Integer> strengthValues = new ArrayList<>(getStrengths().keySet());
    Collections.sort(strengthValues);
    return strengthValues;
  }


  public String getCardBack() {
    return cardBack;
  }

}
