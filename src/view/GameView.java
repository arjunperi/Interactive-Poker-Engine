package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
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

        /*ImageView iv = new ImageView(Controller.class.getResource("/heart-suit.png").toExternalForm());
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

        ImageView iv3 = new ImageView(Controller.class.getResource("/heart-suit.png").toExternalForm());

        iv3.setFitHeight(50);
        iv3.setFitWidth(50);
        Text inftx2 = new Text("Q");
        //Rectangle card2 = new Rectangle(70,100);
        //card.setStrokeType(StrokeType.INSIDE);
        //card2.setStroke(Color.BLACK);
        //card2.setFill(Color.TRANSPARENT);

        //inftx.setX(50000000);
        //inftx.setX(50);
        StackPane pane2 = new StackPane();
        //pane.setPrefSize(7000000,700000); //set a default size for your stackpane
        pane2.setPrefSize(70,100); //set a default size for your stackpane


        //pane2.getChildren().add(card2);

        pane2.getChildren().add(iv3);
        pane2.getChildren().add(inftx2);


        pane2.setAlignment(Pos.CENTER);
        pane2.setTranslateX(270);
        pane2.setTranslateY(300);


        //StackPane.setAlignment(iv,Pos.CENTER); //set it to the Center Left(by default it's on the center)

        root.getChildren().add(pane2);*/

        Pane pane3 = new Pane();
        /*GridPane pane4 = new GridPane();
        pane4.setGridLinesVisible(true);
        pane4.setBorder(new Border(new BorderStroke(Color.RED,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane4.setMinSize(400, 200);
        pane4.add(new Circle(10), 1,1);
        pane4.setPadding(new Insets(10, 10, 10, 10));
        centerGroup.getChildren().add(pane4);*/




        double centerX = 300 ;
        double centerY = 300 ;

        Circle earth = new Circle(centerX, centerY, 150, Color.web("green", 0.5));
        pane3.getChildren().add(earth);
        //pane3.add(new Circle(2), (int)centerX, (int)centerY);

        int numPlayers = 8;
        double distance = 300 ;
        List<String> names = List.of("Arjun", "Noah", "Yasser", "Christian", "Duvall", "Luke Skywalker", "Harry Potter", "Voldemort", "bool");

        for (int i = 0 ; i < numPlayers; i++) {
            double angle = 2 * i * Math.PI / numPlayers ;
            double xOffset = distance * Math.cos(angle);
            double yOffset = distance * Math.sin(angle);
            double x = centerX + xOffset;
            double y = centerY + yOffset;

            Circle calculatedPosition = new Circle(x, y, 5, Color.web("blue", 0.5));
            x -= 100;


            /*if (x - centerX < 0) {
                x -= 200;
            }*/
            /*if (y - centerY > 0) {
                y += 60;
            }
            else {
                y -= 60;
            }*/

            GridPane cardSpots = new GridPane();
            cardSpots.setVgap(5);
            cardSpots.setHgap(5);
            cardSpots.setLayoutX(x);
            cardSpots.setLayoutY(y-110);
            //cardSpots.setGridLinesVisible(true);
            //Rectangle card = new Rectangle(35, 50);
            Rectangle card2 = new Rectangle(35, 50);
            Rectangle card3 = new Rectangle(35, 50);
            Rectangle card4 = new Rectangle(35, 50);
            Rectangle card5 = new Rectangle(35, 50);
            Rectangle card6 = new Rectangle(35, 50);
            Rectangle card7 = new Rectangle(35, 50);
            Rectangle card8 = new Rectangle(35, 50);
            Rectangle card9 = new Rectangle(35, 50);
            Rectangle card10 = new Rectangle(35, 50);

            card2.setStroke(Color.RED);
            card3.setStroke(Color.RED);
            card4.setStroke(Color.RED);
            card5.setStroke(Color.RED);
            card6.setStroke(Color.RED);
            card7.setStroke(Color.RED);
            card8.setStroke(Color.RED);
            card9.setStroke(Color.RED);
            card10.setStroke(Color.RED);



            card2.setFill(Color.TRANSPARENT);
            card3.setFill(Color.TRANSPARENT);
            card4.setFill(Color.TRANSPARENT);
            card5.setFill(Color.TRANSPARENT);
            card6.setFill(Color.TRANSPARENT);
            card7.setFill(Color.TRANSPARENT);
            card8.setFill(Color.TRANSPARENT);
            card9.setFill(Color.TRANSPARENT);
            card10.setFill(Color.TRANSPARENT);


            /*for (int startRow)
            StackPane info = new StackPane();
            Text t = new Text("yuh");
            info.getChildren().addAll(card, t);*/

            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 5; column++) {
                    Rectangle card = new Rectangle(35, 50);
                    card.setStroke(Color.TRANSPARENT);
                    card.setFill(Color.TRANSPARENT);
                    cardSpots.add(card, column, row);
                }
            }

            int row = 1;
            int col = 0;

            StackPane info = new StackPane();
            List<String> currentCards = List.of("K", "Q", "A", "2", "3", "5");
            for (int c = 0; c < currentCards.size(); c++) {
                if (col == 5) {
                    col = 0;
                    row = 0;
                }
                info = new StackPane();
                //info.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                Text t = new Text(currentCards.get(c));
                t.setVisible(true);
                ImageView iv3 = new ImageView();
                iv3.setImage(new Image(Controller.class.getResource("/card-back.png").toExternalForm()));
                iv3.setFitHeight(50);
                iv3.setFitWidth(35);
                Rectangle card = new Rectangle(35, 50);
                card.setStroke(Color.BLUE);
                card.setFill(Color.TRANSPARENT);
                info.getChildren().addAll(iv3,t,card);
                cardSpots.add(info, col, row);
                //cardSpots.getChildren().remove()
                col++;
            }
            cardSpots.getChildren().remove(info);





            /*cardSpots.add(card, 0,0);
            cardSpots.add(info, 1,0);

            cardSpots.add(card2, 1,0);
            cardSpots.add(card3, 2,0);
            cardSpots.add(card4, 3,0);
            cardSpots.add(card5, 4,0);
            cardSpots.add(card6, 0,1);
            cardSpots.add(card7, 1,1);
            cardSpots.add(card8, 2,1);

            cardSpots.add(card9, 3,1);
            cardSpots.add(card10, 4,1);*/





            Rectangle playerBox = new Rectangle(x, y, 200, 30);
            //Rectangle playerBox = new Rectangle(175, 30);
            playerBox.setStroke(Color.RED);
            playerBox.setFill(Color.TRANSPARENT);
            //Circle cardLocation = new Circle(radius, Color.web("blue", 0.5));
            //Text name = new Text(names.get(i));
            //pane3.add(playerBox, x, y);
            //pane3.add(cardLocation, x, y);
            //pane3.add(name, x, y + 2);

            //Circle cardLocation = new Circle(x, y, radius, Color.web("blue", 0.5));
            Text name = new Text(x, y+20, names.get(i));

            pane3.getChildren().addAll(playerBox, name, cardSpots, calculatedPosition);
            //pane3.getChildren().addAll(playerInfo);

        }
        centerGroup.getChildren().add(pane3);
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
        centerGroup.getChildren().remove(gameBox);
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
