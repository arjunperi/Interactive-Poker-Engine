package view;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameViewTest extends DukeApplicationTest {
    private Controller controller;
    private Stage stage;
    private FrontEndCard testCard;

    public void start(final Stage stage) throws Exception {
        controller = new Controller();
        this.stage = stage;
        this.stage.setScene(controller.setupScene());
        this.stage.show();
    }

    @Test
    public void testFrontEndDeal() {
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
        player.receiveCard(new Card(14, "DIAMONDS"));
        player.receiveCard(new Card(13, "DIAMONDS"));
        FrontEndPlayer frontEndPlayer = new FrontEndPlayer(10, 30, "Arjun", 100);
        javafxRun(() -> controller.dealFrontEndCardsInRound(player, frontEndPlayer));
        testCard = lookup("#ADIAMONDS").query();
        assertEquals(20, testCard.getX());
        testCard = lookup("#KDIAMONDS").query();
        assertEquals(100, testCard.getX());

        Player player2 = new InteractivePlayer("Christian", 100, communityCards, pot);
        player2.receiveCard(new Card(12, "DIAMONDS"));
        player2.receiveCard(new Card(11, "DIAMONDS"));
        FrontEndPlayer frontEndPlayer2 = new FrontEndPlayer(10, 80, "Christian", 100);
        javafxRun(() -> controller.dealFrontEndCardsInRound(player2, frontEndPlayer2));
        testCard = lookup("#QDIAMONDS").query();
        assertEquals(20, testCard.getX());
        testCard = lookup("#JDIAMONDS").query();
        assertEquals(100, testCard.getX());
    }

    @Test
    public void testGameSelect() {
        javafxRun(() -> controller.initializeGameSelect());
        Group CenterGroup = lookup("#Center").query();
        VBox gameBox = lookup("#GameBox").query();
        assertTrue(CenterGroup.getChildrenUnmodifiable().contains(gameBox));
    }

    /*@Test
    public void testBettingMenu() {

        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
        javafxRun(() -> controller.initializeSplashMenu());
        Button startButton = lookup("#Start").queryButton();

        clickOn(startButton);
        javafxRun(() -> {
            controller.displayBetMenu(player);
        });
        TextField betInput = lookup("#Bet").query();
        GridPane optionPane = lookup("#OptionPane").query();
        assertTrue(optionPane.getChildren().contains(betInput));
    }*/

    @Test
    public void testFrontEndExchange() {
        CommunityCards communityCards = new CommunityCards();
        Pot pot = new Pot();
        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
        Card card1 = new Card(14, "DIAMONDS");
        Card card2 = new Card(13, "DIAMONDS");
        player.receiveCard(card1);
        player.receiveCard(card2);
        FrontEndPlayer frontEndPlayer = new FrontEndPlayer(10, 30, "Arjun", 100);
        javafxRun(() -> controller.dealFrontEndCardsInRound(player, frontEndPlayer));

        player.clearDiscardedCards();
        player.clearNewCards();
        player.discardCard(card1);
        player.discardCard(card2);

        Card card3 = new Card(12, "DIAMONDS");
        Card card4 = new Card(11, "DIAMONDS");
        player.receiveCard(card3);
        player.receiveCard(card4);

        javafxRun(() -> controller.exchangeFrontEndCards(player, frontEndPlayer));
        testCard = lookup("#JDIAMONDS").query();
        assertEquals(100, testCard.getX());
    }
}
