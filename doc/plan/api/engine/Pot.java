package engine;

import java.util.List;

public class Pot {
    /**
     * Holds the current money being bet on the game
     */


    /**
     * gets current value of all the money in the pot
     * @return
     */
    public double getCurrentValue(){
        return 0.0;
    }

    /**
     * adds amount of money passed in to pot total
     * @param moneyToAdd
     * @return
     */
    public void addToPot(double moneyToAdd){

    }


    /**
     * in case of ties, splits the pot among those who tied
     * @param playerList
     */
    //TODO: make player list class or encapsulate list somehow
    public void splitTie(List<Player> playerList ){

    }

    /**
     * when a player runs out of money to bet in a round, creates side pot for them
     */
    public void splitSidePot(){
    }

}
