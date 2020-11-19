package model;

import java.util.*;

public class RoundManager {


    private final Pot pot;
    private final HandEvaluator handEvaluator;
    private boolean roundOver;
    private String winDialog;
    private List<Hand> winningHand;
    private HandCombiner handCombiner;


    public RoundManager(Pot pot){

        this.pot = pot;
        handEvaluator = new HandEvaluator();
        handCombiner = new HandCombiner();
        roundOver = false;
        winningHand = new ArrayList<>();
    }

    public void checkOnePlayerRemains(PlayerList playerList){
        playerList.removeFoldedPlayers();
        List<Player> activePlayers = playerList.getActivePlayers();
        if (activePlayers.size() == 1){
            Player winner = activePlayers.get(0);
            System.out.println("\n" + winner.toString() + " won and received $" + pot.getPotTotal());
            winDialog = winner.toString() + " won and received $" + pot.getPotTotal();
            pot.dispersePot(winner,pot.getPotTotal().getValue());
            pot.clearPot();
            roundOver = true;
        }
    }

    private int splitAmount(int numberOfWinners){
        return pot.getPotTotal().getValue()/numberOfWinners;
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
            System.out.println("\n" + player.toString() + " won the showdown and received $" + winningAmount);
            winDialog = player.toString() + " won the showdown and received $" + winningAmount;
//            winningHand.add(handEvaluator.getBestHands(handCombiner.getAllHands(player.getTotalHand())).get(0));
            pot.dispersePot(player, winningAmount);
        }
        pot.clearPot();
        roundOver = true;
    }

    public boolean isRoundOver(){
        return roundOver;
    }

    public String getWinDialog(){
        return winDialog;
    }

    //we want to be able to say ROYAL FLUSH
//    public String getWinningHand(){
//        String hand
//        for (Hand hand : winningHand){
//
//        }
//    }
}
