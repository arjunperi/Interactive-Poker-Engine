package view;

public class CardView extends PlayerHUDGameObject {

  private final String rankSymbol;
  private boolean isFrontEndVisible;
  private final String suitImage;
  private final String cardBack;
  private boolean isSelected;


  public static final int CARD_WIDTH = 35;
  public static final int CARD_HEIGHT = 50;

  private static final String NONSELECTED_CARD = "card";
  private static final String SELECTED_CARD = "cardSelected";



  public CardView(String rankSymbol, String suitImage, String cardBack, boolean isFrontEndVisible) {
    super(rankSymbol, suitImage);

    this.rankSymbol = rankSymbol;
    this.suitImage = suitImage;
    this.cardBack = cardBack;

    isSelected = false;

    this.getStyleClass().add(NONSELECTED_CARD);
    setFrontEndVisible(isFrontEndVisible);
  }

  public void toggleCardSelected() {
    isSelected = !isSelected;
    if (isSelected) {
      this.getStyleClass().remove("card");
      this.getStyleClass().add("cardSelected");
    } else {
      this.getStyleClass().remove("cardSelected");
      this.getStyleClass().add("card");
    }
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

  public boolean getIsFrontEndVisible() {
    return isFrontEndVisible;
  }
}

