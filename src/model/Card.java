package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class Card {
    private int cardValue;
    private String suit;
    private String cardNumber;
    private int rank;
    private boolean visible;
    private Map<Integer,String> cardValueMap;


    private static final String FILENAME = "CardValueMapping";


    public Card(int value){
        cardValue = value;
    }


    public Card(String value, String suit){
        cardNumber = value;
        this.suit = suit;
        visible=false;
        getRank();
    }


    public Properties getPropertyFile(String fileName) {
        Properties propertyFile = new Properties();
        try {
            propertyFile
                    .load(CommunityDealerRules.class.getClassLoader().getResourceAsStream(fileName + ".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyFile;
    }



    public void getRank (){
        Properties mappings = getPropertyFile(FILENAME);
        rank = Integer.parseInt(mappings.getProperty(cardNumber));
    }



    public int getCardValue(){
        return cardValue;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public String getCardSuit(){
        return suit;
    }

    public boolean getCardVisibility(){
        return visible;
    }

    public void makeVisible(Card card){
        visible=true;
    }
}
