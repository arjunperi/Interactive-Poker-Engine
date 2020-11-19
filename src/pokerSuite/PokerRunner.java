package pokerSuite;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class PokerRunner extends Application {

  public static final String TITLE = "Poker";
  public static final Paint BACKGROUND = Color.AZURE;
  public static final double SCENE_WIDTH = 800;
  public static final double SCENE_HEIGHT = 800;

  @Override
  public void start(final Stage stage) {
    Controller mainController = new Controller();
    stage.setScene(mainController.setupScene());
    stage.setTitle(TITLE);
    stage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
