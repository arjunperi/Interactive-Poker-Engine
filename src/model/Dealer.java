package model;

import java.util.List;

public class Dealer {
    public Deck dealerDeck;
    public CommunityCards communityCards;

    public Dealer(){
        Card card1 = new Card(2);
        Card card2 = new Card(3);
        Card card3 = new Card(4);
        Card card4 = new Card(5);
        Card card5 = new Card(6);
        Card card6 = new Card(7);
        Card card7 = new Card(8);
        Card card8 = new Card(9);
        Card card9 = new Card(10);
        Card card10 = new Card(11);
        Card card11 = new Card(12);
        Card card12 = new Card(13);
        Card card13 = new Card(14);

        dealerDeck = new Deck(List.of(card1,card2,card3, card4, card5, card6, card7, card8, card9, card10, card11,
        card12,card13));
        communityCards = new CommunityCards();
    }

    public void dealCards(Player player, int numberToDeal){
        for (int i=0; i< numberToDeal; i++){
            player.addToHand(dealerDeck.getTopCard());
        }
    }

    public void dealCommunityCards(int numberToDeal){
        for (int i=0; i< numberToDeal; i++){
            communityCards.addToCommunityCards(dealerDeck.getTopCard());
        }
    }

    public void burnCards(int numberToBurn){}

    public void exchangeCards(Player player, int numberToExchange){}

}
