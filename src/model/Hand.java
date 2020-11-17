package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> hand;

    //for temporary hand evaluation logic -> won't exist later
    private int handSize;

    public Hand() {
        hand = new ArrayList<>();
        handSize = 0;
    }

    public void add(Card card) {
        hand.add(card);
        handSize++;
    }
    public void clear(){
        hand.clear();
        handSize = 0;
    }

    public void remove(Card card) {
//        String cardString = card.toString();
//        List<Card> handCopy = new ArrayList<>(hand);
//        for (Card cardInHand: handCopy) {
//
//            if (cardString.equals(cardInHand.toString())){
//
//                hand.remove(cardInHand);
//                handSize = handSize - 1;
//            }
//
//        }
        hand.remove(card);
        handSize-=1;
    }

    public List<Card> getCards() {
        return hand;
    }

    public int getHandSize() {
        return handSize;
    }

    public Card get(int index){
        if(index<this.getHandSize()){
            int i =0;
            for (Card card:this.getCards()){
                if(i==index){
                    return card;
                }
                i++;
            }
        }
        return null;
    }

    public Hand sortHand() {
        Hand sortedHand = new Hand();
        while (this.getHandSize() > 0) {
            int max = -2;
            Card maxCard = null;
            for (Card card : this.getCards()) {
                if (card.getRank() > max) {
                    max = card.getRank();
                }
            }
            for (Card card : this.getCards()) {
                if (card.getRank() == max) {
                    maxCard = card;
                }
            }
            sortedHand.add(maxCard);
            this.remove(maxCard);
        }
        return sortedHand;
    }

    public Hand copyHand() {
        Hand copyHand = new Hand();
        for(Card card: this.getCards()){
            copyHand.add(card);
        }
        copyHand = copyHand.sortHand();
        return copyHand;
    }



}


