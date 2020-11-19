package model;


import controller.JSONReader;

import java.util.List;


public class Game {

  private RoundManager roundManager;
  private Pot pot;
  private Deck deck;
  private Dealer dealer;
  private CommunityCards communityCards;
  private List<String> suits;
  private List<String> ranks;
  private JSONReader reader;

  //TODO: Game should be constructed frpm Pot, List of Players, Deck, and Dealer (rather than having them be created here)
  public Game() {
    pot = new Pot();
    this.communityCards = new CommunityCards();

    reader = new JSONReader();
    reader.parse("/cardSettings.json");

    deck = new Deck(reader.getSuitNames(), reader.getRankValues());

    dealer = new Dealer(deck);
    roundManager = new RoundManager(pot);
  }

  public RoundManager getTurnManager() {
    return roundManager;
  }

  public Deck getDeck() {
    return deck;
  }

  public CommunityCards getCommunityCards() {
    return communityCards;
  }

  public Pot getPot() {
    return pot;
  }

  public Dealer getDealer() {
    return dealer;
  }


  public enum AutoPlayerNames {

    Player1("Johnny"),
    player2("Cindy"),
    player3("Jimmy"),
    player4("Daequan"),
    player5("Lebron"),
    player6("Metta World Peace"),
    player7("Michael Jordan"),
    player8("Dinna");

    private final String value;

    AutoPlayerNames(final String newValue) {
      value = newValue;
    }

    public String getValue() {
      return value;
    }
  }

}

