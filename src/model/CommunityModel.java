package model;

public class CommunityModel extends Model {

    public CommunityModel(int totalRounds, PlayerList players,CommunityCards communityCards, Dealer dealer){
        super(totalRounds, players, communityCards, dealer);
    }


    public void dealFlow(int currentRound){
        dealStats(currentRound);
        //remove conditionals?
        //use reflection?
        if (recipient.equals("Community")){
            pokerDealer.dealCards(communityCards, numberOfCards);
        }
        else{
            for (Player player: activePlayerList){
                pokerDealer.dealCards(player,numberOfCards);
            }
        }
        System.out.println("Community cards: ");
        for (Card card : communityCards.getCommunityCardsList()){
            System.out.println(card.getRank());
        }
    }
}
