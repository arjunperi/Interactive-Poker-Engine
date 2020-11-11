package model;

public class Game {
    private RoundManager roundManager;
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

