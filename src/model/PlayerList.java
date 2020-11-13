package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Encapsulaton problems?
public abstract class PlayerList {
    protected List<Player> activePlayers;
    private List<Player> removedPlayers;
    protected Player playerUp;
    protected int lastBet;
    protected boolean raiseMade;

    public PlayerList(List<Player> players){
        this.activePlayers = players;
        removedPlayers = new ArrayList<>();
        lastBet = 0;
        raiseMade = false;
    }

    protected void removeFoldedPlayers(){
        List<Player> filtered = activePlayers.stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        removedPlayers.addAll(filtered);
        activePlayers.removeAll(filtered);
    }

    public abstract void updateActivePlayers();


    public boolean raiseMade(Player player) {
        playerUp = player;
        if (playerUp.getBetAmount() > lastBet) {
            raiseMade = true;
            lastBet = playerUp.getBetAmount();
        }
        return raiseMade;
    }

    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
