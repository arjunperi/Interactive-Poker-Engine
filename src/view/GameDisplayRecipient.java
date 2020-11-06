package view;

import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public abstract class GameDisplayRecipient{
    protected int xLocation;
    protected int yLocation;
    private Map<FrontEndCard, Integer> frontEndCards;
    private FrontEndCard lastCard;

    public GameDisplayRecipient(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        frontEndCards = new HashMap<>();
    }

    protected int getX(){
        return xLocation;
    }

    protected int getY(){
        return yLocation;
    }

    public void updateFrontEndCards(FrontEndCard card, int location){
        frontEndCards.put(card,location);
        lastCard = card;
    }

    public Map<FrontEndCard, Integer> getFrontEndCards(){
        return frontEndCards;
    }

    public FrontEndCard getLastCard(){
        return lastCard;
    }


}
