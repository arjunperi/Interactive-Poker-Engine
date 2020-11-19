package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Controller;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class CardGridTest extends DukeApplicationTest {

  private CardGrid cardGrid;
  private CardView cardView;

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
    cardGrid = new CardGrid();

  }

  @Test
  void addCardView() {
    cardView = new CardView("4", "/heart-suit.png", "/card-back.png", true);
    cardGrid.addCardView(cardView);
    assertEquals(new Point2D(0, 1), cardGrid.getCardLocations().get(cardView));
  }

  @Test
  void addCardViewToLocation() {
  }

  @Test
  void removeCard() {
    cardView = new CardView("4", "/heart-suit.png", "/card-back.png", true);
    cardGrid.addCardView(cardView);
    cardGrid.removeCard(cardView);
    assertEquals(new Point2D(1, 1), cardGrid.removeCard(cardView));
  }

  @Test
  void getCardLocations() {
  }

  @Test
  void clearCardGrid() {
  }

  @Test
  void flipCards() {
  }

  @Test
  void getSelectedCards() {
  }
}