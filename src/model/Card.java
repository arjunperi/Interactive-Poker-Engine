package model;

/**
 * This class represents a card object within a poker game
 */
public class Card {

  private final String suit;
  private final int rank;
  private boolean isBackEndVisible;
  private boolean isInteractivePlayerCard;


  public Card(int rank, String suit) {
    this.suit = suit;
    this.rank = rank;
    isBackEndVisible = false;
    isInteractivePlayerCard = false;
  }

  /**
   * Returns the rank and suit of the card in the format "Rank Suit"
   * @return rank and suit as String
   */
  @Override
  public String toString() {
    return (rank + " " + suit);
  }

  /**
   * Determines if the object passed in is equal to the card object calling the method by first checking
   * memory location, then checking that it is not null or of class other than Card. If none of these checks are hit,
   * it checks that the rank and suits of the cards match and returns true if so.
   * @param obj object to be compared to card calling the method.
   * @return Boolean true, if the objects are equal, false if not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Card otherCard = (Card) obj;
    return rank == otherCard.rank && suit.equals(otherCard.suit);
  }

  /**
   * Returns the suit of card
   * @return String suit
   */
  public String getCardSuit() {
    return suit;
  }

  /**
   * Returns the rank of the card
   * @return int rank
   */
  public int getRank() {
    return rank;
  }

  /**
   * If the card is held by an interactive player, returns true
   * @return boolean
   */
  public boolean isInteractivePlayerCard() {
    return isInteractivePlayerCard;
  }

  /**
   * sets the card to indicate that it is held by an interactive player
   */
  public void setInteractivePlayerCard() {
    isInteractivePlayerCard = true;
  }

  /**
   * For Stud Poker games, returns true if the card is visible and false otherwise
   * @return boolean
   */
  public boolean isBackEndVisible() {
    return isBackEndVisible;
  }

  /**
   * sets the card to indicate that it is visible in a Stud Game
   */
  public void makeBackEndVisible() {
    isBackEndVisible = true;
  }
}
