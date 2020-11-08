package model;

import java.util.ArrayList;

public class HandEvaluator {

    public boolean isFlush(Hand hand) {
        Suit flushSuit = hand.get(0).getCardSuit();
        for (Card card : hand.getCards()) {
            if (card.getCardSuit() != flushSuit) {
                return false;
            }
        }
        return true;
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
        return true;
    }

    public boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public boolean isFourOfAKind(Hand hand) {
        if (rankCount(hand, hand.get(0).getRank()) == 4 || rankCount(hand, hand.get(1).getRank()) == 4) {
            return true;
        }
        return false;
    }


    public boolean isFullHouse(Hand hand) {
        if ((rankCount(hand, hand.get(0).getRank()) == 3 && rankCount(hand, hand.get(3).getRank()) == 2) || (rankCount(hand, hand.get(0).getRank()) == 2 && rankCount(hand, hand.get(2).getRank()) == 3)) {
            return true;
        }
        return false;
    }

    public boolean isThreeOfAKind(Hand hand) {
        if ((rankCount(hand, hand.get(0).getRank()) == 3) || (rankCount(hand, hand.get(1).getRank()) == 3) || (rankCount(hand, hand.get(2).getRank()) == 3)) {
            return true;
        }
        return false;
    }


    public boolean isTwoPair(Hand hand) {
        if ((rankCount(hand, hand.get(1).getRank()) == 2 && rankCount(hand, hand.get(3).getRank()) == 2)) {
            return true;
        }
        return false;
    }

    public boolean isPair(Hand hand) {
        for (Card card : hand.getCards()) {
            if ((rankCount(hand, card.getRank())) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isHighCard(Hand hand) {
        if (isStraight(hand) || isFlush(hand)) {
            return false;
        }
        for (Card card : hand.getCards()) {
            if ((rankCount(hand, card.getRank())) > 1) {
                return false;
            }
        }
        return true;
    }

    public int rankCount(Hand hand, int rank) {
        int rankCount = 0;
        for (Card card : hand.getCards()) {
            if (card.getRank() == rank) {
                rankCount++;
            }
        }
        return rankCount;
    }


    public int[] formatStraightFlush(Hand hand) {
        int[] orderedHand = new int[5];
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
        int[] orderedHand = new int[5];
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
        int[] orderedHand = new int[5];
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
        int[] orderedHand = new int[5];
        for (int index = 0; index < orderedHand.length; index++) {
            orderedHand[index] = hand.get(index).getRank();
        }
        return orderedHand;
    }

    public int[] formatStraight(Hand hand) {
        int[] orderedHand = new int[5];
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


    public int[] formatThreeOfAKind(Hand hand) {
        int[] orderedHand = new int[5];
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
        int[] orderedHand = new int[5];
        int index = 0;
        for (Card card : hand.getCards()) {
            if (rankCount(hand, card.getRank()) == 2) {
                orderedHand[index] = card.getRank();
                index++;
            } else if (rankCount(hand, card.getRank()) == 1) {
                orderedHand[4] = card.getRank();
            }
        }
        return orderedHand;
    }

    public int[] formatPair(Hand hand) {
        int[] orderedHand = new int[5];
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
        int[] orderedHand = new int[5];
        for (int index = 0; index < orderedHand.length; index++) {
            orderedHand[index] = hand.get(index).getRank();
        }
        return orderedHand;
    }


    public int[] handStrength(Hand hand) {
        int[] handRank = new int[6];
        if (isStraightFlush(hand)) {
            handRank[0] = 8;
            int[] formattedHand = formatStraightFlush(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFourOfAKind(hand)) {
            handRank[0] = 7;
            int[] formattedHand = formatFourOfAKind(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFullHouse(hand)) {
            handRank[0] = 6;
            int[] formattedHand = formatFullHouse(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isFlush(hand)) {
            handRank[0] = 5;
            int[] formattedHand = formatFlush(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isStraight(hand)) {
            handRank[0] = 4;
            int[] formattedHand = formatStraight(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isThreeOfAKind(hand)) {
            handRank[0] = 3;
            int[] formattedHand = formatThreeOfAKind(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isTwoPair(hand)) {
            handRank[0] = 2;
            int[] formattedHand = formatTwoPair(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isPair(hand)) {
            handRank[0] = 1;
            int[] formattedHand = formatPair(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        if (isHighCard(hand)) {
            handRank[0] = 0;
            int[] formattedHand = formatHighCard(hand);
            for (int index = 0; index < formattedHand.length; index++) {
                handRank[index + 1] = formattedHand[index];
            }
            return handRank;
        }
        return handRank;
    }

    public ArrayList<Hand> getBestHands(ArrayList<Hand> hands) {
        int[] bestHand = new int[6];
        boolean isSame;
        int index;
        ArrayList<Hand> bestHands = new ArrayList<>();
        for (Hand hand : hands) {
            index = 0;
            isSame = true;
            while (isSame && index < 5) {
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
}
