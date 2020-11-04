package controller;


import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
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

    private boolean pauseGame = false;

    private Timeline animation;

    public Controller(Stage stage, Timeline animation) {
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

        this.animation = animation;
    }

    public Scene setupScene() {
        return view.setupScene();
    }

    public void gameStep(){
        if (roundNumber < 5) {
            dealerRules.dealStats(roundNumber);
            dealingRound();
            dealerRules.dealFlow(roundNumber);
//            bettingRound();
            roundNumber++;
        }
    }

    //don't like this conditional
    private void dealingRound(){
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
    }

    private void dealFrontEndCards(GameDisplayRecipient recipient){
        numberOfCards = dealerRules.getNumberOfCards();
        for (int i=0; i<numberOfCards; i++){
            Card backendTopCard = deck.getTopCard();
            System.out.println("backend rank:" + backendTopCard.getRank());
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

    //TODO: Fix the issues with pausing the game to allow for user inputs, then resuming game

//    private void bettingRound(){
//        //need to pause and allow for each player to put in inputs
//        playerList.updateActivePlayers();
//        for (Player player : playerList.getPlayers()){
//            EventHandler<ActionEvent> foldEvent = e -> indicateFold(player);
//            TextField betInput = new TextField();
//            Dialog betBox = view.makeOptionScreen(betInput, foldEvent);
//            Optional<ButtonType> betBoxResult = betBox.showAndWait();
//            if (betBoxResult.isPresent()) {
//                indicateBet(player,betInput.getText());
//            }
//            turnManager.checkGameOver(playerList.getPlayers(),5);
//        }
//    }
//
//    public void indicateFold(Player player){
//        player.exitHand();
//
//        FrontEndPlayer displayPlayer = playerMappings.get(player);
//        displayPlayer.foldDisplay();
//        //resume
//    }
//
//    public void indicateBet(Player player, String betInput){
//        int betAmount = Integer.parseInt(betInput);
//        pot.addToPot(betAmount);
//        player.updateBankroll(betAmount * -1);
//
//        FrontEndPlayer displayPlayer = playerMappings.get(player);
//        displayPlayer.betDisplay(betAmount * -1);
//        //resume
//    }
}
