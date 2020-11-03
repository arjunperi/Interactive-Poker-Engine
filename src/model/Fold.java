package model;

public class Fold implements WagerAction {
    private Player player;

    public Fold(Player player){
        this.player = player;
        performBetAction();
    }

    @Override
    public void performBetAction() {
        player.exitHand();
        System.out.println(player.toString() + " folds");
    }

}
