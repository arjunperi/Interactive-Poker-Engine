package view;
import javafx.scene.text.Text;
//violation? Maybe pass in the string
import model.Suit;

public class FrontEndCard extends Text {
    private String symbol;
    private Suit suit;

    //TODO: take the suit and symbol, and get the associated image
    public FrontEndCard(String symbol, Suit suit) {
        super(10, 10, symbol + "\n" + suit);
        this.setId(symbol);
        this.symbol = symbol;
        this.suit = suit;
    }
}
