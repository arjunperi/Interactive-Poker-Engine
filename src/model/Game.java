package model;

import java.util.List;

public class Game {
    private TurnManager pokerTurnManager;
    private DealerRules holdemDealerRules;
    private DealerRules drawDealerRules;
    private PlayerList players;
    private Pot pot;

    public Game(){
        pot = new Pot();
        Player player1 = new Player("Arjun", 100, pot);
        Player player2 = new Player("Christian", 100, pot);
//        Player player3 = new Player("Noah", 100, pot);
        players = new PlayerList(List.of(player1, player2));
        pokerTurnManager = new TurnManager(pot);
        //we can use factory design pattern here to choose what kind of model to instantiate
        drawDealerRules = new DrawDealerRules(2, players, pokerTurnManager);
//        holdemDealerRules = new CommunityDealerRules(4, players, pokerTurnManager);
    }
}

