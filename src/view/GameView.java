package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pokerSuite.PokerRunner;


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

    public Dialog makeOptionScreen(TextField betInput, EventHandler<ActionEvent> foldEvent) {
        bottomGroup.getChildren().clear();

        Dialog betBox = new TextInputDialog();
//        Button button = new Button("Fold");
//        button.setId("Fold");

        GridPane grid = new GridPane();
        grid.setId("OptionPane");
        betInput.setPromptText("Enter a bet");
        betInput.setId("Bet");
        GridPane.setConstraints(betInput, 0,2);
        grid.getChildren().add(betInput);
        betBox.getDialogPane().setContent(grid);


//        grid.getChildren().add(foldButton);
//        topGroup.getChildren().add(grid);
        return betBox;
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
