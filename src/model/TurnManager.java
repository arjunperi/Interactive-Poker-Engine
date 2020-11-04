package model;

import java.util.*;

//this might need to be abstracted to allow for different betting orders -> might only be needed for stud games
// where the value of your exposed cards determines who bets first

public class TurnManager {
    protected Player winner;
    protected int currentRound;
    protected boolean roundOver;
    protected Pot pot;
    protected List<Player> activePlayers;


    public TurnManager(Pot pot){
        currentRound = 0;
        this.pot = pot;
    }

    public void checkOnePlayerRemains(List<Player> activePlayers){
        if (activePlayers.size() == 1 ){
            winner = activePlayers.get(0);
            endGame();
        }
    }

    public void checkShowDown(List<Player> activePlayers, int currentRound, int totalRounds){
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
        System.out.println("His winning hand was: ");
        for (Card card: winner.getHand().getCards()){
            System.out.println(card.getRank());
        }
        System.exit(0);
    }

    public int getCurrentRound(){
        return currentRound;
    }

    public void endGame(){
        pot.dispersePot(winner);
        System.out.println("Winner is: " + winner.toString() + " and he has $" + winner.getBankroll());
        System.out.println("His winning hand was: ");
        for (Card card: winner.getHand().getCards()){
            System.out.println(card.getRank());
        }
        System.exit(0);
    }
}
