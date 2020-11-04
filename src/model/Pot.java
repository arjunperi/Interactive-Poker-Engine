package model;

public class Pot {
    private int potTotal;

    public Pot(){
        potTotal = 0;
    }

    public void addToPot(int amount) {
        potTotal += amount;
        System.out.println("Pot total is: $" + potTotal);
    }

    public void dispersePot(Player player){

        player.updateBankroll(potTotal);
        potTotal = 0;
    }


    public int getPotTotal(){
        return potTotal;
    }


}
