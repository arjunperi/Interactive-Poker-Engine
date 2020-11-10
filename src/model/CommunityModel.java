package model;

import java.util.Properties;

public class CommunityModel extends Model {

    public CommunityModel(int totalRounds, PlayerList players,CommunityCards communityCards, Dealer dealer, Properties modelProperties){
        super(totalRounds, players, communityCards, dealer, modelProperties);
    }

    public void dealFlow(int currentRound){
        dealStats(currentRound);
        //TODO: Find a way around these conditionals
        if (recipient.equals("Community")){
            dealer.dealCards(communityCards, visibilityList);
        }
        else{
            for (Player player: activePlayerList){
                dealer.dealCards(player, visibilityList);
            }
        }
    }

}
