package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.FrontEndCard;


public class ControllerTest extends DukeApplicationTest {

    private Controller controller;
    private Stage stage;
    private TextField betInput;
    private FrontEndCard testCard;

    public void start(final Stage stage) {
        controller = new Controller(stage);
        stage.setScene(controller.setupScene());
        stage.show();
    }

}
