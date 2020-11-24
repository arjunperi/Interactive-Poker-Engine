package view;

public class CommunityCardGrid extends CardGrid {
  private final double communityCardX;
  private final double communityCardY;


  public CommunityCardGrid(int pokerTableX, int pokerTableY) {
    super();
    communityCardX = pokerTableX - (this.getMinWidth() / 1.75);
    communityCardY = pokerTableY - (this.getMinHeight() / 2);
    this.setLayoutX(communityCardX);
    this.setLayoutY(communityCardY);
  }

  public double getCommunityCardX() {
    return communityCardX;
  }

  public double getCommunityCardY() {
    return communityCardY;
  }
}
