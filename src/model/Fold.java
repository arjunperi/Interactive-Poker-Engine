package model;

public class Fold extends Action {
    private Player player;

    public Fold(Player player){
        this.player = player;
        performAction();
    }

    @Override
    void performAction() {
        player.exitHand();
        System.out.println(player.toString() + " folds");
    }

}
