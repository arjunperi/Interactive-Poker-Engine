package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends CardRecipient{
    private String playerName;
    private int moneyCount;
    private boolean hasFolded;
    private Hand playerHand;
    private List<Card> discardedCardList;
    private CommunityCards communityCards;
    private Hand totalHand;
    //have a player's hand strength
    //update it after every deal

    public Player(String name, int startingAmount, CommunityCards communityCards){
        super();
        playerName = name;
        moneyCount = startingAmount;
        playerHand = new Hand();
        this.communityCards = communityCards;
        totalHand = new Hand();
        discardedCardList = new ArrayList<>();
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
        discardedCardList.add(card);
    }

    public List<Card> getDiscardedCards(){
        return discardedCardList;
    }

    public void clearDiscardedCards(){
        discardedCardList.clear();
    }

    public Hand getHand(){
        return playerHand;
    }


    public void updateBankroll(int amount){
        moneyCount += amount;
        System.out.println(this.toString()  + " has $"  + moneyCount);
    }

    //use sets of cards instead of lists?
    public void updateTotalHand(){
        totalHand.getCards().clear();
        for (Card playerCard: playerHand.getCards()){
            totalHand.add(playerCard);
        }
        for (Card communityCard : communityCards.getCommunityCardsList()){
            totalHand.add(communityCard);
        }
    }

    //send in total hand to the hand evaluator, get the strength
    public int getTotalHandStrength(){
        return 0;
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
