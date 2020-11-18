package model;

import model.Card;
import model.Hand;

import java.util.ArrayList;

public class HandCombiner {

    private ArrayList<Hand> allHands;

    //only need total visible in stud
    public ArrayList<Hand> getAllHands(Hand hand){
        //update total hand?
        int handSize = 5;
        // A temporary array to store all combination one by one
        Card[] temporaryHand =new Card[handSize];
        int totalHandSize  = hand.getHandSize();
        clearAllHands();
        makeAllPossibleHands(hand, temporaryHand, 0, totalHandSize-1, 0);
        return allHands;
    }

    public void clearAllHands(){
        allHands = new ArrayList<>();
    }
        public void makeAllPossibleHands(Hand hand, Card[] temporaryHand, int start,
                                         int end, int currentIndex)
        {
            int handSize = 5;
            if (currentIndex == handSize)
            {
                Hand tempHand = new Hand();
                for (int temporaryHandIndex=0; temporaryHandIndex<handSize; temporaryHandIndex++){
                    tempHand.add(temporaryHand[temporaryHandIndex]);
                }
                allHands.add(tempHand);

            }

            // replace index with all possible elements. The condition
            // "end-totalHandIndex+1 >= handSize-currentIndex" makes sure that including one element
            // at index will make a combination with remaining elements
            // at remaining positions
            if(currentIndex<handSize){
                for (int totalHandIndex=start; totalHandIndex<=end && end-totalHandIndex+1 >= handSize-currentIndex; totalHandIndex++)
                {
                    temporaryHand[currentIndex] = hand.get(totalHandIndex);
                    makeAllPossibleHands(hand, temporaryHand, totalHandIndex+1, end, currentIndex+1);
                }
            }
        }
    }