package model;
import java.util.List;
import controller.JSONReader;

import java.util.*;

import utility.HandCombiner;
import utility.HandEvaluator;


public class RoundManager {

  private Player winner;
  private int currentRound;
  private Pot pot;
  private boolean roundOver;
  private String winDialog;
  private final JSONReader reader;
  private final Map<Integer, String> handStrengths;


  public RoundManager(Pot pot) {
    reader = new JSONReader();
    reader.parse("/cardSettings.json");
    currentRound = 0;
    this.pot = pot;
    handStrengths = reader.getStrengths();
    roundOver = false;
  }

  public void checkOnePlayerRemains(PlayerList playerList) {
    playerList.removeFoldedPlayers();
    List<Player> activePlayers = playerList.getActivePlayers();
    if (activePlayers.size() == 1) {
      winner = activePlayers.get(0);
      System.out
          .println("\n" + winner.toString() + " and received $" + pot.getPotTotal().getValue());
      winDialog =
          "Everyone else folded! " + winner.toString() + " won and received $" + pot.getPotTotal()
              .getValue();
      pot.dispersePot(winner, pot.getPotTotal().getValue());
      pot.clearPot();
      roundOver = true;
    }
  }

  private int splitAmount(int numberOfWinners) {
    return pot.getPotTotal().getValue() / numberOfWinners;
  }

  //should we be updating the players' total hands in a better way/ different place?
  //AI updating?
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
      System.out.println(
          "\n" + player.toString() + " won the showdown with a " + handStrength + " and received $"
              + winningAmount);
      winDialog = player.toString() + " won the showdown with a " + handStrength + " and received $"
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
