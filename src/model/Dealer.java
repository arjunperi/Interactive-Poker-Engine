package model;

import java.util.List;

public class Dealer {
    public Deck dealerDeck;

    public Dealer(Deck deck){
        dealerDeck = deck;
    }

    public void dealCards(CardRecipient recipient, int numberToDeal){
        for (int i=0; i< numberToDeal; i++){
            Card cardDealt = dealerDeck.getTopCard();
            recipient.receiveCard(cardDealt);
        }
    }

    public void exchangeCards(Player player, List<Card> exchangeCards){
        for (Card card: exchangeCards) {
            player.discard(card);
            System.out.println(player.toString() + " gets: " + dealerDeck.peekTopCard().getRank());
            dealCards(player,1);
        }
    }

    public void checkDeck(){
        if (dealerDeck.isEmpty()){
            System.exit(0);
        }
    }

    public void burnCards(int numberToBurn){}

    public void exchangeCards(Player player, int numberToExchange){}

}
