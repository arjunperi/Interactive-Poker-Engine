package view;
import javafx.scene.text.Text;

public class FrontEndCard extends Text {

    public FrontEndCard(int value) {
        super(10, 10, String.valueOf(value));
    }
}
