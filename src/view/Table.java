package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Table extends Circle {
  private int centerX;
  private int centerY;
  private int radius;
  private int numPlayers;
  private List<Point2D> playerPositions;
  private int playerDistanceFromCenter;
  //private Map<PlayerView, Point2D> playerPositions;

  public Table (int centerX, int centerY, int radius, int numPlayers) {
    super (centerX, centerY, radius);
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = radius;
    this.numPlayers = numPlayers;

    this.playerPositions = new ArrayList<>();
    this.playerDistanceFromCenter = radius * 2;
    calculatePlayerPositions();
  }

  private void calculatePlayerPositions() {
    for (int player = 0; player < numPlayers; player++) {
      double angle = 2 * player * Math.PI / numPlayers;
      double horizontalOffset = playerDistanceFromCenter * Math.cos(angle);
      double verticalOffset = playerDistanceFromCenter * Math.sin(angle);
      double playerX = centerX + horizontalOffset;
      double playerY = centerY + verticalOffset;
      playerPositions.add(new Point2D(playerX, playerY));
    }
  }

  public List<Point2D> getPlayerPositions() {
    return playerPositions;
  }


}
