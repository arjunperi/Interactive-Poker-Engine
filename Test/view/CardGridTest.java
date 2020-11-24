package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.Controller;
import java.util.HashMap;
import java.util.Map;
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
    assertEquals(new Point2D(0, 1), cardGrid.removeCard(cardView));
  }

  @Test
  void getCardLocations() {
    cardView = new CardView("4", "/heart-suit.png", "/card-back.png", true);
    cardGrid.addCardView(cardView);
    Map<CardView, Point2D> expectedCardLocations = new HashMap<>();
    expectedCardLocations.put(cardView, new Point2D(0, 1));
    assertEquals(expectedCardLocations, cardGrid.getCardLocations());
  }

  @Test
  void clearCardGrid() {
    cardView = new CardView("4", "/heart-suit.png", "/card-back.png", true);
    cardGrid.addCardView(cardView);
    cardGrid.clearCardGrid();
    assertEquals(0, cardGrid.getCardLocations().size());
  }

  @Test
  void flipCards() {
    cardView = new CardView("4", "/heart-suit.png", "/card-back.png", false);
    cardGrid.addCardView(cardView);
    cardGrid.flipCards();
    assertTrue(cardView.getIsFrontEndVisible());
  }
}