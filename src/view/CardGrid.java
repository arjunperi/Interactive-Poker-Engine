package view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class CardGrid extends GridPane {

  private static final int MAX_NUMBER_OF_ROWS = 2;
  private static final int MAX_NUMBER_OF_COLUMNS = 5;

  private static final int CARD_GRID_MIN_WIDTH = 200;
  private static final int CARD_GRID_MIN_HEIGHT = CardView.CARD_HEIGHT * 2 + 10;

  private int currentRow;
  private int currentColumn;


  private Set<CardView> selectedCards;

  private final Map<CardView, Point2D> cardLocations;

  public CardGrid() {
    super();

    cardLocations = new HashMap<>();
    selectedCards = new HashSet<>();

    initializeCardAddingPosition();
    initializeCardHolders();
    initializeProperties();
  }


  private void initializeProperties() {
    this.setMinSize(CARD_GRID_MIN_WIDTH, CARD_GRID_MIN_HEIGHT);
    this.getStyleClass().add("cardGrid");

  }

  private void initializeCardHolders() {
    for (int row = 0; row < MAX_NUMBER_OF_ROWS; row++) {
      for (int column = 0; column < MAX_NUMBER_OF_COLUMNS; column++) {
        Rectangle card = new Rectangle(CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
        card.getStyleClass().add("cardGridHolder");
        this.add(card, column, row);
      }
    }
  }

  private void initializeCardAddingPosition() {
    currentRow = MAX_NUMBER_OF_ROWS - 1;
    currentColumn = 0;
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

    card.setOnMouseClicked(event -> card.toggleCardSelected());
    //card.setOnMouseClicked(event2 -> countNumberOfCardsHighlighted());
  }


  private void checkCardsSelected() {
    selectedCards = new HashSet<>();
    for (CardView card : cardLocations.keySet()) {
      if (card.getIsSelected()) {
        selectedCards.add(card);
      }
    }
    System.out.println(selectedCards.size());
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
    for (CardView card : cardLocations.keySet()) {
      this.getChildren().remove(card);
    }
    cardLocations.clear();
    initializeCardAddingPosition();
  }

  public void flipCards() {
    for (CardView card : cardLocations.keySet()) {
      card.setFrontEndVisible(true);
    }
  }

  public Set<CardView> getSelectedCards() {
    checkCardsSelected();
    return selectedCards;
  }

}
