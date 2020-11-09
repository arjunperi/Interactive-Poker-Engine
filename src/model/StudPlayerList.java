package model;

import java.util.List;

public class StudPlayerList extends PlayerList {

    public StudPlayerList(List<Player> players){
        super(players);
    }

    @Override
    void updateActivePlayers() {
        removeFoldedPlayers();
        //go through, get each player's total visible hand
        //find the best
    }

    private void bestCurrentHand(){
        //send you all the players
        //return to me the player who has best
    }

    //public -> take in a list of players, give me the best player
    //public -> take in a hand, give me my best hand
}
