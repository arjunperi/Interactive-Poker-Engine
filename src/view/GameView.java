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
    private GameDisplayRecipient community;


    public GameView(){
        topGroup = new Group();
        centerGroup = new Group();
        bottomGroup = new Group();
        root = new BorderPane();
        root.setCenter(centerGroup);
        root.setTop(topGroup);
        root.setBottom(bottomGroup);
        community = new GameDisplayRecipient(50,50);
    }

    public Scene setupScene() {
        this.scene = new Scene(root, PokerRunner.SCENE_WIDTH, PokerRunner.SCENE_HEIGHT,
                PokerRunner.BACKGROUND);
        return this.scene;
    }

    public void deal(FrontEndCard card, String recipient, int xOffset, List<GameDisplayRecipient> frontEndPlayers){
        if (recipient.equals("Community")){
            card.setX(community.getX() + xOffset);
            System.out.println("\n" + card.getX());
            centerGroup.getChildren().add(card);
        }
        else{
            for (GameDisplayRecipient player: frontEndPlayers){
                card.setX(player.getX() + xOffset);
                bottomGroup.getChildren().add(card);
            }
        }
    }
}
