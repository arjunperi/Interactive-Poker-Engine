package model;

//not sure if abstract class is the best way to handle this hierarchy

import java.util.*;

public abstract class Model {
    protected int totalRounds;
    protected Dealer dealer;
    protected PlayerList playerList;
    protected CommunityCards communityCards;
    protected List<Player> activePlayerList;
    protected int numberOfCards;
    protected String recipient;
    protected int faceUpCards;
    protected int faceDownCards;
    protected List<Boolean> visibilityList;
    protected Properties modelProperties;



    public Model(int totalRounds, PlayerList players, CommunityCards communityCards, Dealer dealer, Properties modelProperties){
        this.totalRounds = totalRounds;
        this.communityCards = communityCards;
        playerList = players;
        this.dealer = dealer;
        this.modelProperties = modelProperties;
        visibilityList = new ArrayList<>();
    }

    public void dealStats(int currentRound){
        dealer.checkDeck();

        playerList.updateActivePlayers();
        activePlayerList = playerList.getActivePlayers();

        String[] roundRules = modelProperties.getProperty(String.valueOf(currentRound)).split(",");
        numberOfCards = Integer.parseInt(roundRules[0]);
        recipient = roundRules[1];

        String[] roundVisibility = modelProperties.getProperty("visibility" + currentRound).split(",");
        visibilityList.clear();
        faceDownCards = Integer.parseInt(roundVisibility[0]);
        populateVisibilityList(faceDownCards, false);
        faceUpCards = Integer.parseInt(roundVisibility[1]);
        populateVisibilityList(faceUpCards, true);
    }


    public String getAction(int roundNumber){
        String action = modelProperties.getProperty("action" + roundNumber);
        return action;
    }

    private void populateVisibilityList(int numberOfCards, boolean isVisible){
        for (int i=0; i < numberOfCards; i++){
            visibilityList.add(isVisible);
        }
    }

    public abstract void dealFlow(int currentRound);

    public int getNumberOfCards(){
        return numberOfCards;
    }

    public String getRecipient(){
        return recipient;
    }

}
