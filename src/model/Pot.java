package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents the pot that holds all player bets
 */
public class Pot {

  private final IntegerProperty potTotal;


  public Pot() {
    potTotal = new SimpleIntegerProperty(0);
  }

  /**
   * adds the amount passed in to the pot
   * @param amount amount to be added to pot
   */
  public void addToPot(int amount) {
    potTotal.setValue(potTotal.getValue() + amount);
  }

  /**
   * This method gives the winning player the money in the pot
   * @param winner - the player that won
   * @param potAmount - the amount of money the player recieves
   */
  public void dispersePot(Player winner, int potAmount) {
    winner.updateBankroll(potAmount);
  }

  /**
   * This method sets the pot total to zero
   */
  public void clearPot() {
    potTotal.setValue(0);
  }

  /**
   * This method gets the amount of money in the pot
   * @return the total pot amount
   */
  public IntegerProperty getPotTotal() {
    return potTotal;
  }
}
