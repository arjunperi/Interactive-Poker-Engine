package model;

import controller.JSONReader;

import java.util.*;

public class RoundManager {
    private Player winner;
    private int currentRound;
    private Pot pot;
    private HandEvaluator handEvaluator;
    private boolean roundOver;
    private String winDialog;
    private List<Hand> winningHand;
    private HandCombiner handCombiner;
    private JSONReader reader;
    private Map<Integer,String> handStrengths;



    public RoundManager(Pot pot){
        reader = new JSONReader();
        reader.parse("/cardSettings.json");
        currentRound = 0;
        this.pot = pot;
        handStrengths= reader.getStrengths();
        handEvaluator = new HandEvaluator(handStrengths);
        handCombiner = new HandCombiner();
        roundOver = false;
        winningHand = new ArrayList<>();
    }

    public void checkOnePlayerRemains(PlayerList playerList){
        playerList.removeFoldedPlayers();
        List<Player> activePlayers = playerList.getActivePlayers();
        if (activePlayers.size() == 1){
            winner = activePlayers.get(0);
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
            Hand bestHand = handEvaluator.getBestHands(handCombiner.getAllHands(player.getTotalHand())).get(0);
            String handStrength = handStrengths.get(handEvaluator.handStrength(bestHand)[0]);
            System.out.println("\n" + player.toString() + " won the showdown with a " + handStrength + " and received $" + winningAmount);
            winDialog = player.toString() + " won the showdown with a " + handStrength + " and received $" + winningAmount;
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
