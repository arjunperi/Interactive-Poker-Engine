package view;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardView extends StackPane {
  private String rank;
  private boolean isFrontEndVisible;
  private ImageView displayedImage;
  private String suitImage;
  private String cardBack;

  private static final int CARD_WIDTH = 35;
  private static final int CARD_HEIGHT = 50;

  public CardView(String rank, String suitImage, String cardBack, boolean isFrontEndVisible) {
    super();

    this.rank = rank;
    this.isFrontEndVisible = isFrontEndVisible;
    this.suitImage = suitImage;
    this.cardBack = cardBack;
    this.displayedImage = new ImageView();



    setUpCard();
    checkVisibility();


  }

  private void setUpCard() {
    this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
  }

  public void setFrontEndVisible(boolean isFrontEndVisible) {
    this.isFrontEndVisible = isFrontEndVisible;
    checkVisibility();
  }

  private void checkVisibility() {
    this.getChildren().clear();
    if (isFrontEndVisible) {
      makeCardViewVisible();
    } else {
      makeCardViewInvisible();
    }
  }

  private void makeCardViewInvisible() {
    setCardImage(cardBack);
    this.getChildren().addAll(displayedImage);
  }

  private void makeCardViewVisible() {
    setCardImage(suitImage);
    Rectangle card = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
    Text suitName = new Text(rank);
    card.setStroke(Color.RED);
    card.setFill(Color.TRANSPARENT);
  }

  private void setCardImage(String suitImage) {
    try {
      displayedImage.setImage(new Image(CardView.class.getResource(suitImage).toExternalForm()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
