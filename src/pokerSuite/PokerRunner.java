package pokerSuite;

import controller.Controller;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

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


    public static void main(String[] args) {
        launch(args);
        HandEvaluator evaluator = new HandEvaluator();
        HandCombiner comb = new HandCombiner();
        Card card1 = new Card(14, Suit.CLUBS);
        Card card2 = new Card(14, Suit.DIAMONDS);
        Card card3 = new Card(3, Suit.CLUBS);
        Card card4 = new Card(9, Suit.CLUBS);
        Card card5 = new Card(9, Suit.DIAMONDS);
        Card card6 = new Card(14, Suit.SPADES);
        Card card7 = new Card(14, Suit.HEARTS);

        Hand hand1 = new Hand();
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);
        hand1.add(card6);
        hand1.add(card7);
        hand1 = hand1.sortHand();

        int n = hand1.getHandSize();
        int r = 5;
        // A temporary array to store all combination one by one
        Card data[] = new Card[r];

        // Print all combination using temprary array 'data[]'
        ArrayList<Hand> hands = comb.combinationUtil(hand1, data, 0, n - 1, 0);
        System.out.print(hands.size() + " ");

        for (Hand hand : hands) {
            for (Card card : hand.getCards()) {

                System.out.print(card.getRank() + " ");
            }
        }
        ArrayList<Hand> bestHands = evaluator.getBestHands(hands);

        }
    }

