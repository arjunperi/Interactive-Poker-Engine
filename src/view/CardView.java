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

  private static final int CARD_WIDTH = 35;
  private static final int CARD_HEIGHT = 50;

  public CardView(String rankSymbol, String suitImage, String cardBack, boolean isFrontEndVisible) {
    super();

    this.rankSymbol = rankSymbol;
    this.isFrontEndVisible = isFrontEndVisible;
    this.suitImage = suitImage;
    this.cardBack = cardBack;

    setUpCard();
    checkVisibility();
  }

  private void setUpCard() {
    rank = new Text(rankSymbol);
    cardBackground = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
    cardBackground.setStroke(Color.RED);
    cardBackground.setFill(Color.TRANSPARENT);
    displayedImage = new ImageView();

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

  private void setVisibility(String cardBack, boolean b) {
    setCardImage(cardBack);
    cardBackground.setVisible(b);
    rank.setVisible(b);
  }


  private void setCardImage(String suitImage) {
    try {
      displayedImage.setImage(new Image(CardView.class.getResource(suitImage).toExternalForm()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
