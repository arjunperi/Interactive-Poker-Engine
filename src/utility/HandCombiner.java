// This class is responsible for making every five card hand combination for a hand that consists of five or more cards. 
//This is necessary because there are often times where a player's hand will consist of more than five cards. 
//However, poker is a five card game, meaning the hand that the player uses to determine who has won a game of poker consists of that player's best five cards.
//In order to determine the best five cards, all of the possible five card hands need to be created and compared to.
//The hand evaluator class uses this class to dermine which of all the possible combinations is the best hand.
//I believe this class is good design because it adheres strictly to the Single Responsibilty Principle.
//The class's job is to perform a simple, yet incredibly important task that the rest of the game is dictated by.
//The class is also efficient in achieving this task with only two methods, one of which is private.
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

//This method uses recursion to assist in the process of making all possible five card hand variations from a hand of five cards or more
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
