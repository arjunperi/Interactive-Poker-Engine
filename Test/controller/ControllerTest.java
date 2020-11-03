package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.stage.Stage;
import model.Dealer;
import model.DealerRules;
import model.Game;
import model.TurnManager;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;



public class ControllerTest extends DukeApplicationTest {

    private Controller mainController;
    private DealerRules dealerRules;
    private TurnManager turnManager;
    private Game game;

    public void start(final Stage stage) {
        mainController = new Controller(stage);

    }


    @Test
    public void testGameStep(){
        dealerRules = game.getDealerRules();
        turnManager = game.getTurnManager();
        mainController.gameStep();
//        assertEquals()
    }

    @Test
    public void testDealingRound(){

    }

    @Test
    public void testGetFrontEndTopCard(){}

    @Test
    public void initializeFrontEndPlayers(){

    }

}
