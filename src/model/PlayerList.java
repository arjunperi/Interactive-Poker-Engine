package model;

import java.util.List;
import java.util.stream.Collectors;

//This class is kinda busted
public class PlayerList {
    private List<Player> activePlayers;

    public PlayerList(List<Player> players){
        this.activePlayers = players;
    }

    public void updateActivePlayers(){
        List<Player> filtered = activePlayers.stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        activePlayers.removeAll(filtered);
    }

    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
