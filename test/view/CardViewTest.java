package view;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class CardViewTest extends DukeApplicationTest {
  private Controller controller;
  private Stage stage;
  private CardView card;

  public void start(final Stage stage) throws Exception {
    controller = new Controller();
    this.stage = stage;
    this.stage.setScene(controller.setupScene());
    this.stage.show();
  }

  @BeforeEach
  void setUp() {
    card = new CardView("4", "/heart-suit.png", "/card-back.png", true);
  }


  @Test
  void getRankSymbol() {
    String expectedRankSymbol = "4";
    String actualRankSymbol = card.getRankSymbol();

    assertEquals(expectedRankSymbol, actualRankSymbol);
  }

  @Test
  void getSuitImage() {
    String expectedSuitImage = "/heart-suit.png";
    String actualSuitImage = card.getSuitImage();

    assertEquals(expectedSuitImage, actualSuitImage);

  }
}