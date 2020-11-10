package model;

import java.util.List;
import java.util.stream.Collectors;

public class StandardPlayerList extends PlayerList{

    public StandardPlayerList(List<Player> players, HandEvaluator handEvaluator){
        super(players, handEvaluator);
    }

    public void updateActivePlayers() {
        removeFoldedPlayers();
    }
}
