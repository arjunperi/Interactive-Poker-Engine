package model;

import java.util.ArrayList;
import java.util.List;

//This class is kinda busted
public class PlayerList {
    private List<Player> allPlayers;
    private List<Player> activePlayers;

    public PlayerList(List<Player> players){
        allPlayers = players;
        activePlayers = new ArrayList<>();
    }

    public List<Player> getAllPlayers(){
        return allPlayers;
    }

    public List<Player> updateActivePlayers(){
        activePlayers.clear();
        for (Player player : allPlayers){
            if (player.isActive()){
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }
}
