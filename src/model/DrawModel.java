package model;

import java.util.List;
import java.util.Properties;

public class DrawModel extends Model {


    public DrawModel(int totalRounds, PlayerList players, CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, communityCards, dealer);
    }

    public void dealFlow(int currentRound){
        //later, use reflection for this
        dealStats(currentRound);
        if (recipient.equals("Community")){
            //TODO: Throw an exception here
            System.out.println("No community cards in a Draw game");
        }
        else{
            for (Player player: activePlayerList){
                pokerDealer.dealCards(player,numberOfCards);
            }
        }
    }

    public String getAction(int currentRound){
        Properties ruleProperties = getPropertyFile("FiveCardDraw");
        String action  = ruleProperties.getProperty("action" + currentRound);
        return action;
    }

    //should it be in this class?
    public Card stringToCard(String cardString){
        /*int spaceIndex = cardString.indexOf(" ");
        Suit suit = Suit.valueOf(cardString.substring(0,spaceIndex));
        int rank = Integer.parseInt(cardString.substring(spaceIndex + 1));
        return new Card(rank, suit);*/
        return new Card(Integer.parseInt(cardString.split(" ")[0]), cardString.split(" ")[0]);
    }

    public void exchangeCards(Player player, List<String> exchangeCards) {
        player.discardedCardList.clear();
        player.newCardList.clear();
        for (String exchangeCard: exchangeCards){
            Card card = stringToCard(exchangeCard);
            pokerDealer.exchangeCards(player, card);
        }
    }
    }

