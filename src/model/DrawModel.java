package model;

import java.util.Properties;

public class DrawModel extends Model {


    public DrawModel(int totalRounds, PlayerList players, CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, communityCards, dealer);
    }

    public void dealFlow(int currentRound){
        //later, use reflection for this
        if (recipient.equals("Community")){
            //We're going to want to throw an exception here
            System.out.println("No community cards in a Draw game");
        }
        else{
            for (Player player: activePlayerList){
                pokerDealer.dealCards(player,numberOfCards);
            }
        }
        startExchangeRound(2);
    }

    public void startExchangeRound(int exchangeLimit) {
        for (Player currentPlayer : activePlayerList) {
            System.out.println("\n" + currentPlayer.toString() + " is up for exchange");
            pokerDealer.exchangeCards(currentPlayer, currentPlayer.chooseExchangeCards(exchangeLimit));
        }
//        turnManager.startBettingRound(pokerPlayerList, totalRounds);
    }

    }

