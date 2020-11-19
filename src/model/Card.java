package model;


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


  @Override
  public String toString() {
    return (rank + " " + suit);
  }

  //get the associated value of rank


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

  public String getCardSuit() {
    return suit;
  }

  public int getRank() {
    return rank;
  }

  public boolean isInteractivePlayerCard() {
    return isInteractivePlayerCard;
  }

  public void setInteractivePlayerCard() {
    isInteractivePlayerCard = true;
  }

  public boolean isBackEndVisible() {
    return isBackEndVisible;
  }


  public void makeBackEndVisible() {
    isBackEndVisible = true;
  }
}
