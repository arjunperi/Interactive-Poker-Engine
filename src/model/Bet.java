package model;

public class Bet extends Action {
    private int betAmount;
    private Pot pot;
    private Player player;

    public Bet(Player player, Pot pot){
        this.pot = pot;
        betAmount = (int) ((Math.random() * (11 - 1)) + 1);
        System.out.println(player.toString() + " bets: $" +  betAmount);
        this.player = player;
        performAction();
    }

    @Override
    void performAction() {
        pot.addToPot(betAmount);
        player.updateBankroll(betAmount * -1);
    }
}
