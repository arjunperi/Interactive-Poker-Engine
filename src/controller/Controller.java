package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import view.*;


import java.util.*;

public class Controller {

    private static final String ENGLISH = "English";

    private static final String RESOURCES = "Resources/";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    private ResourceBundle projectTextResources;

    private Game game;
    private Model model;
    private TurnManager turnManager;
    private Deck deck;
    private PlayerList playerList;
    private List<GameDisplayRecipient> frontEndPlayers;
    private CommunityCards communityCards;
    private GameDisplayRecipient displayCommunity;
    private Pot pot;

    private GameView view;
    private Stage stage;

    private int roundNumber;
    private int numberOfCards;
    private String recipient;

    private int xOffset;

    private Stack<Card> cardsRemoved;
    private Map<Player, FrontEndPlayer> playerMappings;

    public Controller(Stage stage) {
        roundNumber = 1;
        xOffset = 0;
        game = new Game();
        model = game.getDealerRules();
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
                model.dealFlow(roundNumber);
                dealingRound();
            }
        };
        view.createStartScreen(startEvent);
    }

    //TODO: maintain player that raised last
    public void initializeBettingMenu(){
        playerList.updateActivePlayers();
        for (Player player : playerList.getActivePlayers()) {
            EventHandler<ActionEvent> foldEvent = e -> indicateFold(player);

            TextField betInput = new TextField();
            Dialog betBox = view.makeOptionScreen(betInput);
            Optional<ButtonType> betBoxResult = betBox.showAndWait();
            if (betBoxResult.isPresent()) {
                indicateBet(player,betInput.getText());
            }
            turnManager.checkOnePlayerRemains(playerList.getActivePlayers());
        }
        turnManager.checkShowDown(playerList.getActivePlayers(),roundNumber,5);
        if (roundNumber < 5){
            model.dealFlow(roundNumber);
            dealingRound();
        }
    }

    //don't like this conditional
    private void dealingRound(){
        recipient = model.getRecipient();
        if (recipient.equals("Community")){
            dealFrontEndCards(communityCards, displayCommunity);
        }
        else {
            playerList.updateActivePlayers();
            for (Player player : playerList.getActivePlayers()){
                dealFrontEndCards(player,playerMappings.get(player));
            }
        }
        roundNumber++;
        initializeBettingMenu();
    }

    private void dealFrontEndCards(CardRecipient recipient, GameDisplayRecipient displayRecipient){
        numberOfCards = model.getNumberOfCards();
        for (Card newCard: recipient.getNewCards()){
            FrontEndCard displayCard = getFrontEndTopCard(newCard);
            view.deal(displayCard, displayRecipient, xOffset);
            xOffset += 70;
        }
    }

    //should this be in View or Controller?
    private FrontEndCard getFrontEndTopCard(Card card){
        FrontEndCard topCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit());
        return topCard;
    }

    private void initializeCommunity(){
        displayCommunity = new FrontEndCommunity(50,50);
    }


    private void initializeFrontEndPlayers(){
        int offset = 20;
        for (Player currentPlayer: playerList.getActivePlayers()){
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
