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



    public DealerRules(int totalRounds, PlayerList players, TurnManager turnManager){
        this.totalRounds = totalRounds;
        this.communityCards = new CommunityCards();
        pokerDealer = new Dealer(communityCards);
        pokerPlayerList = players;
        this.turnManager = turnManager;
        dealFlow();
    }

    protected abstract void dealFlow();

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
}
