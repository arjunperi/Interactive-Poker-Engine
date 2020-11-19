
package view;

public class PotView extends PlayerHUDGameObject {

  private double potViewLocationX;
  private double potViewLocationY;


  public PotView(String potAmount, String potImage, int pokerTableX, int pokerTableY) {
    super(potAmount, potImage);
    potViewLocationX = pokerTableX - (IMAGE_WIDTH / 2);
    potViewLocationY = pokerTableY - (2 * IMAGE_HEIGHT);

    setLayoutX(potViewLocationX);
    setLayoutY(potViewLocationY);
  }

  public double getPotViewLocationX() {
    return potViewLocationX;
  }

  public double getPotViewLocationY() {
    return potViewLocationY;
  }


}

