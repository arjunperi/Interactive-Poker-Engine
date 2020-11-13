package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Encapsulaton problems?
public abstract class PlayerList {
    protected List<Player> activePlayers;
    private List<Player> allPlayers;
    private List<Player> removedPlayers;
    
    public PlayerList(List<Player> players){
        this.allPlayers = new ArrayList<>(players);
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

    public List<Player> getAllPlayers(){
        return allPlayers;
    }

    public void updateStartingRoundOrder(){
        Collections.rotate(allPlayers, -1);
//        activePlayers = new ArrayList<>(allPlayers);
    }

    public abstract void updateActivePlayers();

    public void resetActivePlayers(){
        activePlayers =  new ArrayList<>(allPlayers);
    }


    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
