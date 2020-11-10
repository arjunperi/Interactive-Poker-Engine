package model;

import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;
    private List<String> suits;
    private List<Integer> ranks;

    /*public Deck() {
        deck = new Stack();
        createDeck();
    }*/

    public Deck(List<String> suits, List<Integer> ranks) {
        this.suits = suits;
        this.ranks = ranks;
        deck = new Stack<>();
        createDeck2();
    }

    public Card getTopCard() {
        return deck.pop();
    }

    /*public void createDeck(){
        for (Suit suit : Suit.values()){
            for (int r = 2; r<15; r++){
                Card card = new Card(r,suit);
                deck.add(card);
            }
        }
    }*/

    public void createDeck2() {
        for (String suit: suits) {
            for (int rank: ranks) {
                Card card = new Card(rank, suit);
                deck.add(card);
            }
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

}
