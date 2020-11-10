package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudPlayerList extends PlayerList {

    public StudPlayerList(List<Player> players, HandEvaluator handEvaluator){
        super(players, handEvaluator);
    }

    public void updateActivePlayers() {
        removeFoldedPlayers();
        Player bestPlayer =  handEvaluator.getBestPlayers(this).get(0);
        int shiftAmount = activePlayers.size() - activePlayers.indexOf(bestPlayer);
        Collections.rotate(activePlayers, shiftAmount);
    }

}
