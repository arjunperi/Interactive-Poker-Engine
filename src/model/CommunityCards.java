package model;

import java.util.List;

/**
 * Represents the community cards in a hold'em poker game
 */
public class CommunityCards extends CardRecipient {


  public CommunityCards() {
    super();
  }

  void receiveCard(Card card) {
    cardsList.add(card);
    addNewCards(card);
  }

  /**
   * Returns the list of community cards currently in the game
   * @return List of card objects
   */
  public List<Card> getCommunityCardsList() {
    return cardsList;
  }

}
