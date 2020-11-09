package view;
import javafx.scene.text.Text;
//violation? Maybe pass in the string
import model.Suit;

public class FrontEndCard extends Text {
    private String symbol;
    private Suit suit;
    private boolean isVisible;


    //TODO: take the suit, symbol, and visiblity, and get the associated image
    public FrontEndCard(String symbol, Suit suit, boolean isVisible) {
        super(10, 10, "");
        this.isVisible = isVisible;
        this.setText(symbol + "\n" + suit + "\n" + getVisibilityDisplay());
        this.setId(symbol+suit.toString());
        this.symbol = symbol;
        this.suit = suit;
    }

    private String getVisibilityDisplay(){
        if (isVisible){
            return "VISIBLE";
        }
        return "NON VISIBLE";
    }

}
