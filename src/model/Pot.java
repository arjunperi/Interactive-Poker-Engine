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

  public void dispersePot(Player winner, int potAmount) {
    winner.updateBankroll(potAmount);
  }

  public void clearPot() {
    potTotal.setValue(0);
  }

  public IntegerProperty getPotTotal() {
    return potTotal;
  }
}
