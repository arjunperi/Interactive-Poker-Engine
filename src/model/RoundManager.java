package model;

import java.util.List;

public class RoundManager {

  private Player winner;
  private final int currentRound;
  private final Pot pot;
  private final HandEvaluator handEvaluator;
  private boolean roundOver;


  public RoundManager(Pot pot) {
    currentRound = 0;
    this.pot = pot;
    handEvaluator = new HandEvaluator();
    roundOver = false;
  }

  public void checkOnePlayerRemains(PlayerList playerList) {
    playerList.removeFoldedPlayers();
    List<Player> activePlayers = playerList.getActivePlayers();
    if (activePlayers.size() == 1) {
      winner = activePlayers.get(0);
      System.out.println("\n" + winner.toString() + " won and received $" + pot.getPotTotal());
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
    List<Player> bestPlayers = handEvaluator.getBestPlayers(activePlayers, false);
    int winningAmount = splitAmount(bestPlayers.size());
    for (Player player : bestPlayers) {
      System.out
          .println("\n" + player.toString() + " won the showdown and received $" + winningAmount);
      pot.dispersePot(player, winningAmount);
    }
    pot.clearPot();
    roundOver = true;
  }

  public boolean isRoundOver() {
    return roundOver;
  }
}
