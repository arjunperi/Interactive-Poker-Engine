package model;

/**
 * Represents the active, human player that is playing the game
 */
public class InteractivePlayer extends Player {

  public InteractivePlayer(String name, int startingAmount, CommunityCards communityCards,
      Pot pot) {
    super(name, startingAmount, communityCards, pot);
    isInteractive = true;

  }

}
