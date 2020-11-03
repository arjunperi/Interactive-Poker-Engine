package view;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Game;
import model.Suit;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;

public class GameViewTest extends DukeApplicationTest {
    private GameView gameView;


    @Test
    public void testDealToGameDisplayRecipient(){
        gameView = new GameView();
        FrontEndCard frontEndQueen = new FrontEndCard("Q", Suit.CLUBS);
        GameDisplayRecipient recipient = new GameDisplayRecipient(10,10);
        int xOffset = 50;
        gameView.deal(frontEndQueen, recipient, xOffset);
        assertEquals(60, frontEndQueen.getX());
    }

}
