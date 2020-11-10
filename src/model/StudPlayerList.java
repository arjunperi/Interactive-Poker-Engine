package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudPlayerList extends PlayerList {
    private HandEvaluator handEvaluator;

    public StudPlayerList(List<Player> players){
        super(players);
        handEvaluator = new HandEvaluator();
    }

    public void updateActivePlayers() {
        removeFoldedPlayers();
        Player bestPlayer =  handEvaluator.getBestPlayers(this, true).get(0);
        int shiftAmount = activePlayers.size() - activePlayers.indexOf(bestPlayer);
        Collections.rotate(activePlayers, shiftAmount);
    }

}
