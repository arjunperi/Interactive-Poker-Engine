package pokerSuite;

import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PokerRunner extends Application {

    public static final String TITLE = "Poker";
    public static final Paint BACKGROUND = Color.AZURE;
    public static final double FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double SCENE_WIDTH = 800;
    public static final double SCENE_HEIGHT = 800;
    private Controller mainController;

    private Timeline animation;


    @Override
    public void start(final Stage stage) {
//        initializeAnimation();
//        mainController = new Controller(stage, animation);
        mainController = new Controller(stage);
        stage.setScene(mainController.setupScene());
        stage.setTitle(TITLE);
        stage.show();
    }

//    private void initializeAnimation() {
//        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> gameStep());
////        Timeline animation = new Timeline();
//        animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
//    }
//
//    private void gameStep() {
//        mainController.gameStep();
//    }


    public static void main (String[] args) {
        launch(args);
    }
}
