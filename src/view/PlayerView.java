package view;

import javafx.geometry.Point2D;
import javafx.scene.layout.BorderPane;

public class PlayerView extends BorderPane {
  private CardGrid cardGrid;
  private PlayerInfoBox playerInfoBox;
  private Point2D position;


  public PlayerView(Point2D position) {
    super();
    cardGrid = new CardGrid();
    playerInfoBox = new PlayerInfoBox();

    this.position = position;
    this.setCenter(cardGrid);
    this.setBottom(playerInfoBox);
  }
}
