package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
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
    private Map<Player, PlayerView> playerMappings;
    private Map<String, CardView> frontEndCardMappings;
    private FileReader reader;
    private Writer customWriter;
    private FileWriter writer;
    private Properties modelProperties;
    private String currentGame;
    private boolean gameStart;
    private int lastBet;
    private Player interactivePlayer;
    private boolean exitedPoker;
    private boolean oneSolventPlayer;
    private String betScreenMessage;
    private volatile boolean interactiveActionComplete;
    private Table pokerTable;
    private List<PlayerView> playerViews;
    private String cardBack;
    private JSONReader jsonReader;

    private static final String CARD_SETTINGS = "/cardSettings.json";
    private CommunityCardGrid communityCardGrid;

    public Controller() {
        betScreenMessage = "Enter a bet:";
        interactiveActionComplete = true;
        gameStart = true;
        exitedPoker = false;
        oneSolventPlayer = false;
        playerMappings = new HashMap<>();
        frontEndCardMappings = new HashMap<>();
        frontEndPlayers = new ArrayList<>();
        playerViews = new ArrayList<>();
        reader = new FileReader();
        initializeCardSettings();

        customWriter = new Writer();
        view = new GameView();
        roundNumber = 1;
        initializeMainMenu();
        initializeGameObjects();
    }

    private void initializeCardSettings() {
        jsonReader = new JSONReader();
        jsonReader.parse(CARD_SETTINGS);
        cardBack = jsonReader.getCardBack();
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

    public void transitionRound(){
        EventHandler<ActionEvent> nextRoundEvent = e -> {
            promptBuyIn();
            startRound();
            if (exitedPoker) {
                exitPoker(interactivePlayer);
            }
        };
        EventHandler<ActionEvent> cashOutEvent = e -> {
            indicateCashOut(interactivePlayer);
            if (exitedPoker) {
                exitPoker(interactivePlayer);
            }
        };
        view.makeEndRoundScreen(nextRoundEvent, cashOutEvent);
    }

    public void startRound(){
        System.out.println("\nnew hand");
        view.addToActionLog("NEW HAND");
        roundNumber = 1;
        oneSolventPlayer = false;
        playerList.resetRaiseStats();
        view.clear();
        initializeGameObjects();
        for (Player player: playerList.getAllPlayers()){
            player.clearHand();
            player.enterNewGame(communityCards, pot);
            //playerMappings.get(player).clearFrontEndCardLocations();
        }
        playerList.updateStartingRoundOrder();
        playerList.resetActivePlayers();
        initializeProperties(currentGame);
    }


    public void initializeMainMenu(){
        EventHandler<ActionEvent> gameSelectEvent = e -> initializeGameSelect();
        EventHandler<ActionEvent> homeEvent = e -> initializeMainMenu();
        view.makeMainMenu(gameSelectEvent, homeEvent);
    }

    private void exitPoker(Player player){
        try{
            resetGame();

            Properties cashOutProperties = new Properties();
            cashOutProperties.setProperty(player.toString(), String.valueOf(player.getBankroll()));
            customWriter.cashOutToProperties(player.toString(), cashOutProperties);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void resetGame(){
        roundNumber=1;
        oneSolventPlayer = false;
        gameStart = true;
        exitedPoker = false;
        view.clear();
        initializeGameObjects();
        initializeMainMenu();
        playerViews.clear();
        playerMappings.clear();
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
            view.makeActionLog();
            gameStart = false;
        }
        initializeCommunity();
        model = new Model(totalRounds, playerList, communityCards, dealer, modelProperties);
        initializeGameBoard();
        nextRound();
        if (exitedPoker){
            exitedPoker = false;
            exitPoker(interactivePlayer);
        }
    }

    private void initializeGameBoard() {
        pokerTable = new Table(300, 300, 150, playerViews);
        communityCardGrid = new CommunityCardGrid();
        communityCardGrid.setLayoutX(pokerTable.getCenterX() - (communityCardGrid.getMinWidth() / 2));
        communityCardGrid.setLayoutY(pokerTable.getCenterY() - (communityCardGrid.getMinHeight() / 2));
        view.addGameObject(pokerTable);
        view.addGameObject(communityCardGrid);
        for (PlayerView playerView: playerViews) {
            view.addGameObject(playerView);
            playerView.getCardGrid().clearCardGrid();
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
        //Todo: Create abstraction for AutoPlayerView and PlayerView
        int playerOffset = 30;
        for (Player currentPlayer: playerList.getActivePlayers()){
            PlayerView newPlayerView;
            if (!currentPlayer.isInteractive()) {
                newPlayerView = new PlayerView(currentPlayer.toString(), currentPlayer.getBankroll().getValue(), "/default-profile-pic.png");
            } else {
                newPlayerView = new PlayerView("Arjun", currentPlayer.getBankroll().getValue(), "/default-profile-pic.png");
            }

            newPlayerView.getPlayerInfoBox().getBankroll().textProperty().bind(currentPlayer.getBankroll().asString());

            playerMappings.put(currentPlayer, newPlayerView);
            playerViews.add(newPlayerView);
        }
    }

    private void initializeCommunity(){
        displayCommunity = new FrontEndCommunity(200,400);
    }

    //Everything gets caught here
    private void nextRound(){
        while (roundNumber < totalRounds + 1 && !roundManager.isRoundOver() && !exitedPoker) {
            String action = model.getAction(roundNumber);
            System.out.println("\nnext round");
            model.dealFlow(roundNumber);
            try{
                Class<?> c = Class.forName("controller.Controller");
                Method method = c.getDeclaredMethod(action);
                method.invoke(this);
            }
            //catches an invocation target exception
            catch (Exception e) {
//            displayBetMenu(interactivePlayer, e.getCause().getMessage());
                //bad input strings or something do another catch
//            showError(e.getCause().getMessage());
            }
        }
        checkShowDown();
    }

    private void checkShowDown() {
        if (!roundManager.isRoundOver() && !exitedPoker){
            roundManager.showDown(playerList);
            flipActivePlayerCards();
            view.addToActionLog(roundManager.getWinDialog());
            transitionRound();
        }
    }

    private void flipActivePlayerCards() {
        for (Player player: playerMappings.keySet()) {
            if (player.isActive()) {
                playerMappings.get(player).getCardGrid().flipCards();
            }
        }
    }

    //don't like this conditional
    private void dealingRound() throws InterruptedException {
        String recipient = model.getRecipient();
        if (recipient.equals("Community")){
            dealFrontEndCardsInRound(communityCards, communityCardGrid);
        }
        else {
            for (Player player : playerList.getActivePlayers()){
                dealFrontEndCardsInRound(player, playerMappings.get(player).getCardGrid());
            }
        }
        roundNumber++;
        playerList.updateActivePlayers();
        initializeActionMenu();
    }

    public void exchangeRound() {

        playerList.updateActivePlayers();
        for (Player player : playerList.getActivePlayers()) {

            //TODO: have a way to create the number of text field inputs based on the number of exchange cards allowed by program file
            List<TextField> exchangeInputs = new ArrayList<>();
            int maxExchangeCards = Integer.parseInt(modelProperties.getProperty("maxExchangeCards"));
            for (int i=0; i < maxExchangeCards; i++){
                exchangeInputs.add(new TextField());
            }
            if (!player.isInteractive()) {
                AutoPlayer autoPlayer = (AutoPlayer) player;
                autoPlayer.decideExchange();
                dealer.exchangeCards(autoPlayer,autoPlayer.decideExchange());
//                exchangeFrontEndCards(autoPlayer, playerMappings.get(autoPlayer));
            }
            else {

                Dialog exchangeBox = view.makeExchangeScreen(player.toString(),exchangeInputs);

                Optional<ButtonType> exchangeBoxResult = exchangeBox.showAndWait();
                if (exchangeBoxResult.isPresent()) {
                    List<String> exchangeCards = new ArrayList<>();
                    for (TextField exchangeInput : exchangeInputs){
                        exchangeCards.add(exchangeInput.getText());
                    }
                    List<String> filtered = exchangeCards.stream()
                            .filter(b -> b.equals(""))
                            .collect(Collectors.toList());
                    exchangeCards.removeAll(filtered);

                    dealer.exchangeCards(player, exchangeCards);
//                    exchangeFrontEndCards(player, playerMappings.get(player));
                }
            }
        }
        roundNumber++;
        playerList.updateActivePlayers();
        initializeActionMenu();
    }


    public void initializeActionMenu() {
        playerList.initializeActivePlayers();
        List<Player> players = playerList.getActivePlayers();
        List<Player> playersCopy = new ArrayList<>(players);

        interactiveActionComplete = true;

        if(!oneSolventPlayer){
            actionLoop:
            while(interactiveActionComplete){
                for (Player player : playersCopy) {
                    if (playerList.getRaiseSeat()!=player && player.isSolvent()){
                        lastBet = playerList.getLastBet();
                        if (!player.isInteractive()) {
                            AutoPlayer autoPlayer = (AutoPlayer) player;
                            autoPlayer.decideAction(lastBet);
                            view.addToActionLog(autoPlayer + autoPlayer.getAction());
                        }
                        else {
                            interactiveActionComplete = false;
                            while (!interactiveActionComplete){
                                ChoiceDialog dialog = view.makeActionScreen(player.toString(), lastBet, lastBet - player.getTotalBetAmount());
                                Optional<Button> result = dialog.showAndWait();
                                if (result.isPresent()){
                                    try {
                                        Class<?> c = Class.forName("controller.Controller");
                                        Method method = c.getDeclaredMethod("indicate" + result.get().getId(), Player.class);
                                        method.invoke(this, player);
                                        if (exitedPoker){
                                            return;
                                        }
                                        //TODO: fix exceptions
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (playerList.raiseMade(player)){
                            initializeActionMenu();
                            break;
                        }
                        roundManager.checkOnePlayerRemains(playerList);
                        if (roundManager.isRoundOver()){
                            transitionRound();
                            break actionLoop;
                        }
                    }
                }
                interactiveActionComplete = false;
            }
        }
        interactiveActionComplete = true;
        oneSolventPlayer = playerList.doesOneSolventPlayerRemain();
        playerList.resetRaiseStats();
        playerList.updateActivePlayers();
    }

    public void dealFrontEndCardsInRound(CardRecipient recipient, CardGrid cardGrid){
        for (Card newCard: recipient.getNewCards()) {
            CardView displayCard = getFrontEndCard(newCard);
            cardGrid.addCardView(displayCard);
        }
    }

//    public void exchangeFrontEndCards(Player player, GameDisplayRecipient displayRecipient){
//        int dealLocation = 0;
//        int cardIndex = 0;
//        for (Card discardedCard: player.getDiscardedCards()){
//            FrontEndCard discardedFrontEndCard = frontEndCardMappings.get(discardedCard.toString());
//            view.remove(discardedFrontEndCard);
//
//            int dealLocation = displayRecipient.getFrontEndCardLocations().get(discardedFrontEndCard);
//            Card newCard = player.getNewCards().get(cardIndex);
//            FrontEndCard displayCard = getFrontEndCard(newCard);
//            view.deal(displayCard, displayRecipient, dealLocation);
//            cardIndex ++;
//        }
//    }

    //should this be in View or Controller?
    private CardView getFrontEndCard(Card card){
        boolean isFrontEndVisible = (card.isBackEndVisible() || card.isInteractivePlayerCard());

        //FrontEndCard frontEndCard = new FrontEndCard(card.getCardSymbol(), card.getCardSuit(), isFrontEndVisible);
        CardView cardView = new CardView(jsonReader.getRanks().get(card.getRank()), jsonReader.getSuits().get(card.getCardSuit()), cardBack, isFrontEndVisible);
        frontEndCardMappings.put(card.toString(), cardView);
        return cardView;
    }

    public void promptBuyIn(){
        for (Player player : playerList.getAllPlayers()){
            if (!player.isSolvent()){
                System.out.println(player.toString());
                if (!player.isInteractive()){
                    player.updateBankroll(1000);
                }
                else{
                    TextField buyBackInput = new TextField();
                    Dialog buyBackBox = view.makeBuyInScreen(buyBackInput);
                    Optional buyBackBoxResult = buyBackBox.showAndWait();
                    if (buyBackBoxResult.isPresent()){
                        player.updateBankroll(Integer.parseInt(buyBackInput.getText()));
                    }
                    else{
                        exitedPoker = true;
                    }
                }
            }
        }
    }

    private void indicateBet(Player player) {
        int betAmount = 0;
        TextField betInput = new TextField();
        Dialog betBox = view.makeBetPopUp(betInput, betScreenMessage);
        Optional betBoxResult = betBox.showAndWait();
        if (betBoxResult.isPresent()) {
            betAmount = Integer.parseInt(betInput.getText());
            if (player.getTotalBetAmount() + betAmount < lastBet) {
                betScreenMessage  = "Cannot bet less than the call amount on the table! Please bet at least $" + (lastBet - player.getTotalBetAmount());
                indicateBet(player);
            }
            else {
                try {
                    player.bet(betAmount);
                    view.addToActionLog(player.toString() +  " bet $" + betAmount);
                    interactiveActionComplete = true;
                    PlayerView displayPlayer = playerMappings.get(player);
                    //displayPlayer.getPlayerInfoBox().setBankroll(player.getBankroll());
                    //displayPlayer.betDisplay(betAmount * -1);
                } catch (ModelException e) {
                    betScreenMessage = e.getMessage();
                    indicateBet(player);
                }
            }
        }
        betScreenMessage = "Enter a Bet:";
    }


    private void indicateFold(Player player){
        interactiveActionComplete = true;
        player.fold();
        view.addToActionLog(player.toString() +  " folded");
    }

    private void indicateCall(Player player){
        interactiveActionComplete = true;
        System.out.println("call");
        player.call(lastBet);
        view.addToActionLog(player.toString() +  " called");
    }

    private void indicateCheck(Player player){
        interactiveActionComplete = true;
        System.out.println("Check");
        view.addToActionLog(player.toString() +  " checked");
    }

    private void indicateCashOut(Player player){
        Alert cashOutConfirm = view.makeCashOutAlert(player.toString(), player.getBankroll().getValue());
        Optional<ButtonType> cashOutResult = cashOutConfirm.showAndWait();
        if (cashOutResult.get() == ButtonType.OK){
            exitedPoker = true;
        } else {
            System.out.println("Did not cash out");
        }
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Controller Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

