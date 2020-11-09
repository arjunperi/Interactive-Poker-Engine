package model;

import java.util.List;
import java.util.Properties;

public class StudModel extends Model {

    public StudModel(int totalRounds, PlayerList players,CommunityCards communityCards, Dealer dealer, Properties modelProperties){
        super(totalRounds, players, communityCards, dealer, modelProperties);
    }

    public void dealFlow(int currentRound){
        //TODO: find a way around this conditional
        dealStats(currentRound);
        if (recipient.equals("Community")){
            //TODO: Throw an exception here
            System.out.println("No community cards in a Draw game");
        }
        else{
            for (Player player: activePlayerList){
                dealer.dealCards(player, visibilityList);
            }
        }
    }

    //dealing is different in that we need to deal visible/non visible
    //turns are different
        //first round - lowest exposed card goes first
            //clubs diamonds hearts spades (for ties)
        //next round - exposed hand (2 cards) with highest poker value goes first
        //next round - exposed hand (3 cards) with highest poker value goes first
        // next round - exposed hand (4 cards) with highest poker value goes first
        // next round - exposed hand (4 cards) with highest poker value goes first



    //at start of game, deal 2 cards face down, 1 card face up per person
        //after first round of betting, deal 1 face up card per player
        //after second round of betting, deal 1 face up card per player
        //after third round of betting, deal 1 face up card per player
        //after fourth round of betting, deal 1 face down card per player
}
