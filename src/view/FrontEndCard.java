package view;
import javafx.scene.text.Text;
//violation? Maybe pass in the string

public class FrontEndCard extends Text {
    private String symbol;
    private String suit;

    //TODO: take the suit and symbol, and get the associated image
    public FrontEndCard(String symbol, String suit) {
        super(10, 10, symbol + "\n" + suit);
        this.setId(symbol+suit);
        this.symbol = symbol;
        this.suit = suit;
    }
}
