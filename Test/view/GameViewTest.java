//package view;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import controller.Controller;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Dialog;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//import model.Game;
//import model.Model;
//import model.Player;
//import org.junit.jupiter.api.Test;
//import util.DukeApplicationTest;
//
//public class GameViewTest extends DukeApplicationTest {
//    private Controller controller;
//    private Stage stage;
//    private TextField betInput;
//    private FrontEndCard testCard;
//
//    public void start(final Stage stage) throws Exception {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        {
//            controller = new Controller(stage);
//            stage.setScene(controller.setupScene());
//            stage.show();
//        }
//    }
//
//    //note: these tests will likely fail with a different number of players
//    @Test
//    public void testDealCards() {
//        controller.initializeModel("Holdem");
//        Button startButton = lookup("#Start").queryButton();
//        clickOn(startButton);
//        testCard = lookup("#ADIAMONDS").query();
//        assertEquals(20, testCard.getX());
//        testCard = lookup("#KDIAMONDS").query();
//        assertEquals(100, testCard.getX());
//        testCard = lookup("#QDIAMONDS").query();
//        assertEquals(20, testCard.getX());
//        testCard = lookup("#JDIAMONDS").query();
//        assertEquals(100, testCard.getX());
//    }
//
//    @Test
//    public void testExchangeCards() {
//        javafxRun(() -> controller.initializeSplashMenu());
//
//        controller.initializeModel("FiveCardDraw");
//        Button startButton = lookup("#Start").queryButton();
//        clickOn(startButton);
//        TextField betInputPlayer1 = lookup("#Bet").query();
//        betInputPlayer1.setText("10");
//        press(KeyCode.ENTER);
//        sleep(1000);
//        TextField betInputPlayer2 = lookup("#Bet").query();
//        betInputPlayer2.setText("10");
//        press(KeyCode.ENTER);
//
//        javafxRun(() -> controller.exchangeRound());
//        TextField exchangeInput1 = lookup("#ExchangeCard1").query();
//        exchangeInput1.setText("DIAMONDS 14");
//        clickOn("Ok");
////        javafxRun(()-> press(KeyCode.ENTER));
//        sleep(1000);
//        TextField exchangeInput2 = lookup("#ExchangeCard2").query();
//        exchangeInput2.setText("DIAMONDS 10");
//        sleep(1000);
//        press(KeyCode.ENTER);
//        javafxRun(() -> controller.initializeBettingMenu());
//
//        testCard = lookup("#8DIAMONDS").query();
//        assertEquals(20, testCard.getX());
//    }
//}
