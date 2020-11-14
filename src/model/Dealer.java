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
        try{
            Card exchangeCard = new Card(Integer.parseInt(cardString.split(" ")[0]), cardString.split(" ")[1]);
            return exchangeCard;
        }
        catch (NumberFormatException e){
            throw new ModelException("Invalid Card Input");
        }
    }


    public void checkDeck(){
        if (deck.isEmpty()){
            System.exit(0);
        }
    }

    public void burnCards(int numberToBurn){}

}
