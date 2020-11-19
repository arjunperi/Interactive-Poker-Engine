package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import pokerSuite.PokerRunner;

import java.util.ArrayList;
import java.util.List;


public class GameView {

  private Scene scene;
  private BorderPane root;
  private Group topGroup;
  private Group centerGroup;
  private Group bottomGroup;
  private Group rightGroup;
  private Button homeButton;
  private VBox gameBox;
  private ListView actionLog;

  public GameView() {
    root = new BorderPane();
    initializeBorderPane();
    gameBox = new VBox();
  }

  public void initializeBorderPane() {
    topGroup = new Group();
    topGroup.setId("Top");
    centerGroup = new Group();
    centerGroup.setId("Center");
    bottomGroup = new Group();
    bottomGroup.setId("Center");
    rightGroup = new Group();
    rightGroup.setId("Right");
    root.setRight(rightGroup);
    root.setCenter(centerGroup);
    root.setTop(topGroup);
    root.setBottom(bottomGroup);
  }

  public Scene setupScene() {
    scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
        PokerRunner.BACKGROUND);
    scene.getStylesheets().add(getClass().getResource("/dukeTheme.css").toExternalForm());
    return this.scene;
  }


  public void clear() {
    root.getChildren().clear();
    gameBox = new VBox();
    initializeBorderPane();
  }

  public Alert makeCashOutAlert(String playerName, int playerBankroll) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Cash Out");
    alert.setHeaderText("CASH OUT CONFIRMATION");
    alert.setContentText(
        playerName + ", are you sure you want to cash out? You have $" + playerBankroll);
    return alert;
  }

  public void makeMainMenu(EventHandler<ActionEvent> gameSelectEvent,
      EventHandler<ActionEvent> homeEvent) {
    clear();
    VBox startBox = new VBox();
    startBox.setId("StartBox");
    homeButton = makeButton("Main Menu", homeEvent);
    homeButton.setId("MainMenu");
    Button gameSelectButton = makeButton("Game Select", gameSelectEvent);
    gameSelectButton.setId("GameSelect");
    startBox.getChildren().addAll(gameSelectButton);
    centerGroup.getChildren().add(startBox);
  }

  public Dialog makeDialogBox(TextField input, String prompt, String promptText) {
    clear();
    Dialog dialogBox = new TextInputDialog();
    dialogBox.setHeaderText(prompt);
    input.setPromptText(promptText);


    GridPane grid = new GridPane();
    grid.setId(prompt);

    GridPane.setConstraints(input, 0, 1);
    grid.getChildren().add(input);
    dialogBox.getDialogPane().setContent(grid);

    return dialogBox;
  }

  public void makePlayerSelectScreen(EventHandler<ActionEvent> newPlayerEvent,
      EventHandler<ActionEvent> loadPlayerEvent) {
    clear();
    VBox menuScreen = new VBox();
    menuScreen.getStyleClass().add("menuBox");


    HBox titleBox = new HBox();
    titleBox.getStyleClass().add("titleBox");

    Text title = new Text("Poker Player");
    titleBox.getChildren().add(title);
    title.getStyleClass().add("title");



    VBox playerBox = new VBox();
    playerBox.getStyleClass().add("vBox");



    playerBox.setId("PlayerBox");
    Button newPlayerButton = makeButton("New Player", newPlayerEvent);
    newPlayerButton.setId("New Player");
    Button loadSavedPlayer = makeButton("Load Player", loadPlayerEvent);

    playerBox.getChildren().addAll(newPlayerButton, loadSavedPlayer);

    menuScreen.getChildren().addAll(titleBox, playerBox);

    centerGroup.getChildren().addAll(menuScreen);




    /*menuScreen.setStyle("-fx-spacing: 200");

    title.setStyle("    -fx-font-family: \"Optima\";\n"
        + "    -fx-font-size: 80px;");
    playerBox.setStyle("-fx-alignment: center; -fx-spacing: 10");*/

  }

  public void addGameObject(Node gameObject) {
    centerGroup.getChildren().remove(gameBox);
    topGroup.getChildren().remove(homeButton);
    bottomGroup.getChildren().clear();
    centerGroup.getChildren().add(gameObject);
  }

  public void makeGameSelectScreen(EventHandler<ActionEvent> holdemEvent,
      EventHandler<ActionEvent> drawEvent, EventHandler<ActionEvent> studEvent,
      EventHandler<ActionEvent> customEvent) {
    centerGroup.getChildren().clear();
    gameBox.setId("GameBox");
    gameBox.getStyleClass().add("vBox");
    Button holdEmButton = makeButton("Holdem", holdemEvent);
    holdEmButton.setId("Holdem");
    Button drawButton = makeButton("Draw", drawEvent);
    drawButton.setId("Draw");
    Button studButton = makeButton("Stud", studEvent);
    studButton.setId("Stud");
    Button customButton = makeButton("Custom", customEvent);
    customButton.setId("Custom");
    gameBox.getChildren().addAll(holdEmButton, drawButton, studButton, customButton);
    centerGroup.getChildren().add(gameBox);
    topGroup.getChildren().add(homeButton);
  }



  public Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    result.setText(property);
    result.setOnAction(handler);
    return result;
  }

  public ChoiceDialog makeActionScreen(String playerName, int lastBet, int callAmount) {
    //centerGroup.getChildren().clear();
    Button cashOutButton = new Button("Cash Out");
    cashOutButton.setId("CashOut");

    Button foldButton = new Button("Fold");
    foldButton.setId("Fold");

    Button checkButton = new Button("Check");
    checkButton.setId("Check");

    Button callButton = new Button("Call ($" + callAmount + ")");
    callButton.setId("Call");

    Button betButton = new Button("Bet");
    betButton.setId("Bet");

    List<Button> choices = new ArrayList<>();
    choices.add(foldButton);
    choices.add(betButton);
    choices.add(cashOutButton);

    if (lastBet > 0) {
      choices.add(callButton);
    } else {
      choices.add(checkButton);
    }

    ChoiceDialog<Button> dialog = new ChoiceDialog<Button>(foldButton, choices);
    dialog.setTitle("Select Action");
    dialog.setHeaderText(playerName + ", you're up! What would you like to do?");
    dialog.setContentText("Choose your action:");

    setDialogOnTop(dialog);


    return dialog;
  }


  public Dialog makeBetPopUp(TextField input, String message) {
    bottomGroup.getChildren().clear();

    Dialog betBox = new TextInputDialog();
    betBox.setHeaderText(message);

    input.setPromptText("Bet Amount");
    input.setId("Bet");

    GridPane grid = new GridPane();
    grid.setId("OptionPane");

    GridPane.setConstraints(input, 0, 1);
    grid.getChildren().add(input);
    betBox.getDialogPane().setContent(grid);

    setDialogOnTop(betBox);


    return betBox;
  }

  public GridPane getGrid(Dialog betBox) {
    Node grid = betBox.getDialogPane().getContent();
      if (grid.getId().equals("OptionPane")) {
          ;
      }
    {
      return (GridPane) grid;
    }
  }

  public Button getButton(Dialog betBox, String buttonName) {
    GridPane grid = getGrid(betBox);
      for (Node node : grid.getChildrenUnmodifiable()) {
          if (node.getId().equals(buttonName)) {
              Button desiredButton = (Button) node;
              return desiredButton;
          }
      }
    return null;
  }

  public Dialog makeExchangeScreen(String playerName, int maxExchangeCards) {
    Alert.AlertType type = AlertType.CONFIRMATION;
    Dialog exchangeBox = new Alert(type);
    exchangeBox.initModality(Modality.NONE);
    exchangeBox.setTitle("Exchange Cards");
    exchangeBox.setHeaderText(String.format(
        "%s is up!%nSelect no more than %d card(s) to exchange and then press OK. \nDon't try to cheat! "
            + " You will be reprompted if you try to select more than the specified amount.",
        playerName, maxExchangeCards));
    setDialogOnTop(exchangeBox);
    return exchangeBox;
  }

  private void setDialogOnTop(Dialog exchangeBox) {
    Stage dialogStage = (Stage) exchangeBox.getDialogPane().getScene().getWindow();
    dialogStage.setX(900);
    dialogStage.setY(900);
    dialogStage.setAlwaysOnTop(true);
  }


  //maybe combine this with bet screen input
  public Dialog makeBuyInScreen(TextField buyBackInput) {
    Dialog buyBackBox = new TextInputDialog();
    buyBackBox.setHeaderText(
        "Out of money!\nPlease enter your buy-back-in amount to continue playing, or press cancel to exit: ");

    buyBackInput.setPromptText("Amount: ");
    buyBackInput.setId("BuyBack");

    GridPane grid = new GridPane();
    grid.setId("BuyBackPane");

    GridPane.setConstraints(buyBackInput, 0, 1);
    grid.getChildren().add(buyBackInput);
    buyBackBox.getDialogPane().setContent(grid);
    setDialogOnTop(buyBackBox);

    return buyBackBox;
  }

  public void makeEndRoundScreen(EventHandler<ActionEvent> nextRoundEvent,
      EventHandler<ActionEvent> cashOutEvent) {
    HBox nextRoundBox = new HBox();
    nextRoundBox.getStyleClass().add("hBox");
    Button nextRoundButton = makeButton("Deal next round", nextRoundEvent);
    Button cashOutButton = makeButton("Cash Out", cashOutEvent);
    nextRoundBox.getChildren().addAll(nextRoundButton, cashOutButton);
    bottomGroup.getChildren().add(nextRoundBox);
  }

  public void makeActionLog() {
    actionLog = new ListView<>();
    actionLog.setId("ActionLog");
    actionLog.setMinHeight(300);
    actionLog.setMinWidth(600);
    rightGroup.getChildren().add(actionLog);
  }

  public void addToActionLog(String playerAction) {
    actionLog.getItems().add(playerAction);
  }
}
