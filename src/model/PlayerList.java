package model;

import java.util.ArrayList;
import java.util.List;

//This class is kinda busted
public class PlayerList {
    private List<Player> players;

    public PlayerList(List<Player> players){
        this.players = players;
    }

    public void updateActivePlayers(){
        for (Player player : players){
            if (!player.isActive()){
                players.remove(player);
            }
        }
    }

    public List<Player> getPlayers(){
        return players;
    }
}
