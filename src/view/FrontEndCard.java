package view;
import javafx.scene.text.Text;
//violation? Maybe pass in the string

public class FrontEndCard extends Text {
    private String symbol;
    private String suit;
    private boolean isVisible;

    //TODO: take the suit and symbol, and get the associated image
    public FrontEndCard(String symbol, String suit, boolean isVisible) {
        //if backend vis -> front end vis

        super(10, 10, "");
        this.setId(symbol + suit);
        this.isVisible = isVisible;
        this.setText(symbol + "\n" + suit + "\n" + getVisibilityDisplay());
        this.symbol = symbol;
        this.suit = suit;
    }

    private String getVisibilityDisplay() {
        if (isVisible){
            return "VISIBLE";
        }
        return "NON VISIBLE";
    }
}
