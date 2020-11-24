package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


public class GameViewTest extends DukeApplicationTest {

  private Controller controller;
  private Stage stage;

  public void start(final Stage stage){
    controller = new Controller();
    this.stage = stage;
    this.stage.setScene(controller.setupScene());
    this.stage.show();
  }

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

  @Test
  public void testHoldemProgression(){
    Controller controller = new Controller();
    javafxRun(() -> controller.setInteractivePlayerStats("Arjun",100));
    javafxRun(() -> controller.initializeProperties("HoldEm.properties"));
  }

  @Test
  public void testInvalidCustomGameProgression(){
    Controller controller = new Controller();
    javafxRun(() -> controller.setInteractivePlayerStats("Arjun",100));
    javafxRun(controller::setAlterState);
    javafxRun(controller::changeInteractiveActionCompletion);
    assertThrows(IllegalStateException.class, () -> controller.initializeProperties("HoldEmDrawCombo.properties"));
  }





}
