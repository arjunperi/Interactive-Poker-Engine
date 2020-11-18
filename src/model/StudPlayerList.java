package model;

import utility.HandEvaluator;

import java.util.Collections;
import java.util.List;

public class StudPlayerList extends PlayerList {


  public StudPlayerList(List<Player> players) {
    super(players);
  }


  public void initializeActivePlayers() {
    //why did we have this line -> so that betting order is reset after all the raise shifts
    removeFoldedPlayers();
    studOrder();
  }

  public void updateActivePlayers() {
    resetActivePlayers();
    initializeActivePlayers();
  }

  private void studOrder() {
    Player bestPlayer = HandEvaluator.getBestPlayers(this, true).get(0);
    int shiftAmount = activePlayers.size() - activePlayers.indexOf(bestPlayer);
    Collections.rotate(activePlayers, shiftAmount);
    raiseShift();
  }

}
