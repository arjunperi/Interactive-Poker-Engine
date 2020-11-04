package model;

import java.util.ArrayList;
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
        Player player2 = new Player("Christian", 100, pot);
//        Player player3 = new Player("Noah", 100, pot);


        this.communityCards = new CommunityCards();

        deck = createDeck();

        dealer = new Dealer(deck);

        players = new PlayerList(List.of(player1, player2));
        pokerTurnManager = new TurnManager(pot);
        //we can use factory design pattern here to choose what kind of model to instantiate

        holdemDealerRules = new CommunityDealerRules(4, players, pokerTurnManager, communityCards, dealer);
    }

    public Deck createDeck(){
        List<Card> cardsList = new ArrayList<>();
        for (Suit suit : Suit.values()){
            for (int r = 2; r<15; r++){
                Card card = new Card(r,suit);
                cardsList.add(card);
            }
        }
        return new Deck(cardsList);
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

    public Pot getPot(){
        return pot;
    }



}

