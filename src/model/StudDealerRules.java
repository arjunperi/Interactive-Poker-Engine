package model;

public class StudDealerRules extends DealerRules{

    public StudDealerRules(int totalRounds, PlayerList players, TurnManager turnManager){
        super(totalRounds, players, turnManager);
    }

    protected void dealFlow(){}

    protected void dealingRound(int numberOfCards, CardRecipient recipient) {

    }
    //at start of game, deal 2 cards face up, 1 card face down per person
        //after first round of betting, deal 1 face up card per player
        //after second round of betting, deal 1 face up card per player
        //after third round of betting, deal 1 face up card per player
        //after fourth round of betting, deal 1 face down card per player
}
