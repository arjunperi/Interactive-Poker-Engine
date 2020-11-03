package view;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Game;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameDisplayRecipientTest extends DukeApplicationTest {

    @Test
    public void testGameDisplayRecipientCreation(){
        GameDisplayRecipient player = new GameDisplayRecipient(10,10);
        assertEquals(player.getX(), 10);
    }



}
