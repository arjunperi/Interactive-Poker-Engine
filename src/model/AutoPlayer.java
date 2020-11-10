package model;
/*
Computer-controlled poker player
 */
public class AutoPlayer extends Player {
    private HandEvaluator handEvaluator;
    private HandCombiner handCombiner;


    public AutoPlayer(String name, int startingAmount, CommunityCards communityCards, Pot pot) {
        super(name, startingAmount, communityCards, pot);
        isInteractive = false;
        handEvaluator = new HandEvaluator();
        handCombiner = new HandCombiner();
    }

    public void decideAction() {
        boolean isHighEnough=false;
        Hand bestHand = handEvaluator.getBestHands(handCombiner.getAllHands(this.getTotalHand())).get(0);
        int handStrength = handEvaluator.handStrength(bestHand)[0];
        for (int rank : handEvaluator.handStrength(bestHand)) {
            if (rank > 10) {
                isHighEnough = true;
            }
        }
        if (handStrength > 0 || isHighEnough) {
            bet(10);
        }
        else {
            fold();
        }
    }
}
