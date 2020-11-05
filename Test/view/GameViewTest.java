package view;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;
import model.Model;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameViewTest extends DukeApplicationTest {
    private Controller controller;
    private Stage stage;
    private TextField betInput;
    private FrontEndCard testCard;

    public void start(final Stage stage) {
        controller = new Controller(stage);
        stage.setScene(controller.setupScene());
        stage.show();
    }

    @Test
    public void testDealCards(){
        javafxRun(() -> controller.initializeSplashMenu());
        Button startButton = lookup("#Start").queryButton();
        clickOn(startButton);
        testCard = lookup("#ADIAMONDS").query();
        assertEquals(20, testCard.getX());
        testCard = lookup("#KDIAMONDS").query();
        assertEquals(90, testCard.getX());
        testCard = lookup("#QDIAMONDS").query();
        assertEquals(160, testCard.getX());
        testCard = lookup("#JDIAMONDS").query();
        assertEquals(230, testCard.getX());
//        betInput = lookup("#Bet").query();
//        betInput.setText("10");
//        sleep(1000);
//        betInput= lookup("#Bet").query();
//        betInput.setText("10");
//        sleep(1000);
    }
}
