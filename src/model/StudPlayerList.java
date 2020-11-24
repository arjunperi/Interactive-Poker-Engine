package model;

import java.util.Collections;
import java.util.List;
import utility.HandEvaluator;
/**
 * This class initializes player in a stud game of poker
 * The order is determined based on the strength of the visible cards
 */
public class StudPlayerList extends PlayerList {


  public StudPlayerList(List<Player> players) {
    super(players);
  }

  /**
   * This class initializes the player list in the correct order based on which
   * player has the best visible hand
   */
  public void initializeActivePlayers() {
    removeFoldedPlayers();
    studOrder();
  }
  /**
   * This method updates the active players
   */
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
