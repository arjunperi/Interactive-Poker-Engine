package view;

import javafx.geometry.Point2D;
import javafx.scene.layout.BorderPane;

public class PlayerView extends BorderPane {
  private CardGrid cardGrid;
  private PlayerInfoBox playerInfoBox;
  private Point2D position;


  public PlayerView(Point2D position, String name, int bankroll, String avatar) {
    super();

    cardGrid = new CardGrid();
    playerInfoBox = new PlayerInfoBox(name, bankroll, avatar);

    this.position = position;
    this.setCenter(cardGrid);
    this.setBottom(playerInfoBox);
  }
}
