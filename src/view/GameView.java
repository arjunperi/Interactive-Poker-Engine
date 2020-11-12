package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        centerGroup.setId("Center");
        bottomGroup = new Group();
        root = new BorderPane();
        root.setCenter(centerGroup);
        root.setTop(topGroup);
        root.setBottom(bottomGroup);

        ImageView iv = new ImageView(Controller.class.getResource("/heart-suit.png").toExternalForm());
        iv.setFitHeight(50);
        iv.setFitWidth(50);
        Text inftx = new Text("K");
        Rectangle card = new Rectangle(70,100);
        //card.setStrokeType(StrokeType.INSIDE);
        card.setStroke(Color.BLACK);
        card.setFill(Color.TRANSPARENT);

        //inftx.setX(50000000);
        //inftx.setX(50);
        StackPane pane = new StackPane();
        pane.setPrefSize(70,100); //set a default size for your stackpane

        pane.getChildren().add(card);
        pane.getChildren().add(iv);
        pane.getChildren().add(inftx);


        pane.setAlignment(Pos.CENTER);
        pane.setTranslateX(100);
        pane.setTranslateY(300);

        ImageView iv2 = new ImageView(Controller.class.getResource("/heart-suit.png").toExternalForm());
        iv2.setFitHeight(50);
        iv2.setFitWidth(50);
        Text inftx2 = new Text("Q");
        Rectangle card2 = new Rectangle(70,100);
        //card.setStrokeType(StrokeType.INSIDE);
        card2.setStroke(Color.BLACK);
        card2.setFill(Color.TRANSPARENT);

        //inftx.setX(50000000);
        //inftx.setX(50);
        StackPane pane2 = new StackPane();
        //pane.setPrefSize(7000000,700000); //set a default size for your stackpane
        pane2.setPrefSize(70,100); //set a default size for your stackpane


        pane2.getChildren().add(card2);
        pane2.getChildren().add(iv2);
        pane2.getChildren().add(inftx2);


        pane2.setAlignment(Pos.CENTER);
        pane2.setTranslateX(270);
        pane2.setTranslateY(300);


        //StackPane.setAlignment(iv,Pos.CENTER); //set it to the Center Left(by default it's on the center)

        root.getChildren().add(pane2);
    }

    public Scene setupScene() {
        this.scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
                PokerRunner.BACKGROUND);
        return this.scene;
    }

    public void makeGameSelectScreen(EventHandler<ActionEvent> holdemEvent, EventHandler<ActionEvent> drawEvent, EventHandler<ActionEvent> studEvent, EventHandler<ActionEvent> customEvent){
        VBox gameBox = new VBox();
        gameBox.setId("GameBox");
        Button holdEmButton = makeButton("Holdem", holdemEvent);
        Button drawButton = makeButton("Draw", drawEvent);
        Button studButton = makeButton("Stud", studEvent);
        Button customButton = makeButton("Custom", customEvent);
        gameBox.getChildren().addAll(holdEmButton,drawButton,studButton,customButton);
        centerGroup.getChildren().add(gameBox);
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
        result.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        result.setId(property);
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }

    public ChoiceDialog makeActionScreen(EventHandler<ActionEvent> foldEvent, EventHandler<ActionEvent> checkEvent, EventHandler<ActionEvent> betEvent){
        centerGroup.getChildren().clear();
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
