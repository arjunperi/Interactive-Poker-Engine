package model;

public class InteractivePlayer extends Player{

    public InteractivePlayer(String name, int startingAmount, Pot pot) {

        super(name, startingAmount, pot);
        isInteractive = true;
    }



}
