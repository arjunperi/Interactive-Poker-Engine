package model;

import java.util.List;

public class Player implements CardRecipient, CardDiscarder {
    private String playerName;
    private int moneyCount;
    private boolean hasFolded;
    private Hand playerHand;
    private WagerAction playerAction;
    private Pot pot;
    private Exchange cardExchange;


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

    @Override
    public void receiveCard(Card card){
        playerHand.add(card);
    }

    //once we configure the cards and the deck, we need to make it so that a discarded card ends up back in the deck which is then shuffled
    @Override
    public void discard(Card card) {
        playerHand.getCards().remove(card);
    }

    public Hand getHand(){
        return playerHand;
    }

    public void updateBankroll(int amount){
        moneyCount += amount;
        System.out.println(this.toString()  + " has $"  + moneyCount);
    }

    public List<Card> chooseExchangeCards(int exchangeLimit){
        cardExchange = new Exchange(this, exchangeLimit);
        return cardExchange.getExchangedCards();
    }


    @Override
    public String toString () {
        return playerName;
    }

}
