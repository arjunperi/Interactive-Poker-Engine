package model;

import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private TurnManager pokerTurnManager;
    private Model holdemModel;
    private Model drawModel;
    private PlayerList players;
    private Pot pot;
    private Deck deck;
    private Dealer dealer;
    private CommunityCards communityCards;
    private List<String> suits;
    private List<String> ranks;
    private JSONReader reader;

    public Game(){
        pot = new Pot();
        //TODO: create players based on properties files / user inputs
        Player player1 = new Player("Arjun", 100, pot);
        Player player2 = new Player("Christian", 100, pot);
//        Player player3 = new Player("Noah", 100, pot);


        this.communityCards = new CommunityCards();

        reader = new JSONReader();
        reader.parse("/texas_holdem.json");
        List<Integer> rankValues = new ArrayList<>(reader.getRanks().values());
        List<String> suitNames = new ArrayList<>(reader.getSuits().keySet());

        //deck = new Deck();
        deck = new Deck(suitNames, rankValues);


        dealer = new Dealer(deck);

        players = new PlayerList(new ArrayList<>(List.of(player1, player2)));
        pokerTurnManager = new TurnManager(pot);

        //TODO: use factory design pattern here to choose what kind of model to instantiate
        holdemModel = new CommunityModel(4, players, communityCards, dealer);
//        drawModel = new DrawModel(2, players, communityCards, dealer);
    }


    public Model getModel() {
        return holdemModel;
//        return drawModel;
    }

    public TurnManager getTurnManager(){
        return pokerTurnManager;
    }

    public Deck getDeck(){
        return deck;
    }

    public PlayerList getPlayers(){
        return players;
    }

    public CommunityCards getCommunityCards(){
        return communityCards;
    }

    public Pot getPot(){
        return pot;
    }



}

