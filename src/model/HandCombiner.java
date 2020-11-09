package model;

import java.util.ArrayList;

public class HandCombiner {

    private ArrayList<Hand> allHands;

    public ArrayList<Hand> getAllHands() {
        return allHands;
    }

    public void clearAllHands() {
        allHands = new ArrayList<>();
    }

    public void makeAllPossibleHands(Hand hand, Card[] data, int start,
                                int end, int index, ArrayList<Hand> ret) {
        // Current combination is ready to be printed, print it


        int handSize= 5;
        if (index == handSize) {
            Hand tempHand = new Hand();
            for (int j = 0; j < handSize; j++) {
                tempHand.add(data[j]);
            }
            ret.add(tempHand);

        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        if (index < handSize) {
            for (int i = start; i <= end && end - i + 1 >= handSize - index; i++) {
                data[index] = hand.get(i);
                makeAllPossibleHands(hand, data, i + 1, end, index + 1, ret);
            }
        }
    }
}