package model;

import java.util.List;

public class Dealer {

  public Deck deck;

  public Dealer(Deck deck) {
    this.deck = deck;
  }

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
