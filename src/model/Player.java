package model;

import controller.Controller;

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
    private Hand totalVisibleHand;
    private Pot pot;
    protected boolean isInteractive;
    private int betAmount;
    //have a player's hand strength
    //update it after every deal

    public Player(String name, int startingAmount, CommunityCards communityCards, Pot pot){
        super();
        this.pot = pot;
        playerName = name;
        moneyCount = startingAmount;
        playerHand = new Hand();
        this.communityCards = communityCards;
        totalHand = new Hand();
        totalVisibleHand = new Hand();
        discardedCardList = new ArrayList<>();
    }


    public int getBankroll(){
        return moneyCount;
    }

    public boolean isSolvent(){
        return moneyCount > 0;
    }

    public boolean isActive(){
        if (hasFolded){
            return false;
        }
        else {
            return true;
        }
    }

    public void enterNewGame(CommunityCards communityCards, Pot pot){
        if (isSolvent()){
             hasFolded = false;
        }
        this.communityCards = communityCards;
        this.pot = pot;
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


    public void setHand(Hand hand){
        playerHand = hand;
    }

    public void updateBankroll(int amount){
        moneyCount += amount;
        System.out.println(this.toString()  + " has $"  + moneyCount);
    }

    //use sets of cards instead of lists?
    public void updateTotalHand(){
        totalHand.clear();
        for (Card playerCard: playerHand.getCards()){
            totalHand.add(playerCard);
        }
        for (Card communityCard : communityCards.getCommunityCardsList()){
            totalHand.add(communityCard);
        }
        addDummyCards(totalHand);
        totalHand = totalHand.sortHand();
    }

    public Hand getTotalVisibleHand(){
        totalVisibleHand.clear();
        for (Card card: totalHand.getCards()){
            if (card.isVisible()){
                totalVisibleHand.add(card);
            }
        }
        addDummyCards(totalVisibleHand);
        totalVisibleHand = totalVisibleHand.sortHand();
        return totalVisibleHand;
    }

    private void addDummyCards(Hand hand){
        int handSize = hand.getHandSize();
        if (handSize < 5){
            int fiveCardHandDifference = 5 - handSize;
            for (int i=0; i<fiveCardHandDifference; i++){
                Card dummyCard = new Card(-1, "CLUBS");
                hand.add(dummyCard);
            }
        }
    }

    public Hand getTotalHand(){
        return totalHand;
    }

    public void bet(int amountToBet){
        System.out.print(this.toString() + "bets " + amountToBet);
        betAmount = amountToBet;
        pot.addToPot(amountToBet);
        updateBankroll(amountToBet * -1);
    }

    public int getBetAmount(){
        return betAmount;
    }


    public void fold(){
        System.out.println(this.toString() + " has folded");
        hasFolded = true;
    }

    public boolean isInteractive(){
        return isInteractive;
    }


    public String toString () {
        return playerName;
    }

    public void receiveCard(Card card) {
        playerHand.add(card);
        addNewCards(card);
        updateTotalHand();
    }

    public void clearHand(){
        playerHand.clear();
    }
}
