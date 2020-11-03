package model;

import java.util.List;
import java.util.Properties;

public class CommunityDealerRules extends DealerRules{

    public CommunityDealerRules(int totalRounds, PlayerList players, TurnManager turnManager, CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, turnManager, communityCards, dealer);
    }


    public void dealFlow(int currentRound){
        //later, use reflection for this
        if (recipient.equals("Community")){
            dealingRound(numberOfCards,communityCards);
        }
        else{
            for (Player player: activePlayerList){
                dealingRound(numberOfCards, player);
            }
        }
        System.out.println("Community cards: ");
        for (Card card : communityCards.getCommunityCardsList()){
            System.out.println(card.getCardValue());
        }
//        turnManager.startBettingRound(pokerPlayerList, totalRounds);
    }

    protected void dealingRound(int numberOfCards, CardRecipient recipient){
        pokerDealer.dealCards(recipient, numberOfCards);
    }

}
