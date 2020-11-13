package model;

import java.util.Collections;
import java.util.List;

public class StandardPlayerList extends PlayerList{

    public StandardPlayerList(List<Player> players){
        super(players);
    }

    public void updateActivePlayers() {
        removeFoldedPlayers();
        if (raiseMade){
            int shiftIndex = activePlayers.indexOf(playerUp);
            Collections.rotate(activePlayers, -shiftIndex);
        }
        raiseMade = false;
    }


}
