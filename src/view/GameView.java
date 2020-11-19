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
  private static final String TOP = "Top";
  private static final String CENTER = "Center";
  private static final String RIGHT = "Right";
  private static final String THEME = "/dukeTheme.css";
  private static final String CASHOUT_TITLE = "Cash Out";
  private static final String CASHOUT_CONFIRMATION = "CASH OUT CONFIRMATION";
  private static final String CASHOUT_ALERT = ", are you sure you want to cash out? You have $";
  private static final String STARTBOX_ID = "StartBox";
  private static final String HOME_BUTTON_TEXT = "Main Menu";
  private static final String HOME_BUTTON_ID = "MainMenu";
  private static final String SELECT_BUTTON_TEXT = "Game Select";
  private static final String SELECT_BUTTON_ID = "GameSelect";
  private static final String GRID_ID = "Grid";
  private static final String MENU_BOX = "menuBox";
  private static final String TITLE_BOX = "titleBox";
  private static final String TITLE_TEXT = "Poker Player";
  private static final String TITLE = "title";
  private static final String VBOX = "vBox";
  private static final String PLAYER_BOX_ID = "PlayerBox";
  private static final String NEW_PLAYER_TEXT = "New Player";
  private static final String NEW_PLAYER_ID = "New Player";
  private static final String LOAD_PLAYER_TEXT = "Load Player";
  private static final String GAMEBOX_ID = "GameBox";
  private static final String HOLDEM_BUTTON_TEXT = "Holdem";
  private static final String HOLDEM_BUTTON_ID = "Holdem";
  private static final String DRAW_BUTTON_TEXT = "Draw";
  private static final String DRAW_BUTTON_ID = "Draw";
  private static final String STUD_BUTTON_TEXT = "Stud";
  private static final String STUD_BUTTON_ID = "Stud";
  private static final String CUSTOM_BUTTON_TEXT = "Custom";
  private static final String CUSTOM_BUTTON_ID = "Custom";
  private static final String CASHOUT_BUTTON_TEXT = "Cash Out";
  private static final String CASHOUT_BUTTON_ID = "CashOut";
  private static final String FOLD_BUTTON_TEXT = "Fold";
  private static final String FOLD_BUTTON_ID = "Fold";
  private static final String CHECK_BUTTON_TEXT = "Check";
  private static final String CHECK_BUTTON_ID = "Check";
  private static final String CALL_BUTTON_TEXT = "Call";
  private static final String CALL_BUTTON_ID = "Call";
  private static final String BET_BUTTON_TEXT = "Bet";
  private static final String BET_BUTTON_ID = "Bet";
  private static final String DIALOG_BOX_TITLE= "Select Action";
  private static final String DIALOG_BOX_HEADER_TEXT = ", you're up! What would you like to do?";
  private static final String DIALOG_BOX_TEXT = "Choose your action:";
  private static final String PROMPT_TEXT = "Bet Amount";
  private static final String PROMPT_ID = "Bet";
  private static final String GRIDPANE_ID = "OptionPane";
  private static final String EXCHANGE_BOX_TITLE = "Exchange Cards";
  private static final String EXCHANGE_BOX_HEADER = "%s is up!%nSelect no more than %d card(s) to exchange and then press OK. \nDon't try to cheat! "
      + " You will be reprompted if you try to select more than the specified amount.";
  private static final String BUY_IN_HEADER=  "Out of money!\nPlease enter your buy-back-in amount to continue playing, or press cancel to exit: ";
  private static final String BUY_IN_TEXT = "Amount: ";
  private static final String BUY_IN_ID = "BuyBack: ";
  private static final String BUY_BACK_PANE = "BuyBackPane";
  private static final String HBOX = "hBox";
  private static final String DEAL_NEXT_ROUND_BUTTON_TEXT = "Deal next round";
  private static final String CASH_OUT_BUTTON_TEXT = "Cash Out";
  private static final String ACTION_LOG_ID = "ActionLog";
  private static final String PLAYER_HISTORY = "playerHistory";
  private static final String PLAYER_HISTORY_BOX = "playerHistoryList";



  private Scene scene;
  private BorderPane root;
  private Group topGroup;
  private Group centerGroup;
  private Group bottomGroup;
  private Group rightGroup;
  private Button homeButton;
  private VBox gameBox;
  private ListView<String> actionLog;

  public GameView() {
    root = new BorderPane();
    initializeBorderPane();
    gameBox = new VBox();
  }

  public void initializeBorderPane() {
    topGroup = new Group();
    topGroup.setId(TOP);
    centerGroup = new Group();
    centerGroup.setId(CENTER);
    bottomGroup = new Group();
    bottomGroup.setId(CENTER);
    rightGroup = new Group();
    rightGroup.setId(RIGHT);
    root.setRight(rightGroup);
    root.setCenter(centerGroup);
    root.setTop(topGroup);
    root.setBottom(bottomGroup);
  }

  public Scene setupScene() {
    scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
        PokerRunner.BACKGROUND);
    scene.getStylesheets().add(getClass().getResource(THEME).toExternalForm());
    return this.scene;
  }


  public void clear() {
    root.getChildren().clear();
    gameBox = new VBox();
    initializeBorderPane();
  }

  public Alert makeCashOutAlert(String playerName, int playerBankroll) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(CASHOUT_TITLE);
    alert.setHeaderText(CASHOUT_CONFIRMATION);
    alert.setContentText(
        playerName + CASHOUT_ALERT + playerBankroll);
    return alert;
  }

  public void makeMainMenu(EventHandler<ActionEvent> gameSelectEvent,
      EventHandler<ActionEvent> homeEvent) {
    clear();
    VBox startBox = new VBox();
    startBox.setId(STARTBOX_ID);
    homeButton = makeButton(HOME_BUTTON_TEXT, homeEvent);
    homeButton.setId(HOLDEM_BUTTON_ID);
    Button gameSelectButton = makeButton(SELECT_BUTTON_TEXT, gameSelectEvent);
    gameSelectButton.setId(SELECT_BUTTON_ID);
    startBox.getChildren().addAll(gameSelectButton);
    centerGroup.getChildren().add(startBox);
  }

  public TextInputDialog makeDialogBox(TextField input, String prompt, String promptText) {
    clear();
    TextInputDialog dialogBox = new TextInputDialog();
    dialogBox.setHeaderText(prompt);
    input.setPromptText(promptText);

    GridPane grid = new GridPane();
    grid.setId(GRID_ID);
    GridPane.setConstraints(input, 0, 1);
    grid.getChildren().add(input);

    dialogBox.getDialogPane().setContent(grid);

    return dialogBox;
  }


  public void makePlayerSelectScreen(EventHandler<ActionEvent> newPlayerEvent,
      EventHandler<ActionEvent> loadPlayerEvent) {
    clear();
    VBox menuScreen = new VBox();
    menuScreen.getStyleClass().add(MENU_BOX);


    HBox titleBox = new HBox();
    titleBox.getStyleClass().add(TITLE_BOX);

    Text title = new Text(TITLE_TEXT);
    titleBox.getChildren().add(title);
    title.getStyleClass().add(TITLE);



    VBox playerBox = new VBox();
    playerBox.getStyleClass().add(VBOX);



    playerBox.setId(PLAYER_BOX_ID);
    Button newPlayerButton = makeButton(NEW_PLAYER_TEXT, newPlayerEvent);
    newPlayerButton.setId(NEW_PLAYER_ID);
    Button loadSavedPlayer = makeButton(LOAD_PLAYER_TEXT, loadPlayerEvent);

    playerBox.getChildren().addAll(newPlayerButton, loadSavedPlayer);

    menuScreen.getChildren().addAll(titleBox, playerBox);
    loadSavedPlayer.setId("Load Player");
    newPlayerButton.setId("New Player");
    centerGroup.getChildren().addAll(menuScreen);

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
    gameBox.setId(GAMEBOX_ID);
    gameBox.getStyleClass().add(VBOX);
    Button holdEmButton = makeButton(HOLDEM_BUTTON_TEXT, holdemEvent);
    holdEmButton.setId(HOLDEM_BUTTON_ID);
    Button drawButton = makeButton(DRAW_BUTTON_TEXT, drawEvent);
    drawButton.setId(DRAW_BUTTON_ID);
    Button studButton = makeButton(STUD_BUTTON_TEXT, studEvent);
    studButton.setId(STUD_BUTTON_ID);
    Button customButton = makeButton(CUSTOM_BUTTON_TEXT, customEvent);
    customButton.setId(CUSTOM_BUTTON_ID);
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

  public ChoiceDialog<Button> makeActionScreen(String playerName, int lastBet, int callAmount) {
    Button cashOutButton = new Button(CASHOUT_BUTTON_TEXT);
    cashOutButton.setId(CASHOUT_BUTTON_ID);

    Button foldButton = new Button(FOLD_BUTTON_TEXT);
    foldButton.setId(FOLD_BUTTON_ID);

    Button checkButton = new Button(CHECK_BUTTON_TEXT);
    checkButton.setId(CHECK_BUTTON_ID);

    Button callButton = new Button(CALL_BUTTON_TEXT + "($" + callAmount + ")");
    callButton.setId(CALL_BUTTON_ID);

    Button betButton = new Button(BET_BUTTON_TEXT);
    betButton.setId(BET_BUTTON_ID);

    List<Button> choices = new ArrayList<>();
    choices.add(foldButton);
    choices.add(betButton);
    choices.add(cashOutButton);

    if (lastBet > 0) {
      choices.add(callButton);
    } else {
      choices.add(checkButton);
    }

    ChoiceDialog<Button> dialog = new ChoiceDialog<>(foldButton, choices);
    dialog.setTitle(DIALOG_BOX_TITLE);
    dialog.setHeaderText(playerName + DIALOG_BOX_HEADER_TEXT);
    dialog.setContentText(DIALOG_BOX_TEXT);

    setDialogOnTop(dialog);


    return dialog;
  }


  public TextInputDialog makeBetPopUp(TextField input, String message) {
    bottomGroup.getChildren().clear();

    TextInputDialog betBox = new TextInputDialog();
    betBox.setHeaderText(message);

    input.setPromptText(PROMPT_TEXT);
    input.setId(PROMPT_ID);

    GridPane grid = new GridPane();
    grid.setId(GRIDPANE_ID);

    GridPane.setConstraints(input, 0, 1);
    grid.getChildren().add(input);
    betBox.getDialogPane().setContent(grid);

    setDialogOnTop(betBox);


    return betBox;
  }

  public Alert makeExchangeScreen(String playerName, int maxExchangeCards) {
    Alert.AlertType type = AlertType.CONFIRMATION;
    Alert exchangeBox = new Alert(type);
    exchangeBox.initModality(Modality.NONE);
    exchangeBox.setTitle(EXCHANGE_BOX_TITLE);
    exchangeBox.setHeaderText(String.format(EXCHANGE_BOX_HEADER, playerName, maxExchangeCards));
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
  public TextInputDialog makeBuyInScreen(TextField buyBackInput) {
    TextInputDialog buyBackBox = new TextInputDialog();
    buyBackBox.setHeaderText(
        BUY_IN_HEADER);

    buyBackInput.setPromptText(BUY_IN_TEXT);
    buyBackInput.setId(BUY_IN_ID);

    GridPane grid = new GridPane();
    grid.setId(BUY_BACK_PANE);

    GridPane.setConstraints(buyBackInput, 0, 1);
    grid.getChildren().add(buyBackInput);
    buyBackBox.getDialogPane().setContent(grid);
    setDialogOnTop(buyBackBox);

    return buyBackBox;
  }


  public void makeEndRoundScreen(EventHandler<ActionEvent> nextRoundEvent,
      EventHandler<ActionEvent> cashOutEvent) {
    HBox nextRoundBox = new HBox();
    nextRoundBox.getStyleClass().add(HBOX);
    Button nextRoundButton = makeButton(DEAL_NEXT_ROUND_BUTTON_TEXT, nextRoundEvent);
    Button cashOutButton = makeButton(CASH_OUT_BUTTON_TEXT, cashOutEvent);
    nextRoundBox.getChildren().addAll(nextRoundButton, cashOutButton);
    bottomGroup.getChildren().add(nextRoundBox);
  }

  public void makeActionLog() {
    actionLog = new ListView<>();
    actionLog.setId(ACTION_LOG_ID);
    actionLog.getStyleClass().add(PLAYER_HISTORY);
    actionLog.setMinHeight(300);
    actionLog.setMinWidth(600);
    HBox actionLogBox = new HBox();
    actionLogBox.getStyleClass().add(PLAYER_HISTORY_BOX);
    actionLogBox.getChildren().add(actionLog);
    rightGroup.getChildren().add(actionLogBox);
  }

  public void addToActionLog(String playerAction) {
    actionLog.getItems().add(playerAction);
  }
}
