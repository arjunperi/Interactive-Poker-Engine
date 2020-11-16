package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {


  private String rankSymbol;
  private boolean isFrontEndVisible;
  private ImageView displayedImage;
  private String suitImage;
  private String cardBack;

  private Text rank;
  private Rectangle cardBackground;

  public static final int CARD_WIDTH = 35;
  public static final int CARD_HEIGHT = 50;

  public CardView(String rankSymbol, String suitImage, String cardBack, boolean isFrontEndVisible) {
    super();

    this.rankSymbol = rankSymbol;
    this.suitImage = suitImage;
    this.cardBack = cardBack;

    setUpCard();
    setFrontEndVisible(isFrontEndVisible);
  }

  private void setUpCard() {
    rank = new Text(rankSymbol);

    cardBackground = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
    cardBackground.setStroke(Color.RED);
    cardBackground.setFill(Color.TRANSPARENT);

    displayedImage = new ImageView();
    displayedImage.setFitHeight(CARD_HEIGHT);
    displayedImage.setFitWidth(CARD_WIDTH);

    this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    this.getChildren().addAll(cardBackground, displayedImage, rank);
  }

  public void setFrontEndVisible(boolean isFrontEndVisible) {
    this.isFrontEndVisible = isFrontEndVisible;
    checkVisibility();
  }

  private void checkVisibility() {
    if (isFrontEndVisible) {
      setVisibility(suitImage, true);
    } else {
      setVisibility(cardBack, false);
    }
  }

  private void setVisibility(String cardImage, boolean isFrontEndVisible) {
    setCardImage(cardImage);
    cardBackground.setVisible(isFrontEndVisible);
    rank.setVisible(isFrontEndVisible);
  }


  private void setCardImage(String suitImage) {
    try {
      displayedImage.setImage(new Image(CardView.class.getResource(suitImage).toExternalForm()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
