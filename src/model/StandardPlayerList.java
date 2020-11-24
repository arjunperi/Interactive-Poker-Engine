package model;

import java.util.List;

/**
 * This class initializes player in a standard game of poker
 * The order is shifted after each round one position
 */
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

  /**
   * This method updates the active players
   */
  public void updateActivePlayers() {
    resetActivePlayers();
    initializeActivePlayers();
  }


}
