package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends CardRecipient{
    private String playerName;
    private int moneyCount;
    private boolean hasFolded;
    private Hand playerHand;
    private Pot pot;

    public Player(String name, int startingAmount, Pot pot){
        super();
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

    public void discardCard(Card card) {
        playerHand.getCards().remove(card);
        addDiscardedCard(card);
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


    @Override
    void receiveCard(Card card) {
        playerHand.add(card);
        addNewCards(card);
    }
}
