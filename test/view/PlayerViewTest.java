package view;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class PlayerViewTest extends DukeApplicationTest {
  private PlayerView playerView;

  private Controller controller;
  private Stage stage;

  public void start(final Stage stage) throws Exception {
    controller = new Controller();
    this.stage = stage;
    this.stage.setScene(controller.setupScene());
    this.stage.show();
  }

  @BeforeEach
  void setUp() {
    playerView = new PlayerView("Yasser",
        1000, "/default-profile-pic.png");
  }

  @Test
  void getCardGrid() {

    CardView cardView = new CardView("4", "/heart-suit.png", "/card-back.png", true);
    playerView.getCardGrid().addCardView(cardView);

    int expectedPlayerViewCardGridSize = 1;
    int actualPlayerViewCardGridSize = playerView.getCardGrid().getCardLocations().size();

    assertEquals(expectedPlayerViewCardGridSize, actualPlayerViewCardGridSize);
  }

  @Test
  void getPlayerInfoBox() {
    String expectedPlayerInfoBoxName = "Yasser";
    String actualPlayerInfoBoxName = playerView.getPlayerInfoBox().getPlayerName().getText();

    assertEquals(expectedPlayerInfoBoxName, actualPlayerInfoBoxName);
  }

  @Test
  void setPosition() {
    playerView.setPosition(new Point2D(300, 300));
    Point2D expectedPosition = new Point2D(200, 230);
    Point2D actualPosition = new Point2D(playerView.getLayoutX(), playerView.getLayoutY());

    assertEquals(expectedPosition, actualPosition);
  }
}