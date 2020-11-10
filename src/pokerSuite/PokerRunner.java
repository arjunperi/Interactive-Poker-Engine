package pokerSuite;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

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
    }
}
