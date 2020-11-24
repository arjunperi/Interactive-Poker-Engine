package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of the players in the game.
 */
public abstract class PlayerList {

  protected List<Player> activePlayers;
  protected List<Player> allPlayers;
  private List<Player> removedPlayers;
  private Player playerUp;
  private int lastBet;
  private boolean raiseMade;
  private Player raiseSeat;

  public PlayerList(List<Player> players) {
    this.allPlayers = new ArrayList<>(players);
    this.activePlayers = players;
    removedPlayers = new ArrayList<>();
    lastBet = 0;
    raiseMade = false;
  }

  protected void removeFoldedPlayers() {
    List<Player> filtered = activePlayers.stream()
        .filter(b -> !b.isActive())
        .collect(Collectors.toList());
    removedPlayers.addAll(filtered);
    activePlayers.removeAll(filtered);
  }

  /**
   *  Returns the list of all player's in the game
   * @return List of Player objects
   */
  public List<Player> getAllPlayers() {
    return allPlayers;
  }

  /**
   * If only one player who has not gone bankrupt remains, returns true
   * @return Boolean
   */
  public boolean doesOneSolventPlayerRemain() {
    int SolventPlayerCount = 0;
    for (Player player : this.getActivePlayers()) {
      if (player.isSolvent()) {
        SolventPlayerCount++;
      }
    }
    return SolventPlayerCount == 1;
  }

  /**
   * Rotates the player order so that the first person to act changes each round
   */
  public void updateStartingRoundOrder() {
    Collections.rotate(allPlayers, -1);
  }

  /**
   * updates the active player list
   */
  public abstract void updateActivePlayers();

  /**
   * initializes the active playerlist
   */
  public abstract void initializeActivePlayers();

  /**
   * resets the active player list so that folded players are re-added.
   */
  public void resetActivePlayers() {
    activePlayers = new ArrayList<>(allPlayers);
    for (Player player : getActivePlayers()) {
      player.clearBetAmount();
    }
  }

  /**
   * if the player has raised the current bet amount, returns true
   * @param player Player to determine if has raised
   * @return Boolean
   */
  public boolean raiseMade(Player player) {
    playerUp = player;
    if (playerUp.getTotalBetAmount() > lastBet) {
      raiseMade = true;
      lastBet = playerUp.getTotalBetAmount();
      raiseSeat = player;
    }
    return raiseMade;
  }

  public void raiseShift() {
    if (raiseMade) {
      int shiftIndex = activePlayers.indexOf(playerUp) + 1;
      Collections.rotate(activePlayers, -shiftIndex);
    }
    raiseMade = false;
  }

  /**
   * returns the last bet made
   * @return integer
   */
  public int getLastBet() {
    return lastBet;
  }


  public Player getRaiseSeat() {
    return raiseSeat;
  }

  public void resetRaiseStats() {
    lastBet = 0;
    raiseSeat = null;
  }

  /**
   * returns a list of the player's who are currently active in the game
   * @return List of Player objects
   */
  public List<Player> getActivePlayers() {
    return activePlayers;
  }
}
