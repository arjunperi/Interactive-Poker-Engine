package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Encapsulaton problems?
public abstract class PlayerList {
    protected List<Player> activePlayers;
    protected List<Player> allPlayers;
    private List<Player> removedPlayers;
    private Player playerUp;
    private int lastBet;
    private boolean raiseMade;
    private Player raiseSeat;

    public PlayerList(List<Player> players){
        this.allPlayers = new ArrayList<>(players);
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

    public List<Player> getAllPlayers(){
        return allPlayers;
    }

    public void updateStartingRoundOrder(){
        Collections.rotate(allPlayers, -1);
    }

    public abstract void updateActivePlayers();

    public void resetActivePlayers(){
        activePlayers =  new ArrayList<>(allPlayers);
    }

    public boolean raiseMade(Player player) {
        playerUp = player;
        if (playerUp.getBetAmount() > lastBet) {
            System.out.println(playerUp.toString() + " has raised");
            raiseMade = true;
            lastBet = playerUp.getBetAmount();
            raiseSeat = player;
        }
        playerUp.clearBetAmount();
        return raiseMade;
    }

    public void raiseShift(){
        if (raiseMade){
            int shiftIndex = activePlayers.indexOf(playerUp) + 1;
            Collections.rotate(activePlayers, -shiftIndex);
        }
        //we only want to do this once the betting round is over
//        lastBet = 0;
        raiseMade = false;
    }

    public int getLastBet(){
        return lastBet;
    }

    public Player getRaiseSeat(){
        return raiseSeat;
    }

    public void resetRaiseStats(){
        lastBet = 0;
        raiseSeat = null;
    }

    public List<Player> getActivePlayers(){
        return activePlayers;
    }
}
