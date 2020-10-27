package engine;

/**
 * This class will represent a player within the poker game.
 * This class will be abstracted to allow for an interactive player as well as an AI player.
 */
public abstract class Player {
    /**
     * Returns a player's current hand
     * @return
     */
    public Hand getHand(){}

    /**
     * Return's a player's current money count
     */
    public int getMoneyCount{}

    /**
     * Bets a certain amount of money from a player's money count if possible. Adds money to the pot and update turn manager.
     * Shows an error message if a player bets more than they have.
     * @param betAmount
     * @return
     */
    abstract void Bet(int betAmount){
    }

    /**
     * Bets a certain amount of money from a player's money count if possible. Adds money to the pot and updates the turn manager.
     * Shows an error message if a player bets more than they have.
     * @param RaiseAmount
     * @return
     */
    abstract void Raise(int RaiseAmount){}

    /**
     * Checks on the current hand. Updates turn manager.
     */
    abstract void Check(){}

    /**
     * Folds on the current hand. Updates turn manager.
     */
    abstract void Fold(){}

    /**
     * Returns whether the player is active in a game.
     * @return
     */
    public boolean isActive(){}

    /**
     * Returns whether the player is active in a game.
     * @return
     */
    public boolean isActive(){}


}
