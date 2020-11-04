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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import model.Game;
import pokerSuite.PokerRunner;

import java.util.List;

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

    //want a way for this to specify top or bottom group based on the recipient
    public void deal(FrontEndCard card, GameDisplayRecipient displayRecipient, int xOffset) {
        card.setX(displayRecipient.getX() + xOffset);
        System.out.println("\n" + card.getX());
        centerGroup.getChildren().add(card);
    }


    public Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setId(property);
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }


    public Dialog makeOptionScreen(TextField betInput, EventHandler<ActionEvent> foldEvent){
        Dialog betBox = new TextInputDialog();
        betInput.setId("betBox");
        GridPane grid = new GridPane();
        betInput.setPromptText("Enter Bet Amount");
        GridPane.setConstraints(betInput, 0, 0);
        grid.getChildren().add(betInput);
        betBox.getDialogPane().setContent(grid);

        Button foldButton = makeButton("fold", foldEvent);
        grid.getChildren().add(foldButton);
        topGroup.getChildren().add(grid);
        return betBox;
    }
}
