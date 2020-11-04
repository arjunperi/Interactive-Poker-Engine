package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

//This class is kinda busted
public class PlayerList {
    private List<Player> players;

    public PlayerList(List<Player> players){
        this.players = players;
    }

//    public void updateActivePlayers(){
//        ListIterator<Player> player = players.listIterator();
//        while (player.hasNext()) {
//            Player currentPlayer = player.next();
//            if (!currentPlayer.isActive()) {
//                player.remove();
//            }
//        }
//    }

    public void updateActivePlayers(){
        List<Player> filtered = players.stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        players.removeAll(filtered);
    }







//    public void updateActivePlayers(){
//        for (Player player : players){
//            if (!player.isActive()){
//                players.remove(player);
//            }
//        }
//    }

    public List<Player> getPlayers(){
        return players;
    }
}
