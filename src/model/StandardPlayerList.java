package model;

import java.util.List;

public class StandardPlayerList extends PlayerList{

    public StandardPlayerList(List<Player> players){
        super(players);
    }

    public void initializeActivePlayers() {
        //why did we have this line -> so that betting order is reset after all the raise shifts
        removeFoldedPlayers();
        raiseShift();
    }
    public void updateActivePlayers() {
        //why did we have this line -> so that betting order is reset after all the raise shifts
        resetActivePlayers();
        initializeActivePlayers();
    }


}
