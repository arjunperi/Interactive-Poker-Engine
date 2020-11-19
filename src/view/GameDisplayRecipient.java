package view;

import java.util.HashMap;
import java.util.Map;

public abstract class GameDisplayRecipient {

  private final Map<FrontEndCard, Integer> frontEndCardLocations;
  protected int xLocation;
  protected int yLocation;
  private FrontEndCard lastCard;

  public GameDisplayRecipient(int xLocation, int yLocation) {
    this.xLocation = xLocation;
    this.yLocation = yLocation;
    frontEndCardLocations = new HashMap<>();
  }

  protected int getX() {
    return xLocation;
  }

  protected int getY() {
    return yLocation;
  }

  public void updateFrontEndCards(FrontEndCard card, int location) {
    frontEndCardLocations.put(card, location);
    lastCard = card;
  }

  public void clearFrontEndCardLocations() {
    frontEndCardLocations.clear();
  }

  public Map<FrontEndCard, Integer> getFrontEndCardLocations() {
    return frontEndCardLocations;
  }

  public FrontEndCard getLastCard() {
    return lastCard;
  }


}
