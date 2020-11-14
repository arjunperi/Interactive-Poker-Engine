package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pokerSuite.PokerRunner;

import javax.swing.tree.ExpandVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GameView {
    private Scene scene;
    private BorderPane root;
    private Group topGroup;
    private Group centerGroup;
    private Group bottomGroup;
    private Button homeButton;

    public GameView(){
        root = new BorderPane();
//        initializeBorderPane();
        topGroup = new Group();
        centerGroup = new Group();
        centerGroup.setId("Center");
        bottomGroup = new Group();
        root.setCenter(centerGroup);
        root.setTop(topGroup);
        root.setBottom(bottomGroup);
    }

    public void initializeBorderPane(){
        topGroup = new Group();
        centerGroup = new Group();
        centerGroup.setId("Center");
        bottomGroup = new Group();
        root.setCenter(centerGroup);
        root.setTop(topGroup);
        root.setBottom(bottomGroup);
    }

    public Scene setupScene() {
        this.scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
                PokerRunner.BACKGROUND);
        return this.scene;
    }

    public void clear(){
        root.getChildren().clear();
        initializeBorderPane();
    }

    public Alert makeCashOutAlert(String playerName, int playerBankroll){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cash Out");
        alert.setHeaderText("CASH OUT CONFIRMATION");
        alert.setContentText(playerName + ", are you sure you want to cash out? You have $" + playerBankroll);
        return alert;
    }

    public void makeMainMenu(EventHandler<ActionEvent> gameSelectEvent, EventHandler<ActionEvent> homeEvent){
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

    public void makeGameSelectScreen(EventHandler<ActionEvent> holdemEvent, EventHandler<ActionEvent> drawEvent, EventHandler<ActionEvent> studEvent, EventHandler<ActionEvent> customEvent){
        clear();
        VBox gameBox = new VBox();
        gameBox.setId("GameBox");
        Button holdEmButton = makeButton("Holdem", holdemEvent);
        holdEmButton.setId("Holdem");
        Button drawButton = makeButton("Draw", drawEvent);
        drawButton.setId("Draw");
        Button studButton = makeButton("Stud", studEvent);
        studButton.setId("Stud");
        Button customButton = makeButton("Custom", customEvent);
        customButton.setId("Custom");
        gameBox.getChildren().addAll(holdEmButton,drawButton,studButton,customButton);
        centerGroup.getChildren().add(gameBox);
        topGroup.getChildren().add(homeButton);
    }

    public void deal(FrontEndCard card, GameDisplayRecipient displayRecipient, int xLocation) {
        topGroup.getChildren().clear();
        card.setX(xLocation);
        displayRecipient.updateFrontEndCards(card, xLocation);
        card.setY(displayRecipient.getY());
        root.getChildren().add(card);
    }

    public void remove(FrontEndCard card){
        root.getChildren().remove(card);
    }
    
    public Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }

    public ChoiceDialog makeActionScreen(String playerName, int lastBet){
        centerGroup.getChildren().clear();

        Button cashOutButton = new Button("Cash Out");
        cashOutButton.setId("CashOut");

        Button foldButton = new Button("Fold");
        foldButton.setId("Fold");

        Button checkButton = new Button("Check");
        checkButton.setId("Check");

        Button callButton = new Button("Call");
        callButton.setId("Call");

        Button betButton = new Button("Bet");
        betButton.setId("Bet");

        List<Button> choices = new ArrayList<>();
        choices.add(foldButton);
        choices.add(betButton);
        choices.add(cashOutButton);

        if (lastBet > 0){
            choices.add(callButton);
        }
        else{
            choices.add(checkButton);
        }

        ChoiceDialog<Button> dialog = new ChoiceDialog<Button>(foldButton, choices);
        dialog.setTitle("Select Action");
        dialog.setHeaderText(playerName + ", you're up! What would you like to do?");
        dialog.setContentText("Choose your action:");

        return dialog;
    }


    public Dialog makeBetPopUp(TextField input, String message) {
        bottomGroup.getChildren().clear();

        Dialog betBox = new TextInputDialog();
        betBox.setHeaderText(message);

        input.setPromptText("Enter a Bet: ");
        input.setId("Bet");

        GridPane grid = new GridPane();
        grid.setId("OptionPane");

        GridPane.setConstraints(input, 0,1);
        grid.getChildren().add(input);
        betBox.getDialogPane().setContent(grid);

        return betBox;
    }

    public GridPane getGrid(Dialog betBox){
        Node grid = betBox.getDialogPane().getContent();
            if (grid.getId().equals("OptionPane"));{
                return (GridPane) grid;
            }
    }

    public Button getButton(Dialog betBox, String buttonName){
        GridPane grid = getGrid(betBox);
        for (Node node: grid.getChildrenUnmodifiable())
            if (node.getId().equals(buttonName)){
                Button desiredButton = (Button) node;
                return desiredButton;
            }
        return null;
    }

    public Dialog makeExchangeScreen(String playerName, TextField exchangeCardInput1,TextField exchangeCardInput2, TextField exchangeCardInput3){
        Dialog exchangeBox = new TextInputDialog();
        exchangeBox.setTitle("Exchange Cards");
        exchangeBox.setHeaderText(playerName + " is up. Select Cards to Exchange");

        GridPane grid = new GridPane();
        grid.setId("ExchangeGrid");

        exchangeCardInput1.setPromptText("First card to exchange");
        exchangeCardInput1.setId("ExchangeCard1");
        GridPane.setConstraints(exchangeCardInput1, 0,0);

        exchangeCardInput2.setPromptText("Second card to exchange");
        exchangeCardInput2.setId("ExchangeCard2");
        GridPane.setConstraints(exchangeCardInput2, 0,1);

        exchangeCardInput3.setPromptText("Third card to exchange");
        exchangeCardInput3.setId("ExchangeCard3");
        GridPane.setConstraints(exchangeCardInput3, 0,2);

        grid.getChildren().addAll(exchangeCardInput1,exchangeCardInput2,exchangeCardInput3);
        exchangeBox.getDialogPane().setContent(grid);
        return exchangeBox;
    }
}
