package model;

import java.util.List;

public class StudModel extends Model {

    public StudModel(int totalRounds, PlayerList players, CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, communityCards, dealer);
    }

    public void dealFlow(int currentRound){}

    @Override
    public String getAction(int currentRound) {
        return null;
    }

    @Override
    public void exchangeCards(Player player, List<String> exchangeCards) {

    }

    //at start of game, deal 2 cards face up, 1 card face down per person
        //after first round of betting, deal 1 face up card per player
        //after second round of betting, deal 1 face up card per player
        //after third round of betting, deal 1 face up card per player
        //after fourth round of betting, deal 1 face down card per player
}
