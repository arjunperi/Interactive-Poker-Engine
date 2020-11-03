package model;

import java.util.List;
import java.util.Properties;

public class DrawDealerRules extends DealerRules {


    public DrawDealerRules(int totalRounds, PlayerList players, TurnManager turnManager, CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, turnManager, communityCards, dealer);
    }

    public void dealFlow(int currentRound){
        Properties ruleProperties = getPropertyFile("FiveCardDraw");
        for (int i=1; i<=totalRounds; i++){
            activePlayerList = pokerPlayerList.updateActivePlayers();

            String[] roundRules = ruleProperties.getProperty(String.valueOf(i)).split(",");
            int numberOfCards = Integer.parseInt(roundRules[0]);
            String propertyRecipient = roundRules[1];

            //later, use reflection for this
            if (propertyRecipient.equals("Community")){
                //We're going to want to throw an exception here
                System.out.println("No community cards in a Draw game");
            }
            else{
                for (Player player: activePlayerList){
                    dealingRound(numberOfCards, player);
                }
            }
            turnManager.startBettingRound(pokerPlayerList, totalRounds);
            startExchangeRound(2);
        }
    }

    public void startExchangeRound(int exchangeLimit) {
        for (Player currentPlayer : activePlayerList) {
            System.out.println("\n" + currentPlayer.toString() + " is up for exchange");
            pokerDealer.exchangeCards(currentPlayer, currentPlayer.chooseExchangeCards(exchangeLimit));
        }
        turnManager.startBettingRound(pokerPlayerList, totalRounds);
    }

    protected void dealingRound(int numberOfCards, CardRecipient recipient) {
        pokerDealer.dealCards(recipient, numberOfCards);
    }
    }

