package model;

import java.util.Collections;
import java.util.List;

public class StudPlayerList extends PlayerList {

  private HandEvaluator handEvaluator;

  public StudPlayerList(List<Player> players) {
    super(players);
    handEvaluator = new HandEvaluator();
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
    Player bestPlayer = handEvaluator.getBestPlayers(this, true).get(0);
    int shiftAmount = activePlayers.size() - activePlayers.indexOf(bestPlayer);
    Collections.rotate(activePlayers, shiftAmount);
    raiseShift();
  }

}
