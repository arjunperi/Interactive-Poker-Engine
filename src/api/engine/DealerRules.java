package engine;

/**
 * This class is responsible for holding the rules for how a dealer acts based on the given game.
 * This class will be abstracted to allow for different types of dealer rule "models."
 */
public abstract class DealerRules {


    public void setCommunalCardsDealt(int numCommunalCards){}

    /**
     * Handles which players are being dealt to, when, and how many cards
     */
    abstract void playerDealRules();

    /**
     * Handles when to deal communcal cards and how many to deal
     */
    abstract void communalDealRules();
}
