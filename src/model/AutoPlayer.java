package model;
/*
Computer-controlled poker player
 */
public class AutoPlayer extends Player {



    public AutoPlayer(String name, int startingAmount, Pot pot) {
        super(name, startingAmount, pot);
        isInteractive = false;
    }

    public void decideAction(){
        // check player hand

        // run through hand evaluator
        // check that the hand is pair or better

        // if player has a pair or better, call





        // if not, fold
        bet(10);
    }
}
