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
        view = new GameView();
        initializeFrontEndPlayers();
        this.stage = stage;
    }

    public Scene setupScene() {
        return view.setupScene();
    }

    //right now at each round:
        //updates the active list of players
            //from Model
        //checks the properties file for who to deal to and how many
            //from model
        //deals the cards
            //in Controller
        //betting progression
            //in controller
        //repeat



    //how to handle differences in dealing flows?
        //for stud:  deal, bet, exchange, bet
        //where are we going see whether or not do  an exchange or not?
        //probably abstract dealing rules within Controller

    //what does model need to know after Controller does work
        //what the player actions were
            //update the pot and such
            //update the active players
        //what the player's hands are after cards have been dealt
        //what the communal cards are after cards have been dealt

    public void gameStep(){
        if (roundNumber < 5){
            dealerRules.dealStats();
            dealingRound();
            dealerRules.dealFlow(roundNumber);
            roundNumber++;
        }
    }

    public void dealingRound(){
        numberOfCards = dealerRules.getNumberOfCards();
        recipient = dealerRules.getRecipient();

        for (int i=0; i<numberOfCards; i++){
            FrontEndCard topCard = getFrontEndTopCard(deck.peekTopCard());
            view.deal(topCard, recipient, xOffset, frontEndPlayers);
            xOffset += 70;
        }
    }

    //should this be in View or Controller?
    public FrontEndCard getFrontEndTopCard(Card card){
        FrontEndCard topCard = new FrontEndCard(card.getCardValue());
        return topCard;
    }


    //might be able to combine these two into one method
    //should these be in View or Controller?
    public void initializeFrontEndPlayers(){
        for (Player currentPlayer: playerList.getAllPlayers()){
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

    //with this one player version
        //model step gives the





}
