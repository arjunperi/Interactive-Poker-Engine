package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;


    public Deck() {
        deck = new Stack();
        createDeck();
    }

    public Card getTopCard() {
        return deck.pop();
    }

    public void createDeck(){
        List<Card> cardsList = new ArrayList<>();
        for (Suit suit : Suit.values()){
            for (int r = 2; r<15; r++){
                Card card = new Card(r,suit);
                cardsList.add(card);
            }
        }
        for (Card card : cardsList) {
            deck.add(card);
        }
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

    public void shuffle(){
        Collections.shuffle(deck);
    }
}
