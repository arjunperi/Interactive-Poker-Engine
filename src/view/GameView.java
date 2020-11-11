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
import pokerSuite.PokerRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GameView {
    private Scene scene;
    private BorderPane root;
    private Group topGroup;
    private Group centerGroup;
    private Group bottomGroup;



    public GameView(){
        topGroup = new Group();
        centerGroup = new Group();
        bottomGroup = new Group();
        bottomGroup.setId("Bottom");
        root = new BorderPane();
        root.setCenter(centerGroup);
        root.setTop(topGroup);
        root.setBottom(bottomGroup);
    }

    public Scene setupScene() {
        this.scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
                PokerRunner.BACKGROUND);
        return this.scene;
    }

    public void createStartScreen(EventHandler<ActionEvent> startEvent){
        Button startButton = makeButton("Start", startEvent);
        bottomGroup.getChildren().add(startButton);
    }

    public void deal(FrontEndCard card, GameDisplayRecipient displayRecipient, int xLocation) {
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
        result.setId(property);
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }

    public ChoiceDialog makeActionScreen(EventHandler<ActionEvent> foldEvent, EventHandler<ActionEvent> checkEvent, EventHandler<ActionEvent> betEvent){
        Button foldButton = makeButton("Fold", foldEvent);
        foldButton.setId("Fold");
        Button checkButton = makeButton("Check", checkEvent);
        checkButton.setId("Check");
        Button betButton = makeButton("Bet", betEvent);
        List<Button> choices = new ArrayList<>();

        choices.add(foldButton);
        choices.add(checkButton);
        choices.add(betButton);

        ChoiceDialog<Button> dialog = new ChoiceDialog<Button>(checkButton, choices);
        dialog.setTitle("Select Action");
        dialog.setHeaderText("What would you like to do?");
        dialog.setContentText("Choose your action:");

// Traditional way to get the response value.

        return dialog;
    }


    public Dialog makeOptionScreen(TextField betInput) {
        bottomGroup.getChildren().clear();

        Dialog betBox = new TextInputDialog();



        betInput.setPromptText("Enter a bet");
        betInput.setId("Bet");


        GridPane grid = new GridPane();
        grid.setId("OptionPane");
        GridPane.setConstraints(betInput, 0,1);
        grid.getChildren().add(betInput);
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

    public Dialog makeExchangeScreen(TextField exchangeCardInput1,TextField exchangeCardInput2, TextField exchangeCardInput3){
        Dialog exchangeBox = new TextInputDialog();
        GridPane grid = new GridPane();

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
