package model;

import java.util.List;

public class Dealer {
    public Deck deck;

    public Dealer(Deck deck){
        this.deck = deck;
    }

    public void dealCards(CardRecipient recipient, List<Boolean> visibilityList){
        recipient.clearNewCards();
        for (boolean isVisible: visibilityList){
            Card cardDealt = deck.getTopCard();
            if (isVisible){
                cardDealt.makeVisible();
            }
            recipient.receiveCard(cardDealt);
        }
    }

    public void exchangeCards(Player player, List<String> exchangeCards){
        player.clearDiscardedCards();
        player.clearNewCards();
        for (String exchangeCard: exchangeCards){
            player.discardCard(stringToCard(exchangeCard));
            System.out.println(player.toString() + " gets: " + deck.peekTopCard().getRank());
            Card cardDealt = deck.getTopCard();
            player.receiveCard(cardDealt);
        }
    }

    private Card stringToCard(String cardString){
        /*int spaceIndex = cardString.indexOf(" ");
        Suit suit = Suit.valueOf(cardString.substring(0,spaceIndex));
        int rank = Integer.parseInt(cardString.substring(spaceIndex + 1, cardString.length()));
        return new Card(rank, suit);*/

        return new Card(Integer.parseInt(cardString.split(" ")[0]), cardString.split(" ")[1]);
    }


    public void checkDeck(){
        if (deck.isEmpty()){
            System.exit(0);
        }
    }

    public void burnCards(int numberToBurn){}

}
