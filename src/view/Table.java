package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Table extends Circle {
  private int centerX;
  private int centerY;
  private int numPlayers;
  private int playerDistanceFromCenter;
  private Map<Point2D, PlayerView> playerPositions;
  private List<PlayerView> playerViews;

  public Table (int centerX, int centerY, int radius, int numPlayers) {
    super (centerX, centerY, radius);
    this.centerX = centerX;
    this.centerY = centerY;
    this.numPlayers = numPlayers;

    this.playerPositions = new HashMap<>();
    this.playerViews = new ArrayList<>();
    this.playerDistanceFromCenter = radius * 2;
    this.setStyle("-fx-fill: darkgreen");
    calculatePlayerPositions();
  }

  private void calculatePlayerPositions() {
    for (int player = 0; player < numPlayers; player++) {
      double angle = 2 * player * Math.PI / numPlayers;
      double horizontalOffset = playerDistanceFromCenter * Math.cos(angle);
      double verticalOffset = playerDistanceFromCenter * Math.sin(angle);
      double playerX = centerX + horizontalOffset;
      double playerY = centerY + verticalOffset;

      Point2D location = new Point2D(playerX, playerY);
      PlayerView playerView = new PlayerView(location, "yuh", 100, "/default-profile-pic.png");
      playerPositions.put(location, playerView);
      playerViews.add(playerView);
    }
  }

  public Map<Point2D, PlayerView> getPlayerPositions() {
    return playerPositions;
  }

  public List<PlayerView> getPlayerViews() {
    return playerViews;
  }


}
