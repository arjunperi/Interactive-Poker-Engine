package api.engine;

import engine.Player;


public class DummyPlayer extends Player {


    private int myMoneyAmount = 0;


    public void Bet(int betAmount){
        myMoneyAmount -= betAmount;
    }

    public void Fold(){

    }
    public void Check(){

    }



}
