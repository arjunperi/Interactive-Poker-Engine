package utility;

import model.Card;
import model.Hand;

import java.util.ArrayList;
import java.util.List;

public final class HandCombiner {


  //only need total visible in stud
  public static List<Hand> getAllHands(Hand hand) {
    List<Hand> allHands = new ArrayList<>();//update total hand?
    int handSize = 5;
    // A temporary array to store all combination one by one
    Card[] temporaryHand = new Card[handSize];
    int totalHandSize = hand.getHandSize();
    allHands = makeAllPossibleHands(allHands, hand, temporaryHand, 0, totalHandSize - 1, 0);
    return allHands;
  }


  private static List<Hand> makeAllPossibleHands(List<Hand> allHands, Hand hand,
      Card[] temporaryHand, int start,
      int end, int currentIndex) {
    int handSize = 5;
    if (currentIndex == handSize) {
      Hand tempHand = new Hand();
      for (int temporaryHandIndex = 0; temporaryHandIndex < handSize; temporaryHandIndex++) {
        tempHand.add(temporaryHand[temporaryHandIndex]);
      }
      allHands.add(tempHand);

    }

    // replace index with all possible elements. The condition
    // "end-totalHandIndex+1 >= handSize-currentIndex" makes sure that including one element
    // at index will make a combination with remaining elements
    // at remaining positions
    if (currentIndex < handSize) {
      for (int totalHandIndex = start;
          totalHandIndex <= end && end - totalHandIndex + 1 >= handSize - currentIndex;
          totalHandIndex++) {
        temporaryHand[currentIndex] = hand.get(totalHandIndex);
        makeAllPossibleHands(allHands, hand, temporaryHand, totalHandIndex + 1, end,
            currentIndex + 1);
      }
    }
    return allHands;
  }
}
