package model;

import java.util.List;

public class Game {
    private TurnManager pokerTurnManager;
    private DealerRules holdemDealerRules;
    private PlayerList players;
    private Pot pot;

    public Game(){
        pot = new Pot();
        Player player1 = new Player("Arjun", 100, pot);
        Player player2 = new Player("Christian", 100, pot);
        Player player3 = new Player("Noah", 100, pot);
        players = new PlayerList(List.of(player1, player2, player3));
        pokerTurnManager = new TurnManager(pot);
        holdemDealerRules = new CommunityDealerRules(2, players, pokerTurnManager);
    }
}

