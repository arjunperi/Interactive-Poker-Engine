package utility;

import model.Card;
import model.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for making five card hand combinations
 * of hands that are five or more cards large
 */
public final class HandCombiner {

  /**
   * This method returns a list of all possible five card hand combinations
   * of hands that consist of five or more cards
   * @param hand - A hand that consists of all the cards a player has access to.
   * @return - A list of all the five card hands that are possible from the total
   * hand of a player
   */
  public static List<Hand> getAllHands(Hand hand) {
    List<Hand> allHands = new ArrayList<>();
    int handSize = 5;
    Card[] temporaryHand = new Card[handSize];
    int totalHandSize = hand.getHandSize();
    makeAllPossibleHands(allHands, hand, temporaryHand, 0, totalHandSize - 1, 0);
    return allHands;
  }


  private static void makeAllPossibleHands(List<Hand> allHands, Hand hand,
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

    if (currentIndex < handSize) {
      for (int totalHandIndex = start;
          totalHandIndex <= end && end - totalHandIndex + 1 >= handSize - currentIndex;
          totalHandIndex++) {
        temporaryHand[currentIndex] = hand.get(totalHandIndex);
        makeAllPossibleHands(allHands, hand, temporaryHand, totalHandIndex + 1, end,
            currentIndex + 1);
      }
    }
  }
}
