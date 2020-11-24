package model;

import java.util.List;

/**
 * Represents the dealer, who deals and exchanges cards to players and deals community cards
 */
public class Dealer {

  public Deck deck;

  public Dealer(Deck deck) {
    this.deck = deck;
  }


  /**
   * Deals cards to the CardRecipient passed in.
   * @param recipient The recipient to be dealt cards
   * @param visibilityList
   */
  public void dealCards(CardRecipient recipient, List<Boolean> visibilityList) {
    recipient.clearNewCards();
    for (boolean isVisible : visibilityList) {
      Card cardDealt = deck.getTopCard();
      if (isVisible) {
        cardDealt.makeBackEndVisible();
      }
      recipient.receiveCard(cardDealt);
    }
  }

  /**
   * Exchanges the cards passed in from the player passed in and deals back the same number of cards from the deck.
   * @param player Player to exchange cards from/to
   * @param exchangeCards Cards to be discarded from the player
   */
  public void exchangeCards(Player player, List<Card> exchangeCards) {
    player.clearDiscardedCards();
    player.clearNewCards();
    for (Card exchangeCard : exchangeCards) {
      player.discardCard(exchangeCard);
      Card cardDealt = deck.getTopCard();
      player.receiveCard(cardDealt);
    }
  }

}
