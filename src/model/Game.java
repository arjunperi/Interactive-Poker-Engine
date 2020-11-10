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

    //TODO: Game should be constructed frpm Pot, List of Players, Deck, and Dealer (rather than having them be created here)
    public Game(){
        pot = new Pot();
        //TODO: create players based on properties files / user inputs
        Player player1 = new Player("Arjun", 100, pot);
        Player player2 = new Player("Christian", 100, pot);
//        Player player3 = new Player("Noah", 100, pot);


        this.communityCards = new CommunityCards();

        reader = new JSONReader();
        reader.parse("/texas_holdem.json");

        //deck = new Deck();
        deck = new Deck(reader.getSuitNames(), reader.getRankValues());


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

