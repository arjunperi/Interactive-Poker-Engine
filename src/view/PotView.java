
package view;

public class PotView extends PlayerHUDGameObject {

  public PotView(String potAmount, String potImage, int pokerTableX, int pokerTableY) {
    super(potAmount, potImage);
    setLayoutX(pokerTableX - (IMAGE_WIDTH / 2));
    setLayoutY(pokerTableY - (2 * IMAGE_HEIGHT));
  }

}

