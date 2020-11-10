package model;

import java.util.*;

public class TurnManager {
    private Player winner;
    private int currentRound;
    private Pot pot;
    private HandEvaluator handEvaluator;


    public TurnManager(Pot pot){
        currentRound = 0;
        this.pot = pot;
        handEvaluator = new HandEvaluator();
    }

    public void checkOnePlayerRemains(PlayerList playerList){
        playerList.updateActivePlayers();
        List<Player> activePlayers = playerList.getActivePlayers();
        if (activePlayers.size() == 1){
            System.out.print("hit");
            winner = activePlayers.get(0);
            pot.dispersePot(winner,pot.getPotTotal());
            pot.clearPot();
            System.exit(0);
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
    public void showDown(PlayerList activePlayers){
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
        System.exit(0);
    }

    public int getCurrentRound(){
        return currentRound;
    }
}
