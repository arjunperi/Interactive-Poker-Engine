package model;

import java.util.List;

public class CommunityDealerRules implements DealerRules{
    private int totalRounds;
    private Dealer pokerDealer;
    private PlayerList pokerPlayerList;
    private List<Player> activePlayerList;
    private TurnManager turnManager;

    public CommunityDealerRules(int totalRounds, PlayerList players, TurnManager turnManager){
        this.totalRounds = totalRounds;
        pokerDealer = new Dealer();
        pokerPlayerList = players;
        this.turnManager = turnManager;
        dealFlow();
    }

    //need to step through this
    @Override
    public void dealFlow(){
        for (int i=0; i< totalRounds; i++){
            activePlayerList = pokerPlayerList.updateActivePlayers();
            if (i == 0){
                for (Player player: activePlayerList)
                    pokerDealer.dealCards(player, 2);
            }
            if (i==1){
                pokerDealer.dealCommunityCards(3);
            }
            if (i==2){
                pokerDealer.dealCommunityCards(1);
            }
            if (i ==3){
                pokerDealer.dealCommunityCards(1);
            }
            turnManager.startBettingRound(pokerPlayerList, totalRounds);
        }
    }
}
