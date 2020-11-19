package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Table extends Group {

  private final int centerX;
  private final int centerY;
  private final int playerDistanceFromCenter;
  private final Map<Point2D, PlayerView> playerPositions;
  private final List<PlayerView> playerViews;
  private final Circle pokerTable;
  List<PlayerView> players;

  public Table(int centerX, int centerY, int radius, List<PlayerView> players) {
    super();
    pokerTable = new Circle(centerX, centerY, radius);
    this.getChildren().add(pokerTable);
    this.centerX = centerX;
    this.centerY = centerY;
    this.players = players;

    this.playerPositions = new HashMap<>();
    this.playerViews = new ArrayList<>();
    this.playerDistanceFromCenter = radius * 2;
    pokerTable.getStyleClass().add("pokerTable");
    calculatePlayerPositions();
  }

  private void calculatePlayerPositions() {
    for (int player = 0; player < players.size(); player++) {
      double angle = 2 * player * Math.PI / players.size();
      double horizontalOffset = playerDistanceFromCenter * Math.cos(angle);
      double verticalOffset = playerDistanceFromCenter * Math.sin(angle);
      double playerX = centerX + horizontalOffset;
      double playerY = centerY + verticalOffset;

      Point2D position = new Point2D(playerX, playerY);
      players.get(player).setPosition(position);

      playerPositions.put(position, players.get(player));
    }
  }



  public int getCenterX() {
    return centerX;
  }

  public int getCenterY() {
    return centerY;
  }

}
