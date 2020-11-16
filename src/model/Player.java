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
    private Hand totalVisibleHand;
    private Pot pot;
    protected boolean isInteractive;
    private int totalBetAmount;
    private int currentBetAmount;
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

    public Hand getTotalBackendVisibleHand(){
        totalVisibleHand.clear();
        for (Card card: totalHand.getCards()){
            if (card.isBackEndVisible()){
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
        if (amountToBet <= moneyCount){
            System.out.print(this.toString() + " bets " + amountToBet + "\n");
            currentBetAmount = amountToBet;
            totalBetAmount = totalBetAmount + currentBetAmount;
            pot.addToPot(currentBetAmount);
            updateBankroll(currentBetAmount * -1);
        }
        else {
            throw new ModelException("Cannot bet more money than you have!");
        }
    }

    public int getTotalBetAmount(){
        return totalBetAmount;
    }

    public int getCurrentBetAmount(){
        return currentBetAmount;
    }

    public void clearBetAmount(){
        totalBetAmount = 0;
        currentBetAmount = 0;
    }

    public void fold(){
        System.out.println(this.toString() + " has folded");
        hasFolded = true;
    }

    public void call(int lastBet){
        System.out.println(this.toString() + " has called");
        int callAmount = lastBet-totalBetAmount;
        if(callAmount>=moneyCount){
            bet(moneyCount);
        }
        else{
            bet(callAmount);
        }
    }

    public boolean isInteractive(){
        return isInteractive;
    }


    public String toString () {
        return playerName;
    }

    public void receiveCard(Card card) {
        if (isInteractive){
            card.setInteractivePlayerCard();
        }
        playerHand.add(card);
        addNewCards(card);
        updateTotalHand();
    }

    public void clearHand(){
        playerHand.clear();
    }
}
