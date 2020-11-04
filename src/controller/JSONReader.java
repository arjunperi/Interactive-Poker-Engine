package controller;

import controller.exceptions.SetUpException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONReader {
  private Map<String, Integer> cardTypes;
  private List<String> suits;
  private List<String> ranks;
  private JSONObject jo;

  public JSONReader(){
    cardTypes = new HashMap<>();
    suits = new ArrayList<>();
    ranks = new ArrayList<>();
  }

  public void parse(String directory) {
    try {
      Object obj = new JSONParser().parse(new FileReader(directory));
      jo = (JSONObject) obj;
      parseCardSuits();
      parseCardRanks();
    } catch (ParseException | IOException e) {
      throw new SetUpException();
    }
  }

  private void parseCardSuits() {
    JSONArray jsonArray = (JSONArray) jo.get("suits");
    for (String suit : (Iterable<String>) jsonArray) {
      suits.add(suit);
    }
  }

  private void parseCardRanks() {
    JSONArray jsonArray = (JSONArray) jo.get("ranks");
    for (String rank : (Iterable<String>) jsonArray) {
      ranks.add(rank);
    }
  }

  public List<String> getSuits() {
    return suits;
  }

  public List<String> getRanks() {
    return ranks;
  }
}
