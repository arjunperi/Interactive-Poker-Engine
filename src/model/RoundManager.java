package model;
import java.util.List;
import controller.JSONReader;

import java.util.*;

import utility.HandCombiner;
import utility.HandEvaluator;


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

  public boolean isRoundOver() {
    return roundOver;
  }

  public String getWinDialog() {
    return winDialog;
  }
}
