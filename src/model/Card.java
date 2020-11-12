package model;

import java.util.Properties;

public class Card {

    private String suit;
    private String cardSymbol;
    private int rank;
    private boolean isVisible;
    private FileReader reader;


    private static final String FILENAME = "CardValueMapping";

    /*public Card(int rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        visible=false;
        initializeCardNumber();
    }*/

    public Card(int rank, String suit) {
        this.suit = suit;
        this.rank = rank;
        isVisible = false;
        reader = new FileReader();
        initializeCardNumber();
    }

    private void initializeCardNumber(){
        Properties mappings = reader.getPropertyFile(FILENAME);
        cardSymbol = mappings.getProperty(String.valueOf(rank));
    }

    public String getCardSymbol(){
        return cardSymbol;
    }

    @Override
    public String toString() {
        return (suit + " " + rank);
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Card otherCard = (Card) obj;
        return rank == otherCard.rank && suit == otherCard.suit;
    }

    public String getCardSuit(){
        return suit;
    }

    public int getRank(){return rank;}

    public boolean isVisible(){
        return isVisible;
    }


    public void makeVisible(){
        isVisible =true;
    }
}
