package engine;

/**
 * The Deck class represents a deck of all the playable cards in a game.
 */

//TODO: Figure out if Deck needs to be abstracted / how the size of the deck will be read in and accounted for. Multiple decks?
public class Deck {
    /**
     * Shuffles the deck to randomize all of the cards.
     */
    public void Shuffle(){}

    /**
     * Resets the deck back to it's initial size (after the end of a hand for example).
     */
    public void Reset(){}

    /**
     * Returns the top Card of from the deck
     * @return
     */
    public Card topCard(){
        //will change
        return null;
    }
}
