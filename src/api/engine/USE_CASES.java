package api.engine;

import api.engine.DummyPlayer;

public class USE_CASES {
    /*
    Use Case Example 1 - The user folds
     */
    TurnManager turnManager = new TurnManager();
    DummyPlayer player = new DummyPlayer();
    turnManager.getNextPlayer();
    player.Fold();

    /*
    Use Case Example 2 - User calls current bet
     */
    Pot pot = new Pot();
    turnManager.getNextPlayer();
    currentBet = pot.getCurrentBet();
    player.Bet(currentBet);

    /*
    Use Case Example 3 - User checks
     */

    turnManager.getNextPlayer();
    player.Check();

    /*
    Use Case Example 4
     */
}

