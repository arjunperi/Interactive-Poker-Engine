package view;

public class CommunityCardGrid extends CardGrid {

  public CommunityCardGrid(int pokerTableX, int pokerTableY) {
    super();
    this.setLayoutX(pokerTableX - (this.getMinWidth() / 2));
    this.setLayoutY(pokerTableY - (this.getMinHeight() / 2));
  }

}
