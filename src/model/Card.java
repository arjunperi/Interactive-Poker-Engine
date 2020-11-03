package model;

import java.util.Map;
import java.util.Properties;

public class Card {
    private int cardValue;

    private Suit suit;
    private String cardSymbol;
    private int rank;
    private boolean visible;
    private Map<Integer,String> cardValueMap;


    private static final String FILENAME = "CardValueMapping";


    public Card(int value){
        cardValue = value;
    }


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

    public Suit getCardSuit(){
        return suit;
    }

    public boolean getCardVisibility(){
        return visible;
    }

    public void makeVisible(Card card){
        visible=true;
    }
}
