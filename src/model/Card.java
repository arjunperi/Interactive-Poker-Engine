package model;

public class Card {

    public enum Rank {
        DEUCE,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    public enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

    private final Rank rank;
    private final Suit suit;
    //private int cardValue;

    /*public Card(int value){
        cardValue = value;
    }

    public int getCardValue(){
        return cardValue;
    }*/

    public Card (Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return (suit + " " + rank);
    }

    @Override
    public boolean equals (Object obj) {

    }
}
