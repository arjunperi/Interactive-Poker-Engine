package controller;


import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.FrontEndCard;
import view.GameDisplayRecipient;
import view.GameView;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller {

    private static final String ENGLISH = "English";

    private static final String RESOURCES = "Resources/";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    private ResourceBundle projectTextResources;

    private Game game;
    private DealerRules dealerRules;
    private TurnManager turnManager;
    private Deck deck;
    private PlayerList playerList;
    private List<GameDisplayRecipient> frontEndPlayers;
    private CommunityCards communityCards;

    private GameView view;
    private Stage stage;

    private int roundNumber;
    private int numberOfCards;
    private String recipient;

    private int xOffset;
    private int xIndex;

    public Controller(Stage stage) {
        roundNumber = 1;
        xOffset = 0;
        game = new Game();
        dealerRules = game.getDealerRules();
        turnManager = game.getTurnManager();
        deck = game.getDeck();
        playerList = game.getPlayers();
        frontEndPlayers = new ArrayList<>();
        communityCards = game.getCommunityCards();
        view = new GameView();
        initializeFrontEndPlayers();
        this.stage = stage;
    }

    public Scene setupScene() {
        return view.setupScene();
    }

    public void gameStep(){
        if (roundNumber < 5) {
            dealerRules.dealStats(roundNumber);
            dealingRound();
            dealerRules.dealFlow(roundNumber);
            roundNumber++;
        }
    }

    public void dealingRound(){
        numberOfCards = dealerRules.getNumberOfCards();
        recipient = dealerRules.getRecipient();
        Stack<Card> cardsRemoved = new Stack();

        for (int i=0; i<numberOfCards; i++){
            Card backendTopCard = deck.getTopCard();
            FrontEndCard topCard = getFrontEndTopCard(backendTopCard);
            cardsRemoved.add(backendTopCard);
            view.deal(topCard, recipient, xOffset, frontEndPlayers);
            xOffset += 70;
        }
        for (Card card: cardsRemoved){
            deck.replaceTopCard(card);
        }
    }


    //should this be in View or Controller?
    public FrontEndCard getFrontEndTopCard(Card card){
        FrontEndCard topCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit());
        return topCard;
    }


    //might be able to combine these two into one method
    //should these be in View or Controller?
    public void initializeFrontEndPlayers(){
        for (Player currentPlayer: playerList.getAllPlayers()){
            System.out.println(currentPlayer.toString());
            GameDisplayRecipient player = new GameDisplayRecipient(100,100);
            frontEndPlayers.add(player);
        }
    }

    public void updateFrontEndPlayers(){
        frontEndPlayers.clear();
        for (Player activePlayer : playerList.updateActivePlayers()){
            GameDisplayRecipient player = new GameDisplayRecipient(100,100);
            frontEndPlayers.add(player);
        }
    }


    public void bettingRound(){
        //go through all the players and ask for an action
    }

    public void promptAction(){
        //once action is selected, update the totals and then call
    }

}
