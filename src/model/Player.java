package model;

import java.util.Map;
import java.util.Random;

public class Player {
    private String playerName;
    private int moneyCount;
    private boolean hasFolded;
    private Hand playerHand;
    private Action playerAction;
    private Pot pot;


    public Player(String name, int startingAmount, Pot pot){
        playerName = name;
        moneyCount = startingAmount;
        playerHand = new Hand();
        this.pot = pot;
    }

    public int getBankroll(){
        return moneyCount;
    }

    public boolean isSolvent(){
        return moneyCount > 0;
    }

    public void exitHand(){
        hasFolded = true;
    }

    public boolean isActive(){
        if (hasFolded){
            return false;
        }
        else {
            return true;
        }
    }

    public void performAction(){
        int randomAction = (int) ((Math.random() * (5 - 1)) + 1);
        if (randomAction == 1){
            playerAction = new Fold(this);
        }
        else{
            playerAction =  new Bet(this, pot);
        }
    }

    public void addToHand(Card card){
        playerHand.add(card);
    }

    public Hand getHand(){
        return playerHand;
    }

    public void updateBankroll(int amount){
        moneyCount += amount;
        System.out.println(this.toString()  + " has $"  + moneyCount);
    }

    @Override
    public String toString () {
        return playerName;
    }
}
