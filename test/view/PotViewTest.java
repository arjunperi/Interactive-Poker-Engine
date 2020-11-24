package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Controller;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class PotViewTest extends DukeApplicationTest {

  private Controller controller;
  private Stage stage;
  private PotView pot;

  public void start(final Stage stage) throws Exception {
    controller = new Controller();
    this.stage = stage;
    this.stage.setScene(controller.setupScene());
    this.stage.show();
  }

  @BeforeEach
  void setUp() {
    pot = new PotView("100", "/pot.png", 300, 300);

  }


  @Test
  void getPotViewLocationX() {
    int expectedPotViewX = 283;
    int actualPotViewX = (int) pot.getPotViewLocationX();

    assertEquals(expectedPotViewX, actualPotViewX);
  }

  @Test
  void getPotViewLocationY() {
    int expectedPotViewY = 200;
    int actualPotViewY = (int) pot.getPotViewLocationY();

    assertEquals(expectedPotViewY, actualPotViewY);
  }
}