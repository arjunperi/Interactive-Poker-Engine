package model;

import java.util.ArrayList;
import java.util.List;

public class Exchange {
    private Player player;
    private int exchangeAmount;
    private List<Card> cardsToExchange;

    public Exchange(Player player, int exchangeLimit){
        exchangeAmount = (int) ((Math.random() * (exchangeLimit + 1 - 0)) + 1);
        this.player = player;
    }

    public List<Card> getExchangedCards() {
        if (exchangeAmount > 0){
            Hand playerHand = player.getHand();

            System.out.println(player.toString() + "'s current hand is: ");
            for (Card card: player.getHand().getCards()){
                System.out.println(card.getCardValue());
            }
            cardsToExchange = new ArrayList<>();
            System.out.println(player.toString() + " will exchange " +  exchangeAmount + " cards");
            System.out.println(player.toString() + " will exchange: ");
            for (int i=0; i<exchangeAmount; i++){
//                int exchangeCardIndex = (int) ((Math.random() * (playerHand.getCards().size()  - 1 )) + 1);
//                Card exchangeCard = playerHand.getCards().get(exchangeCardIndex);

                //just exchange in order for now
                Card exchangeCard = playerHand.getCards().get(i);
                System.out.println(exchangeCard.getCardValue());
                cardsToExchange.add(exchangeCard);
            }
        }
        else {
            System.out.println(player.toString() + " does not exchange any cards");
        }
        return cardsToExchange;
    }
}
