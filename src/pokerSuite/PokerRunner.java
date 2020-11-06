package pokerSuite;

import controller.Controller;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Card;
import model.Hand;
import model.HandEvaluator;
import model.Suit;

public class PokerRunner extends Application {

    public static final String TITLE = "Poker";
    public static final Paint BACKGROUND = Color.AZURE;
    public static final double FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double SCENE_WIDTH = 800;
    public static final double SCENE_HEIGHT = 800;
    private Controller mainController;

    @Override
    public void start(final Stage stage) {
        mainController = new Controller(stage);
        stage.setScene(mainController.setupScene());
        stage.setTitle(TITLE);
        stage.show();
    }


    public static void main (String[] args) {
        launch(args);
        HandEvaluator evaluator = new HandEvaluator();
        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(2,Suit.CLUBS);
        Card card3 = new Card(3,Suit.CLUBS);
        Card card4 = new Card(4,Suit.CLUBS);
        Card card5 = new Card(5,Suit.CLUBS);
        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1 = hand1.sortHand();
        int[] ret = evaluator.handStrength(hand1);
        for(int num : ret){
            System.out.print(num + " ");
        }
    }
}
