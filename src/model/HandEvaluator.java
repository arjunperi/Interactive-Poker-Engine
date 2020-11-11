package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {
    private HandCombiner combiner;
    private Map<Hand, Player> bestHandMapping;

    private static final int POKERHANDSIZE = 5;
    private static final int DUMMYCARDRANK = -1;
    private static final int STRENGTHINDEX = 0;
    private static final int STRAIGHTFLUSHRANK = 8;
    private static final int FOUROFAKINDRANK = 7;
    private static final int FULLHOUSERANK = 6;
    private static final int FLUSHRANK = 5;
    private static final int STRAIGHTRANK = 4;
    private static final int THREEOFAKINDRANK = 3;
    private static final int TWOPAIRRANK = 2;
    private static final int PAIRRANK = 1;
    private static final int HIGHCARDRANK = 0;



    public HandEvaluator(){
        combiner = new HandCombiner();
        bestHandMapping = new HashMap<>();
    }

    public boolean isFiveCardHand(Hand hand){
        for(Card card : hand.getCards()){
            if(card.getRank()<1 || hand.getHandSize()!=POKERHANDSIZE){
                return false;
            }
        }
        return true;
    }

    //hands need to be sorted in descending order for the booleans

    public boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public boolean isFourOfAKind(Hand hand) {
        if(rankCount(hand,DUMMYCARDRANK)==4){ return false;}
        if (rankCount(hand, hand.get(0).getRank()) == 4 || rankCount(hand, hand.get(1).getRank()) == 4) {
            return true;
        }
        return false;
    }

    public boolean isFullHouse(Hand hand) {
        if ((rankCount(hand, hand.get(0).getRank()) == 3 && rankCount(hand, hand.get(3).getRank()) == 2) || (rankCount(hand, hand.get(0).getRank()) == 2 && rankCount(hand, hand.get(2).getRank()) == 3)) {
            return isFiveCardHand( hand);        }
        return false;
    }

    public boolean isFlush(Hand hand) {
        Suit flushSuit = hand.get(0).getCardSuit();
        for (Card card : hand.getCards()) {
            if (card.getCardSuit() != flushSuit) {
                return false;
            }
        }
        return isFiveCardHand(hand);
    }


    public boolean isStraight(Hand hand) {
        for (int i = 1; i < hand.getHandSize(); i++) {
            if (hand.get(i).getRank() != (hand.get(i - 1).getRank() - 1)) {
                if (hand.get(0).getRank() == 14) {
                    Suit aceSuit = hand.get(0).getCardSuit();
                    Hand tempHand = hand.copyHand();
                    tempHand.remove(hand.get(0));
                    tempHand.add(new Card(1, aceSuit));
                    return isStraight(tempHand);
                }
                return false;
            }
        }
         return isFiveCardHand(hand);
    }

    public boolean isThreeOfAKind(Hand hand) {
        if(rankCount(hand,DUMMYCARDRANK)==3){ return false;}
        if ((rankCount(hand, hand.get(0).getRank()) == 3) || (rankCount(hand, hand.get(1).getRank()) == 3) || (rankCount(hand, hand.get(2).getRank()) == 3)) {
            return true;
        }
        return false;
    }

    public boolean isTwoPair(Hand hand) {
        if(rankCount(hand,DUMMYCARDRANK)==2){ return false;}
        if ((rankCount(hand, hand.get(1).getRank()) == 2 && rankCount(hand, hand.get(3).getRank()) == 2)) {
            return true;
        }
        return false;
    }

    public boolean isPair(Hand hand) {
        for (Card card : hand.getCards()) {
            if ((rankCount(hand, card.getRank())) == 2 && card.getRank()!=DUMMYCARDRANK){
                return true;
            }
        }
        return false;
    }

    public boolean isHighCard(Hand hand) {return true;}

//put in a hand and a rank and returns how many times that rank was in the hand
    public int rankCount(Hand hand, int rank) {
        int rankCount = 0;
        for (Card card : hand.getCards()) {
            if (card.getRank() == rank) {
                rankCount++;
            }
        }
        return rankCount;
    }

//Hands need to be sorted in descending order before put into formatting methods

    public int[] formatStraightFlush(Hand hand) {
        int[] orderedHand = makeNewArray();
        if (hand.get(0).getRank() == 14 && hand.get(1).getRank() == 5) {
            Suit aceSuit = hand.get(0).getCardSuit();
            hand.remove(hand.get(0));
            hand.add(new Card(1, aceSuit));
        }
        for (int index = 0; index < orderedHand.length; index++) {
            orderedHand[index] = hand.get(index).getRank();
        }
        return orderedHand;
    }

    public int[] formatFourOfAKind(Hand hand) {
        int[] orderedHand = makeNewArray();
        int index = 0;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 4) {
                orderedHand[index] = card.getRank();
                index++;
            } else if (rankCount(hand, card.getRank()) == 1) {
                orderedHand[4] = card.getRank();
            }
        }
        return orderedHand;
    }


    public int[] formatFullHouse(Hand hand) {
        int[] orderedHand = makeNewArray();
        int index1 = 0;
        int index2 = 3;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 3) {
                orderedHand[index1] = card.getRank();
                index1++;
            } else if (rankCount(hand, card.getRank()) == 2) {
                orderedHand[index2] = card.getRank();
                index2++;
            }
        }
        return orderedHand;
    }

    public int[] formatFlush(Hand hand) {
        int[] orderedHand = makeNewArray();
        for (int index = 0; index < orderedHand.length; index++) {
            orderedHand[index] = hand.get(index).getRank();
        }
        return orderedHand;
    }

    public int[] formatStraight(Hand hand) {
        return formatStraightFlush(hand);
    }


    public int[] formatThreeOfAKind(Hand hand) {
        int[] orderedHand = makeNewArray();
        int index1 = 0;
        int index2 = 3;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 3) {
                orderedHand[index1] = card.getRank();
                index1++;
            } else if (rankCount(hand, card.getRank()) == 1) {
                orderedHand[index2] = card.getRank();
                index2++;
            }
        }
        return orderedHand;
    }

    public int[] formatTwoPair(Hand hand) {
        int[] orderedHand = makeNewArray();
        int index1 = 0;
        int index2 = 4;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 2) {
                orderedHand[index1] = card.getRank();
                index1++;
            } else if (rankCount(hand, card.getRank()) == 1) {
                orderedHand[index2] = card.getRank();
            }
        }
        return orderedHand;
    }

    public int[] formatPair(Hand hand) {
        int[] orderedHand = makeNewArray();
        int index1 = 0;
        int index2 = 2;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 2) {
                orderedHand[index1] = card.getRank();
                index1++;
            } else if (rankCount(hand, card.getRank()) == 1) {
                orderedHand[index2] = card.getRank();
                index2++;
            }
        }
        return orderedHand;
    }

    public int[] formatHighCard(Hand hand) {
        int[] orderedHand = makeNewArray();
        for (int index = 0; index < orderedHand.length; index++) {
            orderedHand[index] = hand.get(index).getRank();
        }
        return orderedHand;
    }


    public int[] handStrength(Hand hand) {
        int[] handRank = new int[6];
        if (isStraightFlush(hand)) {
            handRank[STRENGTHINDEX] = STRAIGHTFLUSHRANK;
            int[] formattedHand = formatStraightFlush(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFourOfAKind(hand)) {
            handRank[STRENGTHINDEX] = FOUROFAKINDRANK;
            int[] formattedHand = formatFourOfAKind(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFullHouse(hand)) {
            handRank[STRENGTHINDEX] = FULLHOUSERANK;
            int[] formattedHand = formatFullHouse(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFlush(hand)) {
            handRank[STRENGTHINDEX] = FLUSHRANK;
            int[] formattedHand = formatFlush(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isStraight(hand)) {
            handRank[STRENGTHINDEX] = STRAIGHTRANK;
            int[] formattedHand = formatStraight(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isThreeOfAKind(hand)) {
            handRank[STRENGTHINDEX] = THREEOFAKINDRANK;
            int[] formattedHand = formatThreeOfAKind(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isTwoPair(hand)) {
            handRank[STRENGTHINDEX] = TWOPAIRRANK;
            int[] formattedHand = formatTwoPair(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isPair(hand)) {
            handRank[STRENGTHINDEX] = PAIRRANK;
            int[] formattedHand = formatPair(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isHighCard(hand)) {
            handRank[STRENGTHINDEX] = HIGHCARDRANK;
            int[] formattedHand = formatHighCard(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        return handRank;
    }

    public List<Hand> getBestHands(List<Hand> hands) {
        int[] bestHand = new int[6];
        boolean isSame;
        int index;
        ArrayList<Hand> bestHands = new ArrayList<>();
        for (Hand hand : hands) {
            index = 0;
            isSame = true;
            while (isSame && index < 6) {
                int[] handStrength = handStrength(hand);
                if (handStrength[index] > bestHand[index]) {
                    bestHand = handStrength;
                    isSame = false;
                } else if (handStrength[index] == bestHand[index]) {
                    index++;
                } else if (handStrength[index] < bestHand[index]) {
                    isSame = false;
                }
            }
        }
        for (Hand hand : hands) {
            boolean shouldAdd = true;
            int[] handStrength = handStrength(hand);
            for (int j = 0; j < handStrength.length; j++) {
                if (handStrength[j] != bestHand[j]) {
                    shouldAdd = false;
                }
            }
            if (shouldAdd) {
                bestHands.add(hand);
            }
        }
        return bestHands;
    }


    //maybe sort here instead of when updating the total hands
    public List<Player> getBestPlayers(PlayerList playerList, boolean isVisibleHand) {
        List<Player> bestPlayers = new ArrayList<>();
        bestHandMapping.clear();
        for (Player player : playerList.getActivePlayers()){
            combiner.clearAllHands();
            Hand playerHand;
            if (isVisibleHand){
                playerHand = getBestHands(combiner.getAllHands(player.getTotalVisibleHand())).get(0);
            }
            else{
                playerHand = getBestHands(combiner.getAllHands(player.getTotalHand())).get(0);
            }
            bestHandMapping.put(playerHand, player);
        }
        List<Hand> bestHands = getBestHands (new ArrayList<>(bestHandMapping.keySet()));

        for (Card bestCard: bestHands.get(0).getCards()){
            System.out.print(bestCard.toString() + " " );
        }

        for (Hand bestHand : bestHands){
            bestPlayers.add(bestHandMapping.get(bestHand));
        }

        return bestPlayers;
    }

    public int[] makeNewArray(){return new int[POKERHANDSIZE];}

}
