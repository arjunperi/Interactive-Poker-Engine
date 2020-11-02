package model;

import java.util.List;

public class Dealer {
    public Deck dealerDeck;
    public CommunityCards communityCards;

    public Dealer(Deck deck){
        dealerDeck = deck;
    }

    public void dealCards(CardRecipient recipient, int numberToDeal){
        for (int i=0; i< numberToDeal; i++){
            recipient.receiveCard(dealerDeck.getTopCard());
        }
    }

    public void exchangeCards(Player player, List<Card> exchangeCards){
        for (Card card: exchangeCards) {
            player.discard(card);
            System.out.println(player.toString() + " gets: " + dealerDeck.peekTopCard().getCardValue());
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
