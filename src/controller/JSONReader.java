package controller;

import controller.exceptions.SetUpException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class JSONReader {
  private Map<String, Integer> cardTypes;
  private List<String> suits;
  private Map<String, Integer> ranks;
  private JSONObject jo;

  public JSONReader(){
    cardTypes = new HashMap<>();
    suits = new ArrayList<>();
    ranks = new HashMap<>();
  }

  public void parse(String directory) {
    try {
      InputStream is = JSONReader.class.getResourceAsStream(directory);
      JSONTokener tokener = new JSONTokener(is);
      jo = new JSONObject(tokener);
      parseCardSuits();
      parseCardRanks();

    } catch (Exception e) {
      throw new SetUpException();
    }
  }

  private void parseCardSuits() {
    JSONArray jsonArray = jo.getJSONArray("suits");
    for (Iterator<Object> it = jsonArray.iterator(); it.hasNext(); ) {
      String suit = (String) it.next();
      suits.add(suit);
    }
  }

  private void parseCardRanks() {
    JSONObject jObject = jo.getJSONObject("ranks");
    for (Iterator<String> it = jObject.keys(); it.hasNext(); ) {
      String rank = it.next();
      ranks.put(rank, (Integer) jObject.get(rank));
    }
  }

  public List<String> getSuits() {
    return suits;
  }

  public Map<String, Integer> getRanks() {
    return ranks;
  }
}
