package model;


import controller.JSONReader;
import java.util.ArrayList;
import java.util.List;


public class Game {
    private RoundManager roundManager;
    private Pot pot;
    private Deck deck;
    private Dealer dealer;
    private CommunityCards communityCards;
    private List<String> suits;
    private List<String> ranks;
    private JSONReader reader;
    private HandEvaluator handEvaluator;

    //TODO: Game should be constructed frpm Pot, List of Players, Deck, and Dealer (rather than having them be created here)
    public Game(){
        pot = new Pot();
        this.communityCards = new CommunityCards();

        reader = new JSONReader();
        reader.parse("/texas_holdem.json");

        deck = new Deck(reader.getSuitNames(), reader.getRankValues());

        dealer = new Dealer(deck);
        handEvaluator = new HandEvaluator();
        roundManager = new RoundManager(pot);
    }

    public RoundManager getTurnManager(){
        return roundManager;
    }

    public Deck getDeck(){
        return deck;
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

    public HandEvaluator getHandEvaluator(){
        return handEvaluator;
    }


}

