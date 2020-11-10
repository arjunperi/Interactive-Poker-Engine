package model;

import java.util.List;
import java.util.stream.Collectors;

public class StandardPlayerList extends PlayerList{

    public StandardPlayerList(List<Player> players){
        super(players);
    }

    public void updateActivePlayers() {
        removeFoldedPlayers();
    }
}
