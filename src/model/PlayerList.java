package model;

import java.util.List;
import java.util.stream.Collectors;

//Encapsulaton problems?
public abstract class PlayerList {
    protected List<Player> activePlayers;

    public PlayerList(List<Player> players){
        this.activePlayers = players;
    }

    public void removeFoldedPlayers(){
        List<Player> filtered = activePlayers.stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        activePlayers.removeAll(filtered);
    }

    public abstract void updateActivePlayers();


    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
