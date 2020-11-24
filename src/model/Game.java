package model;


import controller.JSONReader;


public class Game {

  private final RoundManager roundManager;
  private final Pot pot;
  private final Deck deck;
  private final Dealer dealer;
  private final CommunityCards communityCards;
  private final JSONReader reader;

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

    Player1("Physics", "/arya_roy_player.png"),
    player2("Big O", "/astrachan_player.png"),
    player3("Goat", "/coach_k_player.png"),
    player4("MATLAB", "/gustufson.png"),
    player5("DUKE", "/mascot_player.png"),
    player6("Prez", "/price_player.png"),
    player7("Slizzy Sue", "/sue_player.png"),
    player8("Bob", "/default-profile-pic.png");

    private final String name;
    private final String image;


    AutoPlayerNames(final String name, final String image) {
      this.name = name;
      this.image = image;
    }

    public String getName() {
      return name;
    }
    public String getImage() {
      return image;
    }
  }

}

