package model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    public Deck(List<Card> cardsList) {
        deck = new Stack();
        for (Card card : cardsList) {
            deck.add(card);
        }
    }

    public Card getTopCard() {
        return deck.pop();
    }

    public void replaceTopCard(Card card){
        deck.push(card);
    }

    //temporary, just for print methods
    public Card peekTopCard() {
        return (Card) deck.peek();
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

}
