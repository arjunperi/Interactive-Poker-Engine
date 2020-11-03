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
    public void testDealToCommunity(){
        gameView = new GameView();
        FrontEndCard frontEndQueen = new FrontEndCard("Q", Suit.CLUBS);
        String recipient = "Community";
        int xOffset = 50;
        GameDisplayRecipient player = new GameDisplayRecipient(10,10);
        List<GameDisplayRecipient> frontEndPlayers = List.of(player);
        gameView.deal(frontEndQueen,recipient,xOffset,frontEndPlayers);
        assertEquals(frontEndQueen.getX(), 100);
    }

    @Test
    public void testDealToPlayer(){
        gameView = new GameView();
        FrontEndCard frontEndQueen = new FrontEndCard("Q", Suit.CLUBS);
        String recipient = "Players";
        int xOffset = 50;
        GameDisplayRecipient player = new GameDisplayRecipient(10,10);
        List<GameDisplayRecipient> frontEndPlayers = List.of(player);
        gameView.deal(frontEndQueen,recipient,xOffset,frontEndPlayers);
        assertEquals(frontEndQueen.getX(), 60);
    }
}
