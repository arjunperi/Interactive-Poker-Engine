package model;

import java.util.List;

public class StandardPlayerList extends PlayerList {

  public StandardPlayerList(List<Player> players) {
    super(players);
  }

  public void initializeActivePlayers() {
    removeFoldedPlayers();
    raiseShift();
  }

  public void updateActivePlayers() {
    resetActivePlayers();
    initializeActivePlayers();
  }


}
