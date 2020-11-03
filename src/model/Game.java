package model;

import java.util.List;

public class Game {
    private TurnManager pokerTurnManager;
    private DealerRules holdemDealerRules;
    private DealerRules drawDealerRules;
    private PlayerList players;
    private Pot pot;
    private Deck deck;
    private Dealer dealer;
    private CommunityCards communityCards;

    public Game(){
        pot = new Pot();
        Player player1 = new Player("Arjun", 100, pot);
//        Player player2 = new Player("Christian", 100, pot);
//        Player player3 = new Player("Noah", 100, pot);


        this.communityCards = new CommunityCards();

        Card card1 = new Card(2);
        Card card2 = new Card(3);
        Card card3 = new Card(4);
        Card card4 = new Card(5);
        Card card5 = new Card(6);
        Card card6 = new Card(7);
        Card card7 = new Card(8);
        Card card8 = new Card(9);
        Card card9 = new Card(10);
        Card card10 = new Card(11);
        Card card11 = new Card(12);
        Card card12 = new Card(13);
        Card card13 = new Card(14);
        deck = new Deck(List.of(card1,card2,card3, card4, card5, card6, card7, card8, card9, card10, card11,
                card12,card13));
        dealer = new Dealer(deck);

        players = new PlayerList(List.of(player1));
        pokerTurnManager = new TurnManager(pot);
        //we can use factory design pattern here to choose what kind of model to instantiate

        holdemDealerRules = new CommunityDealerRules(4, players, pokerTurnManager, communityCards, dealer);
    }

    public DealerRules getDealerRules() {
        return holdemDealerRules;
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



}

