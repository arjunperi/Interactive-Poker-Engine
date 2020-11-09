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

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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
    private String recipient;



    private Stack<Card> cardsRemoved;
    private Map<Player, FrontEndPlayer> playerMappings;
    private Map<String, FrontEndCard> frontEndCardMapppings;

    //TODO: Use reflection to see what kind of model we want to create
    public Controller(Stage stage) {
        roundNumber = 1;
        game = new Game();

        model = game.getModel();

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

        frontEndCardMapppings = new HashMap<>();
        initializeFrontEndPlayers();
        initializeCommunity();

        this.stage = stage;

        initializeSplashMenu();

    }
    public Scene setupScene() {
        return view.setupScene();
    }

    //if community
        //deal backend
        //deal frontend
        //bet frontend
        //etx
        //CURRENT FLOW:
            //Start button
            //dealFlow()
            //dealingRound()
            //bettingMenu()

    //if draw
        //deal backend
        //deal frontend
        //bet frontend
        //exchange backend
        //exchange frontend
        //bet
        //NECESSARY FLOW:
            //Start button
            //dealFlow()
            //dealingRound()
            //bettingMenu()
            //exchange (backend)
            //exchange (frontend)

    //what is really an exchange on the frontend
        //for a player -> take their cards rn, remove ones that are being exchanged, deal new cards
        //still called dealing round, but we need to know to remove

    //if stud
        //deal backend
            //2 face up one face down
        //deal frontend
        //bet frontend
        //etc
            //visibility is variable
                //that will happen on backend, and frontend will simply read if the card dealt needs to be visible or not
            //betting order based on strength of face up cards
                //need to have backend logic that tells the frontend who to bet to
                    //for all games -> rather than just looping through active players, loop through an ordered list of players


    public void initializeSplashMenu(){
        EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                model.dealFlow(roundNumber);
                nextAction(model.getAction(roundNumber));
            }
        };
        view.createStartScreen(startEvent);
    }

    //TODO: maintain player that raised last
    //once the order is read in from the backend, this should be the same
