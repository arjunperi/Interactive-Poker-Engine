package model;
import java.util.List;
import controller.JSONReader;

import java.util.*;

import utility.HandCombiner;
import utility.HandEvaluator;

/**
 * This class is responsible for managing round completion (i.e. all players folded
 * or showdown)
 */
public class RoundManager {

  private final Pot pot;
  private boolean roundOver;
  private String winDialog;
  private final Map<Integer, String> handStrengths;
  private final static String CARD_SETTINGS_PATH = "/cardSettings.json";
  private final static String WON_SHOWDOWN_TEXT = " won the showdown with a ";
  private final static String RECEIVED_CASH_SHOWDOWN_TEXT = " and received $";
  private final static String EVERYONE_FOLDED_TEXT = "Everyone else folded! ";
  private final static String RECEIVED_CASH_FOLDED = " won and received $";

  public RoundManager(Pot pot) {
    JSONReader reader = new JSONReader();
    reader.parse(CARD_SETTINGS_PATH);
    this.pot = pot;
    handStrengths = reader.getStrengths();
    roundOver = false;
  }

  /**
   * This method checks if there is only one active player left and if so
   * that player wins the pot
   * @param playerList - The list of the players in the game
   */
  public void checkOnePlayerRemains(PlayerList playerList) {
    playerList.removeFoldedPlayers();
    List<Player> activePlayers = playerList.getActivePlayers();
    if (activePlayers.size() == 1) {
      Player winner = activePlayers.get(0);
      winDialog =
          EVERYONE_FOLDED_TEXT + winner.toString() + RECEIVED_CASH_FOLDED + pot.getPotTotal()
              .getValue();
      pot.dispersePot(winner, pot.getPotTotal().getValue());
      pot.clearPot();
      roundOver = true;
    }
  }

  private int splitAmount(int numberOfWinners) {
    return pot.getPotTotal().getValue() / numberOfWinners;
  }

  /**
   * This method gives the active player with the best hand its winnings
   * from the pot
   * @param activePlayers - a list of the active players (players that have not folded)
   */
  public void showDown(PlayerList activePlayers) {
    for (Player player : activePlayers.getActivePlayers()) {
      player.updateTotalHand();
    }
    List<Player> bestPlayers = HandEvaluator.getBestPlayers(activePlayers, false);
    int winningAmount = splitAmount(bestPlayers.size());
    for (Player player : bestPlayers) {
      Hand bestHand = HandEvaluator.getBestHands(HandCombiner.getAllHands(player.getTotalHand()))
          .get(0);
      String handStrength = handStrengths.get(HandEvaluator.handStrength(bestHand)[0]);
      winDialog = player.toString() + WON_SHOWDOWN_TEXT + handStrength + RECEIVED_CASH_SHOWDOWN_TEXT
          + winningAmount;
      pot.dispersePot(player, winningAmount);
    }
    pot.clearPot();
    roundOver = true;
  }

  /**
   * This method checks if the round is over
   * @return - boolean if the round is over not
   */
  public boolean isRoundOver() {
    return roundOver;
  }

  /**
   * This method returns the win dialog
   * @return - a string that is to be displayed on the action log that shows who
   * won the round
   */
  public String getWinDialog() {
    return winDialog;
  }
}
