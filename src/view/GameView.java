package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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

    public void promptAction(GameDisplayRecipient player){
        //pop up an option box
        //say the player chooses bet
            //player's displayed total decreases by bet amount
            //pot total increases by bet amount

    }

}
