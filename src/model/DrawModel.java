package model;

import java.util.Properties;

public class DrawModel extends Model {


    public DrawModel(int totalRounds, PlayerList players,CommunityCards communityCards, Dealer dealer, Properties modelProperties){
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
    }

