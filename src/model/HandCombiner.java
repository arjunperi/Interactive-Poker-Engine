package model;

import java.util.ArrayList;

public class HandCombiner {

    private ArrayList<Hand> allHands;

    public ArrayList<Hand> getAllHands(){
        return allHands;
    }

    public void clearAllHands(){
        allHands = new ArrayList<>();
    }
        public void combinationUtil(Hand hand, Card[] data, int start,
                                    int end, int index, ArrayList<Hand> ret)
        {
            // Current combination is ready to be printed, print it



            int r = 5;
            if (index == r)
            {
                Hand tempHand = new Hand();
                for (int j=0; j<r; j++){
                    tempHand.add(data[j]);
                }
                ret.add(tempHand);

            }

            // replace index with all possible elements. The condition
            // "end-i+1 >= r-index" makes sure that including one element
            // at index will make a combination with remaining elements
            // at remaining positions
            if(index<r){
                for (int i=start; i<=end && end-i+1 >= r-index; i++)
                {
                    data[index] = hand.get(i);
                    combinationUtil(hand, data, i+1, end, index+1,ret);
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
            combinationUtil(hand, data, 0, n-1, 0,allHands);
        }

        /*Driver function to check for above function*/
        public static void main (String[] args) {

            Card card1 = new Card(8,Suit.CLUBS);
            Card card2 = new Card(9,Suit.CLUBS);
            Card card3 = new Card(2,Suit.CLUBS);
            Card card4 = new Card(4,Suit.CLUBS);
            Card card5 = new Card(10,Suit.CLUBS);
            Card card6 = new Card(5,Suit.CLUBS);
            Card card7 = new Card(14,Suit.CLUBS);
            Card card8 = new Card(14,Suit.CLUBS);
            Hand hand = new Hand();
            hand.add(card1);
            hand.add(card2);
            hand.add(card3);
            hand.add(card4);
            hand.add(card5);
            hand.add(card6);
            hand.add(card7);
            hand.add(card8);
            hand = hand.sortHand();

            int n = hand.getHandSize();






        }
    }