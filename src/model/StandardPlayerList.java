package model;

import java.util.List;

public class StandardPlayerList extends PlayerList {


  public StandardPlayerList(List<Player> players) {
    super(players);
  }

  /**
   * initializes the list of active players by removing those who have folded and shifting the position of the player who has bandwidth
   */
  public void initializeActivePlayers() {
    removeFoldedPlayers();
    raiseShift();
  }

  public void updateActivePlayers() {
    resetActivePlayers();
    initializeActivePlayers();
  }


}
