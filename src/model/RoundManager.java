package model;

import java.util.*;

public class RoundManager {
    private Player winner;
    private int currentRound;
    private Pot pot;
    private HandEvaluator handEvaluator;
    private boolean roundOver;


    public RoundManager(Pot pot){
        currentRound = 0;
        this.pot = pot;
        handEvaluator = new HandEvaluator();
        roundOver = false;
    }

    public void checkOnePlayerRemains(PlayerList playerList){
        playerList.removeFoldedPlayers();
        List<Player> activePlayers = playerList.getActivePlayers();
        if (activePlayers.size() == 1){
            winner = activePlayers.get(0);
            pot.dispersePot(winner,pot.getPotTotal());
            pot.clearPot();
            roundOver = true;
        }
    }

    public void checkShowDown(PlayerList playerList, int currentRound, int totalRounds){
        if (currentRound == totalRounds){
            showDown(playerList);
        }
    }

    private int splitAmount(int numberOfWinners){
        return pot.getPotTotal()/numberOfWinners;
    }

    //should we be updating the players' total hands in a better way/ different place?
    //AI updating?
    private void showDown(PlayerList activePlayers){
        for (Player player: activePlayers.getActivePlayers()){
            player.updateTotalHand();
        }
        List<Player> bestPlayers =  handEvaluator.getBestPlayers(activePlayers, false);
        int winningAmount = splitAmount(bestPlayers.size());
        for (Player player : bestPlayers){
            System.out.println("\n" + player.toString() + " won and received $" + winningAmount);
            pot.dispersePot(player, winningAmount);
        }
        pot.clearPot();
        roundOver = true;
    }

    public boolean roundOver(){
        return roundOver;
    }
}
