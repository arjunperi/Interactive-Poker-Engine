package view;

import javafx.geometry.Point2D;
import javafx.scene.layout.BorderPane;

public class PlayerView extends BorderPane {

  private final CardGrid cardGrid;
  private final PlayerInfoBox playerInfoBox;


  public PlayerView(String name, int bankroll, String avatar) {
    super();

    cardGrid = new CardGrid();
    playerInfoBox = new PlayerInfoBox(name, bankroll, avatar);

    this.setCenter(cardGrid);
    this.setBottom(playerInfoBox);

  }

  public CardGrid getCardGrid() {
    return cardGrid;
  }

  public PlayerInfoBox getPlayerInfoBox() {
    return playerInfoBox;
  }

  public void setPosition(Point2D position) {
    this.setLayoutX(position.getX() - (playerInfoBox.getMinWidth() / 2));
    this.setLayoutY(
        position.getY() - ((playerInfoBox.getMinHeight() + cardGrid.getMinHeight()) / 2));
  }
}
