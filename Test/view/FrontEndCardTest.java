package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Controller;
import javafx.stage.Stage;
import model.Card;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class FrontEndCardTest extends DukeApplicationTest {

  @Test
  public void testFrontEndCardCreation() {
    FrontEndCard frontEndQueen = new FrontEndCard("Q", "CLUBS", false);
    assertEquals(frontEndQueen.getText(), "Q\n" +
        "CLUBS\n" +
        "NON VISIBLE");
  }

}
