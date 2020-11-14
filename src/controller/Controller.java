package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import view.*;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private static final String ENGLISH = "English";

    private static final String RESOURCES = "Resources/";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    private ResourceBundle projectTextResources;

    private Model model;
    private RoundManager roundManager;
    private PlayerList playerList;
    private List<GameDisplayRecipient> frontEndPlayers;
    private CommunityCards communityCards;
    private GameDisplayRecipient displayCommunity;
    private Pot pot;
    private Dealer dealer;
    private GameView view;
    private int roundNumber;
    private int totalRounds;
    private Map<Player, FrontEndPlayer> playerMappings;
    private Map<String, FrontEndCard> frontEndCardMappings;
    private FileReader reader;
    private Writer customWriter;
    private FileWriter writer;
    private Properties modelProperties;
    private String currentGame;
    private boolean gameStart;
    private int lastBet;
    private int callAmount;
    private Player interactivePlayer;
    private boolean cashedOut;

    public Controller() {
        gameStart = true;
        cashedOut = false;
        playerMappings = new HashMap<>();
        frontEndCardMappings = new HashMap<>();
        frontEndPlayers = new ArrayList<>();
        reader = new FileReader();
        customWriter = new Writer();
        view = new GameView();
        roundNumber = 1;
        try{
            writer = new FileWriter("properties/Bank.properties");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        initializeMainMenu();
        initializeGameObjects();
    }

    private void initializeGameObjects(){
        Game game = new Game();
        communityCards = game.getCommunityCards();
        pot = game.getPot();
        dealer = game.getDealer();
        roundManager = game.getTurnManager();
    }

    public Scene setupScene() {
        return view.setupScene();
    }

    public void checkRoundOver(){
        if (roundManager.roundOver()){
            startRound();
        }
    }

    public void startRound(){
        System.out.println("new round");
        roundNumber = 1;
        view.clear();
        initializeGameObjects();
        for (Player player: playerList.getAllPlayers()){
            player.clearHand();
            player.enterNewGame(communityCards, pot);
            playerMappings.get(player).clearFrontEndCardLocations();
        }
        playerList.updateStartingRoundOrder();
        playerList.resetActivePlayers();
        initializeProperties(currentGame);
    }

    public void initializeMainMenu(){
//        view = new GameView();
        System.out.println("out");
        EventHandler<ActionEvent> gameSelectEvent = e -> initializeGameSelect();
        EventHandler<ActionEvent> homeEvent = e -> initializeMainMenu();
        view.makeMainMenu(gameSelectEvent, homeEvent);
    }

    private void cashOut(Player player){
//        cashedOut = true;
        try{
            view.clear();
            initializeGameObjects();
            initializeMainMenu();

            Properties cashOutProperties = new Properties();
            cashOutProperties.setProperty(player.toString(), String.valueOf(player.getBankroll()));
            customWriter.cashOutToProperties(writer, cashOutProperties);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void initializeGameSelect(){
        EventHandler<ActionEvent> holdemEvent = e -> initializeProperties("Holdem.properties");
        EventHandler<ActionEvent> drawEvent = e -> initializeProperties("FiveCardDraw.properties");
        EventHandler<ActionEvent> studEvent = e -> initializeProperties("SevenCardStud.properties");
        EventHandler<ActionEvent> customEvent = e -> chooseNewFile();
        view.makeGameSelectScreen(holdemEvent, drawEvent, studEvent, customEvent);
    }

    private void chooseNewFile() {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Game Type (*.properties)", "*.properties");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialDirectory(new File("properties/"));
        File file = fileChooser.showOpenDialog(new Stage());
        if(file!=null) {
            initializeProperties((file).getName());
        }
    }

    public void initializeProperties(String fileName){
        currentGame = fileName;
        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        modelProperties = reader.getPropertyFile(fileName);
        totalRounds = Integer.parseInt(modelProperties.getProperty("maxRounds"));
        if (gameStart){
            initializePlayerList(fileName);
            initializeFrontEndPlayers();
            gameStart = false;
        }
        initializeCommunity();
        model = new Model(totalRounds, playerList, communityCards, dealer, modelProperties);
        model.dealFlow(roundNumber);
        nextRound(model.getAction(roundNumber));
        if (cashedOut){
            cashedOut = false;
            cashOut(interactivePlayer);
        }
    }

    private void initializePlayerList(String fileName){
        //TODO: use factory design pattern here to choose what kind of playerList to instantiate
        //TODO: use configuration files to instantiate the players
        try {
            Properties modelProperties = reader.getPropertyFile(fileName);
            String playerListType = modelProperties.getProperty("playerListType");
            Class<?> cl = Class.forName("model." + playerListType + "PlayerList");
            Player player1 = new InteractivePlayer("Arjun", 1000, communityCards, pot);
            interactivePlayer = player1;
            Player player2 = new AutoPlayer("Christian", 1000, communityCards, pot);
            Player player3 = new AutoPlayer("Yasser", 1000, communityCards, pot);
            playerList = (PlayerList) cl.getConstructor(List.class)
                    .newInstance(new ArrayList<>(List.of(player1,player2, player3)));
        }
        catch(Exception e){
            e.printStackTrace();
        }
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

    private void initializeCommunity(){
        displayCommunity = new FrontEndCommunity(200,400);
    }


    //Everything gets caught here
    private void nextRound(String action){
        if (cashedOut){
            return;
        }

        try{
            Class<?> c = Class.forName("controller.Controller");
            Method method = c.getDeclaredMethod(action);
            method.invoke(this);
        }
        //catches an invocation target exception
        catch (Exception e) {
            e.printStackTrace();
            //if it's a exchange card error, then we want to reprompt an exchange
            //if it's an invalid bet, we want to reprompt the whole action
//            showError(e.getCause().getMessage());
        }
    }

    //don't like this conditional
    private void dealingRound() throws InterruptedException {
        String recipient = model.getRecipient();
        if (recipient.equals("Community")){
            dealFrontEndCardsInRound(communityCards, displayCommunity);
        }
        else {
            for (Player player : playerList.getActivePlayers()){
                dealFrontEndCardsInRound(player,playerMappings.get(player));
            }
        }
        roundNumber++;
        playerList.updateActivePlayers();
        initializeActionMenu();
    }

    public void exchangeRound() {
        playerList.updateActivePlayers();
        for (Player player : playerList.getActivePlayers()) {

            //TODO: have a way to create the number of text field inputs based on the number of exchange cards allowed as specified by user
            TextField exchangeCardInput1 = new TextField();
            TextField exchangeCardInput2 = new TextField();
            TextField exchangeCardInput3 = new TextField();
            Dialog exchangeBox = view.makeExchangeScreen(player.toString(), exchangeCardInput1,exchangeCardInput2, exchangeCardInput3);

            Optional<ButtonType> exchangeBoxResult = exchangeBox.showAndWait();
            if (exchangeBoxResult.isPresent()) {
                List<String> exchangeCards  = new ArrayList<>(List.of(exchangeCardInput1.getText(),exchangeCardInput2.getText(),exchangeCardInput3.getText()));
                List<String> filtered = exchangeCards.stream()
                        .filter(b -> b.equals(""))
                        .collect(Collectors.toList());
                exchangeCards.removeAll(filtered);
                dealer.exchangeCards(player, exchangeCards);
                exchangeFrontEndCards(player, playerMappings.get(player));
            }
        }
        roundNumber++;
        playerList.updateActivePlayers();
        initializeActionMenu();
    }

    public void initializeActionMenu() {
        if (cashedOut){
            return;
        }

        playerList.initializeActivePlayers();
        List<Player> players = playerList.getActivePlayers();
        List<Player> playersCopy = new ArrayList<>(players);

        for (Player player : playersCopy) {
            if (playerList.getRaiseSeat()!=player){
                if (!player.isInteractive()) {
                    AutoPlayer autoPlayer = (AutoPlayer) player;
                    autoPlayer.decideAction();
                }
                else {
                    lastBet = playerList.getLastBet();

                    ChoiceDialog dialog = view.makeActionScreen(player.toString(), lastBet);
                    Optional<Button> result = dialog.showAndWait();
                    if (result.isPresent()){
                        if (result.get().getId().equals("Bet")){
                            displayBetMenu(player);
                        }
                        else {
                            try {
                                Class<?> c = Class.forName("controller.Controller");
                                Method method = c.getDeclaredMethod("indicate" + result.get().getId(), Player.class);
                                method.invoke(this, player);
                                //TODO: fix exceptions
                            } catch (Exception e) {
                                e.printStackTrace();
//                                showError(e.getMessage());
                            }
                        }
                    }
                }
                if (playerList.raiseMade(player)){
                    initializeActionMenu();
                    break;
                }
                roundManager.checkOnePlayerRemains(playerList);
                checkRoundOver();
            }
        }
        playerList.resetRaiseStats();
        playerList.updateActivePlayers();
        roundManager.checkShowDown(playerList, roundNumber, totalRounds + 1);

        if (roundNumber < totalRounds + 1) {
            model.dealFlow(roundNumber);
            nextRound(model.getAction(roundNumber));
        }
        else {
            startRound();
        }
    }




    public void dealFrontEndCardsInRound(CardRecipient recipient, GameDisplayRecipient displayRecipient){
        for (Card newCard: recipient.getNewCards()){
            FrontEndCard displayCard = getFrontEndCard(newCard);
            int numberOfFrontEndCards = displayRecipient.getFrontEndCardLocations().size();

            if (numberOfFrontEndCards!= 0){
                FrontEndCard lastCard = displayRecipient.getLastCard();
                int lastCardLocation = displayRecipient.getFrontEndCardLocations().get(lastCard);
                view.deal(displayCard, displayRecipient, lastCardLocation + 80);
            }
            else{
                view.deal(displayCard, displayRecipient, 20);
            }
        }
    }

    public void exchangeFrontEndCards(Player player, GameDisplayRecipient displayRecipient){
//        int dealLocation = 0;
        int cardIndex = 0;
        for (Card discardedCard: player.getDiscardedCards()){
            FrontEndCard discardedFrontEndCard = frontEndCardMappings.get(discardedCard.toString());
            view.remove(discardedFrontEndCard);

            int dealLocation = displayRecipient.getFrontEndCardLocations().get(discardedFrontEndCard);
            Card newCard = player.getNewCards().get(cardIndex);
            FrontEndCard displayCard = getFrontEndCard(newCard);
            view.deal(displayCard, displayRecipient, dealLocation);
            cardIndex ++;
        }
    }

    //should this be in View or Controller?
    private FrontEndCard getFrontEndCard(Card card){
        FrontEndCard frontEndCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit(), card.isVisible());
        frontEndCardMappings.put(card.toString(), frontEndCard);
        return frontEndCard;
    }

    public void displayBetMenu(Player player){
        TextField betInput = new TextField();
        Dialog betBox = view.makeBetPopUp(betInput);
        Optional betBoxResult = betBox.showAndWait();
        if (betBoxResult.isPresent()) {
            indicateBet(player, betInput.getText());
        }
    }

    private void indicateBet(Player player, String betInput){
        int betAmount = Integer.parseInt(betInput);
        player.bet(betAmount);

        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.betDisplay(betAmount * -1);
    }

    private void indicateFold(Player player){
        player.fold();
        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.foldDisplay();
    }


    private void indicateCall(Player player){
        System.out.println("call");
        callAmount = lastBet - player.getTotalBetAmount();
        player.bet(callAmount);
        FrontEndPlayer displayPlayer = playerMappings.get(player);
//        displayPlayer.callDisplay();
    }

    private void indicateCheck(Player player){
        System.out.println("Check");
        FrontEndPlayer displayPlayer = playerMappings.get(player);
        displayPlayer.checkDisplay();
    }

    private void indicateCashOut(Player player){
        Alert cashOutConfirm = view.makeCashOutAlert();
        Optional<ButtonType> cashOutResult = cashOutConfirm.showAndWait();
        if (cashOutResult.get() == ButtonType.OK){
//            cashOut(player);
            cashedOut = true;
        } else {
            System.out.println("Did not cash out");
        }
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Controller Error");
        alert.setContentText(message);
        alert.show();
    }


}

