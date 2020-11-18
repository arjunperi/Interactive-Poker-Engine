package model;

public class InteractivePlayer extends Player {

  public InteractivePlayer(String name, int startingAmount, CommunityCards communityCards,
      Pot pot) {
    super(name, startingAmount, communityCards, pot);
    isInteractive = true;

  }

}
