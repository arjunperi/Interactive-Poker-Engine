package view;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardGrid extends GridPane {
  private static final int MAX_NUMBER_OF_ROWS = 2;
  private static final int MAX_NUMBER_OF_COLUMNS = 5;

  private static final int CARD_GRID_MIN_WIDTH = 200;
  private static final int CARD_GRID_MIN_HEIGHT = CardView.CARD_HEIGHT * 2 + 10;

  private int currentRow;
  private int currentColumn;

  private int numberOfCardsHighlighted;

  private Map<CardView, Point2D> cardLocations;

  public CardGrid() {
    super();

    cardLocations = new HashMap<>();
    numberOfCardsHighlighted = 0;

    initializeCardAddingPosition();
    initializeCardHolders();
    initializeProperties();
  }


  //TODO: Add to CSS File
  private void initializeProperties() {
    /*this.setStyle("-fx-vgap: 5");
    this.setStyle("-fx-hgap: 5");
    this.setStyle("-fx-padding: 5, 0, 5, 0");
    this.setGridLinesVisible(true);*/
    this.setVgap(5);
    this.setHgap(5);
    this.setPadding(new Insets(5, 0, 5, 0));
    this.setMinSize(CARD_GRID_MIN_WIDTH, CARD_GRID_MIN_HEIGHT);

  }

  private void initializeCardHolders() {
    for (int row = 0; row < MAX_NUMBER_OF_ROWS; row++) {
      for (int column = 0; column < MAX_NUMBER_OF_COLUMNS; column++) {
        Rectangle card = new Rectangle(CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
        card.setStroke(Color.TRANSPARENT);
        card.setFill(Color.TRANSPARENT);
        this.add(card, column, row);
      }
    }
  }

  private void initializeCardAddingPosition() {
    currentRow = MAX_NUMBER_OF_ROWS - 1;
    currentColumn = 0;
  }

  public void countNumberOfCardsHighlighted() {
    numberOfCardsHighlighted = 0;
    for (CardView card: cardLocations.keySet()) {
      if (card.getIsSelected()) {
        numberOfCardsHighlighted++;
      }
    }
    System.out.println(numberOfCardsHighlighted);
  }

  public void addCardView(CardView card) {
    addCardViewToLocation(card, new Point2D(currentColumn, currentRow));
  }

  public void addCardViewToLocation(CardView card, Point2D location) {
    checkGridConstraints();
    int column = (int) location.getX();
    int row = (int) location.getY();

    if (column == MAX_NUMBER_OF_COLUMNS) {
      column = 0;
      row--;
    }

    this.add(card, column, row);
    cardLocations.put(card, new Point2D(column, row));

    if (column == currentColumn) {
      currentColumn++;
    }

    card.setOnMouseClicked(event -> {card.toggleCardSelected(); countNumberOfCardsHighlighted();});
    //card.setOnMouseClicked(event2 -> countNumberOfCardsHighlighted());
  }

  private void checkGridConstraints() {
    if (currentColumn == MAX_NUMBER_OF_COLUMNS) {
      currentColumn = 0;
      currentRow--;
    }
  }

  public Point2D removeCard(CardView card) {
    if (cardLocations.containsKey(card)) {
      this.getChildren().remove(card);
      return cardLocations.remove(card);
    }
    return new Point2D(currentColumn, currentRow);
  }

  public Map<CardView, Point2D> getCardLocations() {
    return cardLocations;
  }

  public void clearCardGrid() {
    for (CardView card: cardLocations.keySet()) {
      this.getChildren().remove(card);
    }
    initializeCardAddingPosition();
  }

  public void flipCards() {
    for (CardView card: cardLocations.keySet()) {
      card.setFrontEndVisible(true);
    }
  }

}
