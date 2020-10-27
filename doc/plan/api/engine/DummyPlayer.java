package api.engine;

import engine.Player;


public class DummyPlayer extends Player {


    private int myMoneyAmount = 0;


    public void bet(int betAmount){
        myMoneyAmount -= betAmount;
    }

    public void fold(){

    }
    public void check(){

    }



}
