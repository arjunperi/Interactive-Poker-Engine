package model;

import java.util.List;
import java.util.Properties;

public class CommunityDealerRules extends DealerRules{


    public CommunityDealerRules(int totalRounds, PlayerList players, TurnManager turnManager){
        super(totalRounds, players, turnManager);
    }

    public void temporaryMapping(){}

    //need to step through this

    //rather than doing if statements to get the round, what could be a better way to do this?
    //if a user wanted to come in and say -> okay, at round one I want you to deal a different amount of cards
        //wouldn't be too big of a deal, just have the number of cards read in from a file (per round)?

    //instead, get rounds completed from turn manager
        //0 rounds completed -> deal the hole cards
        //1 round completed -> deal flop
        //2 rounds completed -> deal turn
        //3 rounds completed -> deal river

    //Or, the turn manager could get the rounds from the Dealer Rules
        //1st round of dealing done (hole cards) -> 1st round of betting starts
        //2nd round of dealing done (flop) -> 2nd round of betting starts
        //3rd round of dealing done (turn) -> 3rd round of betting starts
        //4th round of dealing done (river) -> 4th round of betting starts

    //things a user should be able to change within CommunityCard games
        //number of cards dealt in the beginning
        //number of communal cards dealt for rounds
            //as long as this combo leads to at least 5 cards per player
        //could the number of rounds change?
            //theoretically yes
                //in which case the if statements would not hold up
        //maybe it's not always hole cards first -> what if we wanted to switch the order and say maybe deal the flop first before you even get your cards?
            //how would user input this?
            //round 1, communal, 3
            //that could work -> then, we'd need a dealingRound class?

        //number of cards needed to use from hand
            //as long as this is less than or equal to number of hole cards dealt to a person
            //dealer rules doesn't really care about this

    //things user should be able to change within Draw Games
        //same as above

        //number of cards you are allowed to trade
            //don't think dealer rules really cares about this

    //things user should be able to change within stud games
        //same as above
        //whether a card is face up or face down
            //does dealer rules need to know this?
                //probably, since it tells the dealer how to deal

    //So -> need to know what round it is
        //Number of cards dealt read in
        //number of communal cards dealt read in

    //Most extensible -> have the round actions be their own subclasses?


    protected void dealFlow(){
        Properties ruleProperties = getPropertyFile("HoldEm");
        //total rounds will also be read in from a file
        for (int i=1; i<=totalRounds; i++){
            activePlayerList = pokerPlayerList.updateActivePlayers();

            String[] roundRules = ruleProperties.getProperty(String.valueOf(i)).split(",");
            int numberOfCards = Integer.parseInt(roundRules[0]);
            String propertyRecipient = roundRules[1];

            //later, use reflection for this
            if (propertyRecipient.equals("Community")){
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
            turnManager.startBettingRound(pokerPlayerList, totalRounds);
        }
    }


    protected void dealingRound(int numberOfCards, CardRecipient recipient){
        pokerDealer.dealCards(recipient, numberOfCards);
    }

}
