package model;

//not sure if abstract class is the best way to handle this hierarchy

import java.util.List;
import java.util.Properties;

public abstract class DealerRules {
    protected int totalRounds;
    protected Dealer pokerDealer;
    protected PlayerList pokerPlayerList;
    protected TurnManager turnManager;
    protected CommunityCards communityCards;
    protected List<Player> activePlayerList;
    protected int numberOfCards;
    protected String recipient;
    protected int currentRound;


    public DealerRules(int totalRounds, PlayerList players, TurnManager turnManager, CommunityCards communityCards, Dealer dealer){
        this.totalRounds = totalRounds;
        this.communityCards = communityCards;
        pokerPlayerList = players;
        this.turnManager = turnManager;
        pokerDealer = dealer;
    }


    public void dealStats(int currentRound){
        pokerDealer.checkDeck();

        Properties ruleProperties = getPropertyFile("HoldEm");
        pokerPlayerList.updateActivePlayers();
        activePlayerList = pokerPlayerList.getPlayers();

        String[] roundRules = ruleProperties.getProperty(String.valueOf(currentRound)).split(",");
        numberOfCards = Integer.parseInt(roundRules[0]);
        recipient = roundRules[1];
    }

    //if we're paused, then we don't want to do dealflow
    //we don;t w

    public abstract void dealFlow(int currentRound);

    protected abstract void dealingRound(int numberOfCards, CardRecipient recipient);

    public Properties getPropertyFile(String fileName) {
        Properties propertyFile = new Properties();
        try {
            propertyFile
                    .load(CommunityDealerRules.class.getClassLoader().getResourceAsStream(fileName + ".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyFile;
    }

    public int getNumberOfCards(){
        return numberOfCards;
    }

    public String getRecipient(){
        return recipient;
    }

}
