package model;


import controller.JSONReader;

/**
 *
 */
public class Game {

  private final RoundManager roundManager;
  private final Pot pot;
  private final Deck deck;
  private final Dealer dealer;
  private final CommunityCards communityCards;


  public Game() {
    pot = new Pot();
    this.communityCards = new CommunityCards();

    JSONReader reader = new JSONReader();
    reader.parse("/cardSettings.json");

    deck = new Deck(reader.getSuitNames(), reader.getRankValues());

    dealer = new Dealer(deck);
    roundManager = new RoundManager(pot);
  }

  /**
   * Returns the roundManager manager within the current game
   * @return RoundManager
   */
  public RoundManager getTurnManager() {
    return roundManager;
  }

  /**
   * Returns the deck within the current game
   * @return Deck object
   */
  public Deck getDeck() {
    return deck;
  }

  /**
   * Returns the community cards within the current game
   * @return CommunityCards object
   */
  public CommunityCards getCommunityCards() {
    return communityCards;
  }

  /**
   * Returns the pot within the current game
   * @return Pot object
   */
  public Pot getPot() {
    return pot;
  }

  /**
   * Returns the dealer within the current game
   * @return Dealer
   */
  public Dealer getDealer() {
    return dealer;
  }


  /**
   *
   */
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

