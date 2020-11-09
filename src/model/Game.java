package model;

public class Game {
    private TurnManager turnManager;
    private Pot pot;
    private Deck deck;
    private Dealer dealer;
    private CommunityCards communityCards;
    private HandEvaluator handEvaluator;

    public Game(){
        pot = new Pot();
        this.communityCards = new CommunityCards();
        deck = new Deck();
        dealer = new Dealer(deck);
        handEvaluator = new HandEvaluator();
        turnManager = new TurnManager(pot);
    }

    public TurnManager getTurnManager(){
        return turnManager;
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