//    private void initializeBettingMenu(){
//        playerList.updateActivePlayers();
//        for (Player player : playerList.getActivePlayers()) {
//            EventHandler<ActionEvent> foldEvent = e -> indicateFold(player);
//
//            TextField betInput = new TextField();
//            Dialog betBox = view.makeOptionScreen(betInput);
//            Optional<ButtonType> betBoxResult = betBox.showAndWait();
//            if (betBoxResult.isPresent()) {
//                indicateBet(player,betInput.getText());
//            }
//            turnManager.checkOnePlayerRemains(playerList.getActivePlayers());
//        }
//        turnManager.checkShowDown(playerList.getActivePlayers(),roundNumber,5);
//        if (roundNumber < 5){
//            model.dealFlow(roundNumber);
//            nextAction(model.getAction(roundNumber));
//        }
//    }

    //TODO: maintain player that raised last
    //once the order is read in from the backend, this should be the same
    private void initializeBettingMenu(){
        playerList.updateActivePlayers();
        for (Player player : playerList.getActivePlayers()) {
            // if auto player
            // decide which action is best using algorithm (where is algorithm??) player??
            if (!player.isInteractive()) {
                AutoPlayer autoPlayer = (AutoPlayer) player;
                autoPlayer.decideAction();
            }
            else {
                // if interactive player
                // prompt player for input using stuff below
                //
                // how to access button presses within dialog box????
                EventHandler<ActionEvent> foldEvent = e -> indicateFold(player);

                TextField betInput = new TextField();
                Dialog betBox = view.makeOptionScreen(betInput, foldEvent);
                Optional<ButtonType> betBoxResult = betBox.showAndWait();
                if (betBoxResult.isPresent()) {
                    indicateBet(player, betInput.getText());
                }




            }
            turnManager.checkOnePlayerRemains(playerList.getActivePlayers());
        }
        turnManager.checkShowDown(playerList.getActivePlayers(),roundNumber,5);
        if (roundNumber < 5){
            model.dealFlow(roundNumber);
            nextAction(model.getAction(roundNumber));
        }
    }




    public void nextAction(String action){
        try{
            Class<?> c = Class.forName("controller.Controller");
            Method method = c.getDeclaredMethod(action);
            method.invoke(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void exchangeRound(){
        playerList.updateActivePlayers();
        for (Player player : playerList.getActivePlayers()) {

            TextField exchangeCardInput1 = new TextField();
            TextField exchangeCardInput2 = new TextField();
            TextField exchangeCardInput3 = new TextField();
            Dialog exchangeBox = view.makeExchangeScreen(exchangeCardInput1,exchangeCardInput2, exchangeCardInput3);

            Optional<ButtonType> exchangeBoxResult = exchangeBox.showAndWait();
            if (exchangeBoxResult.isPresent()) {
                List<String> exchangeCards  = new ArrayList<>(List.of(exchangeCardInput1.getText(),exchangeCardInput2.getText(),exchangeCardInput3.getText()));
                List<String> filtered = exchangeCards.stream()
                        .filter(b -> b.equals(""))
                        .collect(Collectors.toList());
                exchangeCards.removeAll(filtered);

                model.exchangeCards(player, exchangeCards);
                exchangeFrontEndCards(player, playerMappings.get(player));
//                dealFrontEndCards(player,playerMappings.get(player));

            }
        }
        roundNumber++;
        initializeBettingMenu();
    }


    //don't like this conditional

    //what happens when it's draw game with no community cards?
            //other than that, should be the same -> deal to whoever is up (does the order of dealing change for stud?)

    //for draw: prompt user on front end to choose cards to exchange
        //take those cards and send to backend
        //remove and deal

    //read in from the backend an action?
        //know when to do either dealing round or exchange round

    private void dealingRound(){
        recipient = model.getRecipient();
        if (recipient.equals("Community")){
            dealFrontEndCards(communityCards, displayCommunity);
        }
        else {
            playerList.updateActivePlayers();
            for (Player player : playerList.getActivePlayers()){
                dealFrontEndCards(player,playerMappings.get(player));
                //get the new cards
                //start dealing at position of last card, increment with offset
                    //knowing the positon of last card is not too bad, could be a state of FEP
            }
        }
        roundNumber++;
        initializeBettingMenu();
    }

    //deal cards in a dealing round
        //get the player's new cards
        //deal starting from last card location

    //deal card in exchange round
        //get the players' new card
        //deal at place of card that was just removed

    //to deal
        //new card(s)
        //location of where to deal to
            //how would we get this
            //keep a map of front end cards -> locations
            //get that location
            //deal to that location



    private void dealFrontEndCards(CardRecipient recipient, GameDisplayRecipient displayRecipient){
        for (Card newCard: recipient.getNewCards()){
            FrontEndCard displayCard = getFrontEndCard(newCard);
            int numberOfFrontEndCards = displayRecipient.getFrontEndCards().size();

            if (numberOfFrontEndCards!= 0){
                FrontEndCard lastCard = displayRecipient.getLastCard();
                int lastCardLocation = displayRecipient.getFrontEndCards().get(lastCard);
                view.deal(displayCard, displayRecipient, lastCardLocation + 80);
            }
            else{
                view.deal(displayCard, displayRecipient, 20);
            }
        }
    }


    private void exchangeFrontEndCards(CardRecipient recipient, GameDisplayRecipient displayRecipient){
        int dealLocation = 0;
        int cardIndex = 0;
        for (Card discardedCard: recipient.getDiscardedCardList()){
            FrontEndCard discardedFrontEndCard = frontEndCardMapppings.get(discardedCard.toString());
            view.remove(discardedFrontEndCard);

            dealLocation = displayRecipient.getFrontEndCards().get(discardedFrontEndCard);
            Card newCard = recipient.getNewCards().get(cardIndex);
            FrontEndCard displayCard = getFrontEndCard(newCard);
            view.deal(displayCard, displayRecipient, dealLocation);
            cardIndex ++;
        }
    }


    //should this be in View or Controller?
    private FrontEndCard getFrontEndCard(Card card){
        FrontEndCard frontEndCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit());
        frontEndCardMapppings.put(card.toString(), frontEndCard);
        return frontEndCard;
    }


    private void initializeCommunity(){
        displayCommunity = new FrontEndCommunity(200,200);
    }


    private void initializeFrontEndPlayers(){
        int playerOffset = 30;
        for (Player currentPlayer: playerList.getActivePlayers()){
            FrontEndPlayer newPlayer = new FrontEndPlayer(10, playerOffset, currentPlayer.toString(), currentPlayer.getBankroll());
            playerMappings.put(currentPlayer, newPlayer);
            frontEndPlayers.add(newPlayer);
            playerOffset+=50;
        }
    }

    private void indicateFold(Player player){
        player.fold();
        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.foldDisplay();
    }

    private void indicateBet(Player player, String betInput){
        int betAmount = Integer.parseInt(betInput);
        player.bet(betAmount);

        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.betDisplay(betAmount * -1);
    }
}

