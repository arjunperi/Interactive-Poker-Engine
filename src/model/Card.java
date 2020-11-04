package model;

import java.util.Map;
import java.util.Properties;

public class Card {

    private Suit suit;
    private String cardSymbol;
    private int rank;
    private boolean visible;
    private Map<Integer,String> cardValueMap;
    private int cardValue;


    private static final String FILENAME = "CardValueMapping";

    public Card(int rank , Suit suit){
        this.suit = suit;
        this.rank = rank;
        visible=false;
        initializeCardNumber();
    }


    public Properties getPropertyFile(String fileName) {
        Properties propertyFile = new Properties();
        try {
            propertyFile
                    .load(Card.class.getClassLoader().getResourceAsStream(fileName + ".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyFile;
    }


    public void initializeCardNumber(){
        Properties mappings = getPropertyFile(FILENAME);
        cardSymbol = mappings.getProperty(String.valueOf(rank));
    }

    public String getCardSymbol(){
        return cardSymbol;
    }


    public int getCardValue(){
        return cardValue;
    }

    /*public Card (Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }*/

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

    public Suit getCardSuit(){
        return suit;
    }

    public int getCardRank(){return rank;}

    public boolean getCardVisibility(){
        return visible;
    }

    public void makeVisible(){
        visible=true;
    }
}
