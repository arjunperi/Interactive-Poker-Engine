package model;

//not sure if abstract class is the best way to handle this hierarchy

import java.util.List;
import java.util.Properties;

public abstract class Model {
    protected int totalRounds;
    protected Dealer pokerDealer;
    protected PlayerList pokerPlayerList;
    protected CommunityCards communityCards;
    protected List<Player> activePlayerList;
    protected int numberOfCards;
    protected String recipient;


    public Model(int totalRounds, PlayerList players, CommunityCards communityCards, Dealer dealer){
        this.totalRounds = totalRounds;
        this.communityCards = communityCards;
        pokerPlayerList = players;
        pokerDealer = dealer;
    }

    public void dealStats(int currentRound){
        pokerDealer.checkDeck();

        //TODO: Figure out a way to get the property file of the associated Model without hardcoded string
//        Properties ruleProperties = getPropertyFile("FiveCardDraw");
        Properties ruleProperties = getPropertyFile("Holdem");
        pokerPlayerList.updateActivePlayers();
        activePlayerList = pokerPlayerList.getActivePlayers();

        String[] roundRules = ruleProperties.getProperty(String.valueOf(currentRound)).split(",");
        numberOfCards = Integer.parseInt(roundRules[0]);
        recipient = roundRules[1];
    }

    public abstract void dealFlow(int currentRound);

    public abstract String getAction(int currentRound);

    public abstract void exchangeCards(Player player, List<String> exchangeCards);

    public Properties getPropertyFile(String fileName) {
        Properties propertyFile = new Properties();
        try {
            propertyFile
                    .load(CommunityModel.class.getClassLoader().getResourceAsStream(fileName + ".properties"));
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
