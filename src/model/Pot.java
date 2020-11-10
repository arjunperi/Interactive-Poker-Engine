package model;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class Pot {
    private int potTotal;

    public Pot(){
        potTotal = 0;
    }

    public void addToPot(int amount) {
        potTotal += amount;
        System.out.println("Pot total is: $" + potTotal);
    }

    //error checking of some sort needed here
    public void dispersePot(Player winner, int potAmount){
        winner.updateBankroll(potAmount);
    }

    public void clearPot(){
        potTotal = 0;
    }

    public int getPotTotal(){
        return potTotal;
    }
}
