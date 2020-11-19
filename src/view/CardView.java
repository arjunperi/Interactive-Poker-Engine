/*
package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {

  private final String rankSymbol;
  private boolean isFrontEndVisible;
  private ImageView displayedImage;
  private final String suitImage;
  private final String cardBack;
  private boolean isSelected;

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

  //TODO: Use CSS instead
  public void toggleCardSelected() {
    isSelected = !isSelected;
    this.setStyle("");
    if (isSelected) {
      this.setStyle("-fx-border-color: black; -fx-border-width: 2px");
    }
  }

  private void setUpCard() {
    rank = new Text(rankSymbol);
    rank.setStroke(Color.WHITE);

    cardBackground = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
    cardBackground.setStroke(Color.RED);
    cardBackground.setFill(Color.TRANSPARENT);

    displayedImage = new ImageView();
    displayedImage.setFitHeight(CARD_HEIGHT);
    displayedImage.setFitWidth(CARD_WIDTH);

    isSelected = false;

    //this.setOnMouseClicked(event -> toggleCardSelected());
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

  public boolean getIsSelected() {
    return isSelected;
  }


  public String getRankSymbol() {
    return rankSymbol;
  }

  public String getSuitImage() {
    return suitImage;
  }
}
*/


package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends PlayerHUDGameObject {

  private final String rankSymbol;
  private boolean isFrontEndVisible;
  private ImageView displayedImage;
  private final String suitImage;
  private final String cardBack;
  private boolean isSelected;

  private Text rank;

  public static final int CARD_WIDTH = 35;
  public static final int CARD_HEIGHT = 50;

  public CardView(String rankSymbol, String suitImage, String cardBack, boolean isFrontEndVisible) {
    super(rankSymbol, suitImage);

    this.rankSymbol = rankSymbol;
    this.suitImage = suitImage;
    this.cardBack = cardBack;

    setUpCard();
    setFrontEndVisible(isFrontEndVisible);
  }

  //TODO: Use CSS instead
  public void toggleCardSelected() {
    isSelected = !isSelected;
    this.setStyle("");
    if (isSelected) {
      this.setStyle("-fx-border-color: black; -fx-border-width: 2px");
    }
  }

  private void setUpCard() {
    rank = new Text(rankSymbol);
    rank.setStroke(Color.WHITE);

    displayedImage = new ImageView();
    displayedImage.setFitHeight(CARD_HEIGHT);
    displayedImage.setFitWidth(CARD_WIDTH);

    isSelected = false;

    //this.setOnMouseClicked(event -> toggleCardSelected());
    //this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    //this.getChildren().addAll(cardBackground, displayedImage, rank);
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
    setImage(cardImage);
    getGameStat().setVisible(isFrontEndVisible);
  }

  public boolean getIsSelected() {
    return isSelected;
  }


  public String getRankSymbol() {
    return rankSymbol;
  }

  public String getSuitImage() {
    return suitImage;
  }
}

