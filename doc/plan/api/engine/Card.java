package api.engine;

public class Card
{
    /**
     * Represents a card in the game, has a value, suit, and visibility.
     * Visibility determines whether the card can be seen or not on the screen
     *
     */

    /**
     * returns value of card (in poker, usually two through ace
     * @return String
     */
    public String getValue(){
        return "";
    }

    /**
     * returns the suit of the card
     * @return String
     */
    public String getSuit(){
        return "";
    }

    /**
     * returns boolean indicating if the card is currently visible to the player or not
     */
    public boolean getVisibility(){
        return false;
    }

    /**
     * Changes the visibility of a card
     */
    public void setVisibility(){}

}
