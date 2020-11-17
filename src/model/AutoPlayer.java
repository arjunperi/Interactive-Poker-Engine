package model;
/*
Computer-controlled poker player
 */
public class AutoPlayer extends Player {
    private HandEvaluator handEvaluator;
    private HandCombiner handCombiner;
    private static final int DEFAULTBETAMOUNT = 10;


    public AutoPlayer(String name, int startingAmount, CommunityCards communityCards, Pot pot) {
        super(name, startingAmount, communityCards, pot);
        isInteractive = false;
        handEvaluator = new HandEvaluator();
        handCombiner = new HandCombiner();
    }

    public void decideAction(int lastBet) {
        boolean isHighEnough=false;
        Hand bestHand = handEvaluator.getBestHands(handCombiner.getAllHands(this.getTotalHand())).get(0);
        int handStrength = handEvaluator.handStrength(bestHand)[0];
        for (int rank : handEvaluator.handStrength(bestHand)) {
            if (rank > 10) {
                isHighEnough = true;
            }
        }
        if (handStrength > 0 || isHighEnough) {
            if(lastBet==0){
                int betAmount = computerBetAmount(DEFAULTBETAMOUNT);
                bet(betAmount);
            }
            else {
                call(lastBet);
            }

        }
        else {
            fold();
        }
    }

    private int computerBetAmount(int defaultBetAmount){
        int betAmount = defaultBetAmount;
        if(betAmount>this.getBankroll()){
            betAmount = this.getBankroll();
        }
        return betAmount;
    }
}
