package controller;

import controller.exceptions.SetUpException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONTokener;


public class JSONReader {
  private final Map<String, String> suits;
  private final Map<Integer, String> ranks;
  private JSONObject jo;
  private String cardBack;

  public JSONReader(){
    suits = new HashMap<>();
    ranks = new HashMap<>();
  }

  public void parse(String directory) {
    try {
      InputStream is = JSONReader.class.getResourceAsStream(directory);
      JSONTokener tokener = new JSONTokener(is);
      jo = new JSONObject(tokener);
      parseCardSuits();
      parseCardRanks();
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

  public Map<String, String> getSuits() {
    return suits;
  }

  public Map<Integer, String> getRanks() {
    return ranks;
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


  public String getCardBack() {
    return cardBack;
  }

}
