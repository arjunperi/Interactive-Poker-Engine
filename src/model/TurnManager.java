package model;

import java.util.*;

//this will need to be abstracted to allow for different betting orders
public class TurnManager {
    private Player winner;
    private int currentRound;
    private boolean roundOver;
    private Pot pot;
    private List<Player> activePlayers;


    public TurnManager(Pot pot){
        currentRound = 0;
        this.pot = pot;
    }

    public void startBettingRound(PlayerList pokerPlayerList, int totalRounds){
        List<Player> allPlayers = pokerPlayerList.getAllPlayers();
        for (Player currentPlayer: allPlayers){
            System.out.println("\n" + currentPlayer.toString() + " is up");
            if (currentPlayer.isActive()){
                currentPlayer.performAction();
            }
            activePlayers = pokerPlayerList.updateActivePlayers();
            if (activePlayers.size() == 1 ){
                winner = activePlayers.get(0);
                endGame();
            }
        }
        currentRound ++;
        if (currentRound == totalRounds){
            showDown(activePlayers);
        }
    }

    public void showDown(List<Player> activePlayers){
        Map<Player, Integer> scoreMap = new HashMap<>();
        int maxScore = 0;
        for (Player player: activePlayers){
            scoreMap.put(player, player.getHand().getHandTotal());
        }
        for (Player player: scoreMap.keySet()){
            System.out.println(player.toString() + " " + scoreMap.get(player));
            if (scoreMap.get(player) > maxScore){
                maxScore = scoreMap.get(player);
                winner = player;
            }
        }
        System.out.println("Winner is: " + winner.toString() + " with a score of " +  maxScore);
        }


    public void endGame(){
        pot.dispersePot(winner);
        System.out.println("Winner is: " + winner.toString() + " and he has $" + winner.getBankroll());
        System.exit(0);
    }
}
