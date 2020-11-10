package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Encapsulaton problems?
public abstract class PlayerList {
    protected List<Player> activePlayers;
    private List<Player> removedPlayers;

    public PlayerList(List<Player> players){
        this.activePlayers = players;
        removedPlayers = new ArrayList<>();
    }

    protected void removeFoldedPlayers(){
        List<Player> filtered = activePlayers.stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        removedPlayers.addAll(filtered);
        activePlayers.removeAll(filtered);
    }

    public void restore(){
        activePlayers.addAll(removedPlayers);
    }


    public abstract void updateActivePlayers();


    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
