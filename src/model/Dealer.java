package model;

import java.util.List;

public class Dealer {
    public Deck dealerDeck;

    public Dealer(Deck deck){
        dealerDeck = deck;
    }

    public void dealCards(CardRecipient recipient, int numberToDeal){
        recipient.clearNewCards();
        for (int i=0; i< numberToDeal; i++){
            Card cardDealt = dealerDeck.getTopCard();
            recipient.receiveCard(cardDealt);
        }
    }

    public void exchangeCards(Player player, Card exchangeCard){
        player.discardCard(exchangeCard);
        System.out.println(player.toString() + " gets: " + dealerDeck.peekTopCard().getRank());
        Card cardDealt = dealerDeck.getTopCard();
        player.receiveCard(cardDealt);
    }

    public void checkDeck(){
        if (dealerDeck.isEmpty()){
            System.exit(0);
        }
    }

    public void burnCards(int numberToBurn){}


}
