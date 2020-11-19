package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

  public void start(final Stage stage) throws Exception {
    controller = new Controller();
    this.stage = stage;
    this.stage.setScene(controller.setupScene());
    this.stage.show();
  }

//    @Test
//    public void testFrontEndDeal() {
//        CommunityCards communityCards = new CommunityCards();
//        Pot pot = new Pot();
//        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
//        player.receiveCard(new Card(14, "DIAMONDS"));
//        player.receiveCard(new Card(13, "DIAMONDS"));
//        FrontEndPlayer frontEndPlayer = new FrontEndPlayer(10, 30, "Arjun", 100);
//        javafxRun(() -> controller.dealFrontEndCardsInRound(player, frontEndPlayer));
//        testCard = lookup("#ADIAMONDS").query();
//        assertEquals(20, testCard.getX());
//        testCard = lookup("#KDIAMONDS").query();
//        assertEquals(100, testCard.getX());
//
//        Player player2 = new InteractivePlayer("Christian", 100, communityCards, pot);
//        player2.receiveCard(new Card(12, "DIAMONDS"));
//        player2.receiveCard(new Card(11, "DIAMONDS"));
//        FrontEndPlayer frontEndPlayer2 = new FrontEndPlayer(10, 80, "Christian", 100);
//        javafxRun(() -> controller.dealFrontEndCardsInRound(player2, frontEndPlayer2));
//        testCard = lookup("#QDIAMONDS").query();
//        assertEquals(20, testCard.getX());
//        testCard = lookup("#JDIAMONDS").query();
//        assertEquals(100, testCard.getX());
//    }

  @Test
  public void testStartBox() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
  }

  @Test
  public void testGameSelectButton() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    assertTrue(startBox.getChildren().contains(selectButton));
  }

  @Test
  public void testSelectionBox() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    clickOn(selectButton);
    CenterGroup = lookup("#Center").query();
    VBox gameBox = lookup("#GameBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(gameBox));
  }

  @Test
  public void testGameSelectHoldem() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    clickOn(selectButton);
    VBox gameBox = lookup("#GameBox").query();
    Button holdemButton = lookup("#Holdem").query();
    clickOn(holdemButton);
    assertTrue(gameBox.getChildrenUnmodifiable().contains(holdemButton));
  }

  @Test
  public void testGameSelectStud() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    clickOn(selectButton);
    VBox gameBox = lookup("#GameBox").query();
    Button studButton = lookup("#Stud").query();
    clickOn(studButton);
    assertTrue(gameBox.getChildrenUnmodifiable().contains(studButton));
  }

  @Test
  public void testGameSelectDraw() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    clickOn(selectButton);
    VBox gameBox = lookup("#GameBox").query();
    Button drawButton = lookup("#Draw").query();
    clickOn(drawButton);
    assertTrue(gameBox.getChildrenUnmodifiable().contains(drawButton));
  }

  @Test
  public void testGameSelectCustom() {
    javafxRun(() -> controller.initializeMainMenu());
    Group CenterGroup = lookup("#Center").query();
    VBox startBox = lookup("#StartBox").query();
    assertTrue(CenterGroup.getChildrenUnmodifiable().contains(startBox));
    Button selectButton = lookup("#GameSelect").query();
    clickOn(selectButton);
    VBox gameBox = lookup("#GameBox").query();
    Button customButton = lookup("#Custom").query();
    clickOn(customButton);
    assertTrue(gameBox.getChildrenUnmodifiable().contains(customButton));
    javafxRun(() -> stage.close());
  }

  @Test
  public void testNewPlayerBox() {
    javafxRun(() -> controller.initializeNewPlayer());
    TextField nameInput = lookup("#nameInput").query();
    clickOn(nameInput);
    type(KeyCode.C, KeyCode.H, KeyCode.R, KeyCode.I, KeyCode.S);
    assertEquals(nameInput.getText(), "chris");
  }



  @Test
  public void testGetNumAutoPlayerBox() {
    javafxRun(() -> controller.getNumAutoPlayers());
    TextField numAutoPlayerInput = lookup("#numAutoPlayerInput").query();
    clickOn(numAutoPlayerInput);
    type(KeyCode.DIGIT6);
    assertEquals(numAutoPlayerInput.getText(), "6");
  }

  @Test
  public void testNewPlayerStartingAmount() {
    javafxRun(() -> controller.initializeNewPlayerStartingAmount());
    TextField startingMoneyInput = lookup("#startingMoneyInput").query();
    clickOn(startingMoneyInput);
    type(KeyCode.DIGIT6, KeyCode.DIGIT0, KeyCode.DIGIT0);
    assertEquals(startingMoneyInput.getText(), "600");
  }

