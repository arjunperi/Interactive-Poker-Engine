package model;

import java.util.ArrayList;

public class HandCombiner {

    private ArrayList<Hand> allHands;

    //only need total visible in stud
    public ArrayList<Hand> getAllHands(Hand hand){
        //update total hand?
        int r = 5;
        // A temporary array to store all combination one by one
        Card data[]=new Card[r];
        int n  = hand.getHandSize();
        clearAllHands();
        makeAllPossibleHands(hand, data, 0, n-1, 0);
        return allHands;
    }

    public void clearAllHands(){
        allHands = new ArrayList<>();
    }
        public void makeAllPossibleHands(Hand hand, Card[] data, int start,
                                         int end, int index)
        {
            // Current combination is ready to be printed, print it
            int r = 5;
            if (index == r)
            {
                Hand tempHand = new Hand();
                for (int j=0; j<r; j++){
                    tempHand.add(data[j]);
                }
                allHands.add(tempHand);

            }

            // replace index with all possible elements. The condition
            // "end-i+1 >= r-index" makes sure that including one element
            // at index will make a combination with remaining elements
            // at remaining positions
            if(index<r){
                for (int i=start; i<=end && end-i+1 >= r-index; i++)
                {
                    data[index] = hand.get(i);
                    makeAllPossibleHands(hand, data, i+1, end, index+1);
                }
            }
        }

        // The main function that prints all combinations of size r
        // in arr[] of size n. This function mainly uses combinationUtil()
        public  void printCombination(Hand hand, int n)
        {
            int r = 5;
            // A temporary array to store all combination one by one
            Card data[]=new Card[r];

            // Print all combination using temprary array 'data[]'
            makeAllPossibleHands(hand, data, 0, n-1, 0);
        }
    }