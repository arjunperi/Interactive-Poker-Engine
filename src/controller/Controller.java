package controller;


import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.*;
import view.*;


import java.awt.desktop.ScreenSleepEvent;
import java.util.*;

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
    private GameDisplayRecipient community;
    private Pot pot;

    private GameView view;
    private Stage stage;

    private int roundNumber;
    private int numberOfCards;
    private String recipient;

    private int xOffset;

    private Stack<Card> cardsRemoved;
    private Map<Player, FrontEndPlayer> playerMappings;

    private boolean gamePaused = false;

    private Timeline animation;

    private int playerIndex;

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
        pot = game.getPot();
        turnManager = game.getTurnManager();
        cardsRemoved = new Stack<>();
        view = new GameView();
        playerMappings = new HashMap<>();
        initializeFrontEndPlayers();
        initializeCommunity();

        this.stage = stage;


        initializeSplashMenu();

    }
    public Scene setupScene() {
        return view.setupScene();
    }

    public void initializeSplashMenu(){
        EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                dealingRound();
                initializeBettingMenu();
            }
        };
        view.createStartScreen(startEvent);
    }



    //TODO: maintain player that raised last
    public void initializeBettingMenu(){
        playerList.updateActivePlayers();
        for (Player player : playerList.getPlayers()) {
            EventHandler<ActionEvent> foldEvent = e -> indicateFold(player);

            TextField betInput = new TextField();
            Dialog betBox = view.makeOptionScreen(betInput);
            Optional<ButtonType> betBoxResult = betBox.showAndWait();
            if (betBoxResult.isPresent()) {
                indicateBet(player,betInput.getText());
            }
            turnManager.checkOnePlayerRemains(playerList.getPlayers());
        }
        turnManager.checkShowDown(playerList.getPlayers(),roundNumber,5);
        if (roundNumber < 5){
            dealingRound();
        }
    }


    //don't like this conditional
    private void dealingRound(){
        dealerRules.dealStats(roundNumber);
        recipient = dealerRules.getRecipient();
        if (recipient.equals("Community")){
            dealFrontEndCards(community);
        }
        else {
            for (GameDisplayRecipient player : frontEndPlayers){
                dealFrontEndCards(player);
            }
        }
        for (Card card: cardsRemoved){
            deck.replaceTopCard(card);
        }
        cardsRemoved.clear();
        dealerRules.dealFlow(roundNumber);
        roundNumber++;
        initializeBettingMenu();
    }

    private void dealFrontEndCards(GameDisplayRecipient recipient){
        numberOfCards = dealerRules.getNumberOfCards();
        for (int i=0; i<numberOfCards; i++){
            Card backendTopCard = deck.getTopCard();
            FrontEndCard topCard = getFrontEndTopCard(backendTopCard);
            cardsRemoved.add(backendTopCard);
            view.deal(topCard, recipient, xOffset);
            xOffset += 70;
        }
    }

    //should this be in View or Controller?
    private FrontEndCard getFrontEndTopCard(Card card){
        FrontEndCard topCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit());
        return topCard;
    }

    private void initializeCommunity(){
        community = new FrontEndCommunity(50,50);
    }


    private void initializeFrontEndPlayers(){
        int offset = 20;
        for (Player currentPlayer: playerList.getPlayers()){
            FrontEndPlayer newPlayer = new FrontEndPlayer(offset, offset, currentPlayer.toString(), currentPlayer.getBankroll());
            playerMappings.put(currentPlayer, newPlayer);
            frontEndPlayers.add(newPlayer);
        }
    }


    public void indicateFold(Player player){
        player.exitHand();
        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.foldDisplay();
    }

    public void indicateBet(Player player, String betInput){
        int betAmount = Integer.parseInt(betInput);
        pot.addToPot(betAmount);
        player.updateBankroll(betAmount * -1);

        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.betDisplay(betAmount * -1);
    }
}