//    @Test
//    public void testBettingMenu() {
//        CommunityCards communityCards = new CommunityCards();
//        Pot pot = new Pot();
//        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
//        javafxRun(() -> controller.initializeGameSelect());
//        Button startButton = lookup("#Holdem").queryButton();
//        clickOn(startButton);
//        javafxRun(() -> {
//            controller.displayBetMenu(player);
//        });
//        TextField betInput = lookup("#Bet").query();
//        GridPane optionPane = lookup("#OptionPane").query();
//        assertTrue(optionPane.getChildren().contains(betInput));
//    }
//
//    @Test
//    public void testFrontEndExchange() {
//        CommunityCards communityCards = new CommunityCards();
//        Pot pot = new Pot();
//        Player player = new InteractivePlayer("Arjun", 100, communityCards, pot);
//        Card card1 = new Card(14, "DIAMONDS");
//        Card card2 = new Card(13, "DIAMONDS");
//        player.receiveCard(card1);
//        player.receiveCard(card2);
//        FrontEndPlayer frontEndPlayer = new FrontEndPlayer(10, 30, "Arjun", 100);
//        javafxRun(() -> controller.dealFrontEndCardsInRound(player, frontEndPlayer));
//
//        player.clearDiscardedCards();
//        player.clearNewCards();
//        player.discardCard(card1);
//        player.discardCard(card2);
//
//        Card card3 = new Card(12, "DIAMONDS");
//        Card card4 = new Card(11, "DIAMONDS");
//        player.receiveCard(card3);
//        player.receiveCard(card4);
//
//        javafxRun(() -> controller.exchangeFrontEndCards(player, frontEndPlayer));
//        testCard = lookup("#JDIAMONDS").query();
//        assertEquals(100, testCard.getX());
//    }

//  @Test
//  public void testExchangeInputScreen() {
//    javafxRun(() -> controller.initializeGameSelect());
//    Button startButton = lookup("#Holdem").queryButton();
//    clickOn(startButton);
//    javafxRun(() -> {
//      controller.exchangeRound();
//    });
//    GridPane exchangeGrid = lookup("#ExchangeGrid").query();
//    TextField exchangeCardInput1 = lookup("#ExchangeCard1").query();
//    TextField exchangeCardInput2 = lookup("#ExchangeCard2").query();
//    TextField exchangeCardInput3 = lookup("#ExchangeCard3").query();
//    assertTrue(exchangeGrid.getChildren()
//        .containsAll(List.of(exchangeCardInput1, exchangeCardInput2, exchangeCardInput3)));
//  }

  @Test
  public void testInvalidNumberPlayersExceptionThrown() {
    javafxRun(() -> controller.getNumAutoPlayers());
    TextField numAutoPlayerInput = lookup("#numAutoPlayerInput").query();
    clickOn(numAutoPlayerInput);
    type(KeyCode.DIGIT8);
    type(KeyCode.ENTER);
    assertTrue(controller.invalidPlayersEntered(Integer.parseInt(numAutoPlayerInput.getText())));
    //assertThrows(InvalidNumberPlayersException.class, () -> javafxRun(() -> controller.getNumAutoPlayers()));
  }

  @Test
  public void testInvalidNameEnteredThrown() {
    javafxRun(() -> controller.initializeNewPlayer());
    TextField nameInput = lookup("#nameInput").query();
    clickOn(nameInput);
    type(KeyCode.DIGIT8);
    type(KeyCode.ENTER);
    assertTrue(controller.invalidNameEntered(nameInput.getText()));

  }

  @Test
  public void testInvalidStartingAmount() {
    javafxRun(() -> controller.initializeNewPlayerStartingAmount());
    TextField amountInput = lookup("#startingMoneyInput").query();
    clickOn(amountInput);
    type(KeyCode.DIGIT8); //8 dollars is not enough
    type(KeyCode.ENTER);

    assertFalse(controller.isValidInteger(Integer.parseInt(amountInput.getText())));
  }



}
