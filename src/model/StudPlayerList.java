package model;

import controller.JSONReader;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StudPlayerList extends PlayerList {
    private HandEvaluator handEvaluator;
    private Map<Integer,String> handStrengths;
    private JSONReader reader;

    public StudPlayerList(List<Player> players) {
        super(players);
        reader = new JSONReader();
        reader.parse("/cardSettings.json");
        handEvaluator = new HandEvaluator(reader.getStrengths());
    }


    public void initializeActivePlayers() {
        //why did we have this line -> so that betting order is reset after all the raise shifts
        removeFoldedPlayers();
        studOrder();
    }

    public void updateActivePlayers() {
        resetActivePlayers();
        initializeActivePlayers();
    }

    private void studOrder(){
            Player bestPlayer =  handEvaluator.getBestPlayers(this, true).get(0);
            int shiftAmount = activePlayers.size() - activePlayers.indexOf(bestPlayer);
            Collections.rotate(activePlayers, shiftAmount);
            raiseShift();
        }

}
