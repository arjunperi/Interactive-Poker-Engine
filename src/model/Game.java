package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Game {
    private TurnManager turnManager;
    private Model model;
    private PlayerList playerList;
    private Pot pot;
    private Deck deck;
    private Dealer dealer;
    private CommunityCards communityCards;

    public Game(){
        pot = new Pot();
        //TODO: create players based on properties files / user inputs
//        Player player3 = new Player("Noah", 100, pot);

        this.communityCards = new CommunityCards();
        Player player1 = new Player("Arjun", 100, communityCards);
        Player player2 = new Player("Christian", 100, communityCards);
        deck = new Deck();
        dealer = new Dealer(deck);
        playerList = new PlayerList(new ArrayList<>(List.of(player1, player2)));
        turnManager = new TurnManager(pot);
    }


    public Model getModel() {
        return model;
    }

    public TurnManager getTurnManager(){
        return turnManager;
    }

    public Deck getDeck(){
        return deck;
    }

    public PlayerList getPlayerList(){
        return playerList;
    }

    public CommunityCards getCommunityCards(){
        return communityCards;
    }

    public Pot getPot(){
        return pot;
    }

    public Dealer getDealer(){
        return dealer;
    }


}

