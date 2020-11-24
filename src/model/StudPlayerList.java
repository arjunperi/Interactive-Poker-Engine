package model;

import java.util.Collections;
import java.util.List;
import utility.HandEvaluator;

public class StudPlayerList extends PlayerList {


  public StudPlayerList(List<Player> players) {
    super(players);
  }


  public void initializeActivePlayers() {
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
