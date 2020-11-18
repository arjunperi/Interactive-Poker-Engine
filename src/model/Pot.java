package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pot {

  private final IntegerProperty potTotal;

  public Pot() {
    potTotal = new SimpleIntegerProperty(0);
  }

  public void addToPot(int amount) {
    potTotal.setValue(potTotal.getValue() + amount);
    System.out.println("Pot total is: $" + potTotal.getValue());
  }

  //error checking of some sort needed here
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
