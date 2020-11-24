package controller;

import controller.exceptions.ControllerException;
import controller.exceptions.InvalidNameEnteredException;
import controller.exceptions.InvalidNumberPlayersException;
import controller.exceptions.InvalidStartingAmountException;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AutoPlayer;
import model.Card;
import model.CardRecipient;
import model.CommunityCards;
import model.Dealer;
import model.Game;
import model.Game.AutoPlayerNames;
import model.InteractivePlayer;
import model.PokerModel;
import model.ModelException;
import model.Player;
import model.PlayerList;
import model.Pot;
import model.RoundManager;
import utility.PropertiesFileReader;
import utility.PropertiesFileWriter;
import view.CardGrid;
import view.CardView;
import view.CommunityCardGrid;
import view.GameView;
import view.PlayerView;
import view.PotView;
import view.Table;

public class Controller {

  private static final String CARD_SETTINGS = "/cardSettings.json";
  private PokerModel pokerModel;
  private RoundManager roundManager;
  private PlayerList playerList;
  private CommunityCards communityCards;
  private Pot pot;
  private Dealer dealer;
  private final GameView view;
  private int roundNumber;
  private int totalRounds;
  private final Map<Player, PlayerView> playerMappings;
  private final Map<Card, CardView> frontEndCardMappings;
  private String currentGame;
  private boolean gameStart;
  private int lastBet;
  private Player interactivePlayer;
  private boolean exitedPoker;
  private boolean oneSolventPlayer;
  private String betScreenMessage;
  private volatile boolean interactiveActionComplete;
  private final List<PlayerView> playerViews;
  private String cardBack;
  private JSONReader jsonReader;
  private CommunityCardGrid communityCardGrid;
  private int maxExchangeCards;
  private String interactivePlayerName;
  private int playerStartingAmount;
  private int numAutoPlayers;
  private static int MINIMUM_STARTING_AMOUNT = 50;
  private static int MAXIMUM_STARTING_AMOUNT = 10000;
  private boolean alterHands;

  public Controller() {
    betScreenMessage = "Enter a bet:";
    interactiveActionComplete = true;
    gameStart = true;
    exitedPoker = false;
    oneSolventPlayer = false;
    alterHands = false;
    playerMappings = new HashMap<>();
    frontEndCardMappings = new HashMap<>();
    playerViews = new ArrayList<>();
    initializeCardSettings();

    view = new GameView();
    roundNumber = 1;
    initializePlayerSelectMenu();
    initializeGameObjects();
  }


  public Scene setupScene() {
    return view.setupScene();
  }

  private void initializeCardSettings() {
    jsonReader = new JSONReader();
    jsonReader.parse(CARD_SETTINGS);
    cardBack = jsonReader.getCardBack();
  }

  private void initializeGameObjects() {
    Game game = new Game();
    communityCards = game.getCommunityCards();
    pot = game.getPot();
    dealer = game.getDealer();
    roundManager = game.getTurnManager();
  }

  private void initializePlayerSelectMenu() {
    EventHandler<ActionEvent> newPlayerSelectEvent = e -> initializeNewPlayer();
    EventHandler<ActionEvent> loadPlayerEvent = e -> initializeLoadPlayer();
    view.makePlayerSelectScreen(newPlayerSelectEvent, loadPlayerEvent);
  }

  public void initializeNewPlayer() {
    try {
      TextField nameInput = new TextField();
      nameInput.setId("nameInput");
      TextInputDialog newPlayerDialog = view.makeDialogBox(nameInput, "Enter a name: ", "Name: ");
      styleDialogBox(newPlayerDialog);


      Optional<String> result = newPlayerDialog.showAndWait();
      if (result.isPresent()) {
        String nameEntered = nameInput.getText();
        if (invalidNameEntered(nameEntered)) {
          throw new InvalidNameEnteredException();
        } else {
          interactivePlayerName = nameInput.getText();
          initializeNewPlayerStartingAmount();
        }
      } else {
        initializePlayerSelectMenu();
      }
    } catch (InvalidNameEnteredException e) {
      showError(e.getMessage());
      initializeNewPlayer();
    }
  }

  public boolean invalidNameEntered(String nameEntered) {
    String regex = "^[a-zA-Z]+$";
    return !nameEntered.matches(regex);
  }

  public boolean invalidPlayersEntered(int numberOfPlayers) {
    int MAX_AUTOPLAYERS = 7;
    return ((numberOfPlayers > MAX_AUTOPLAYERS) || (numberOfPlayers < 1));
  }


  public void initializeNewPlayerStartingAmount() {
    try {
      TextField startingMoneyInput = new TextField();
      startingMoneyInput.setId("startingMoneyInput");
      TextInputDialog newPlayerDialog = view
          .makeDialogBox(startingMoneyInput, "How much money would you like to start with?", "Starting Amount: ");
      styleDialogBox(newPlayerDialog);
      Optional<String> result = newPlayerDialog.showAndWait();
      if (result.isPresent()) {
        Integer amountEntered = Integer.parseInt(startingMoneyInput.getText());
        if (!isValidInteger(amountEntered)) {
          throw new InvalidStartingAmountException();
        } else {
          playerStartingAmount = amountEntered;
          getNumAutoPlayers();
        }
      } else {
        initializePlayerSelectMenu();
      }
    } catch (NumberFormatException | InvalidStartingAmountException e) {
      InvalidStartingAmountException invalidAmount = new InvalidStartingAmountException();
      showError(invalidAmount.getMessage());
      initializeNewPlayerStartingAmount();
    }
  }

  private void initializeLoadPlayer() {
    File savedFile = chooseNewFile("PlayerSaveFiles");
    if (savedFile != null) {
      String savedFileName = savedFile.getName();
      String savedFileNameWithoutExtension = savedFileName
          .substring(0, savedFileName.lastIndexOf('.'));

      Properties savedInfo = PropertiesFileReader.getPropertyFile(savedFileNameWithoutExtension);
      interactivePlayerName = savedInfo.getProperty("NAME");
      playerStartingAmount = Integer.parseInt(savedInfo.getProperty("BANKROLL"));
      getNumAutoPlayers();
    } else {
      initializePlayerSelectMenu();
    }
  }


  public void getNumAutoPlayers() {
    try {
      TextField numAutoPlayerInput = new TextField();
      numAutoPlayerInput.setId("numAutoPlayerInput");
      TextInputDialog newPlayerDialog = view
          .makeDialogBox(numAutoPlayerInput, "How many opponents would you like? You can choose between 1 and 7 opponents.", "Number of Opponents: ");
      styleDialogBox(newPlayerDialog);

      Optional<String> result = newPlayerDialog.showAndWait();
      if (result.isPresent()) {
        int numEntered = Integer.parseInt(numAutoPlayerInput.getText());
        if (invalidPlayersEntered(numEntered)) {
          throw new InvalidNumberPlayersException();
        } else {
          numAutoPlayers = numEntered;
          initializeMainMenu();
        }
      } else {
        initializePlayerSelectMenu();
      }
    } catch (InvalidNumberPlayersException | NumberFormatException e) {
      InvalidNumberPlayersException invalidError = new InvalidNumberPlayersException();
      showError(invalidError.getMessage());
      getNumAutoPlayers();
    }
  }

  public boolean isValidInteger(Object o) {
    if (!o.getClass().getName().equals("java.lang.Integer")) {
      return false;
    }
    int amountEntered = (int) o;
    return (amountEntered >= MINIMUM_STARTING_AMOUNT) && (amountEntered <= MAXIMUM_STARTING_AMOUNT);
  }

  public void initializeMainMenu() {
    EventHandler<ActionEvent> gameSelectEvent = e -> initializeGameSelect();
    EventHandler<ActionEvent> homeEvent = e -> initializePlayerSelectMenu();
    view.makeMainMenu(gameSelectEvent, homeEvent);
  }

  private void transitionRound() {
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

  private void startRound() {
    view.addToActionLog("NEW HAND");
    roundNumber = 1;
    oneSolventPlayer = false;
    playerList.resetRaiseStats();
    view.clear();
    initializeGameObjects();
    for (Player player : playerList.getAllPlayers()) {
      player.clearHand();
      player.enterNewGame(communityCards, pot);
    }
    playerList.updateStartingRoundOrder();
    playerList.resetActivePlayers();
    initializeProperties(currentGame);
  }

  private void exitPoker(Player player) {
    try {
      resetGame();

      Properties cashOutProperties = new Properties();
      cashOutProperties
          .setProperty("BANKROLL", String.valueOf(player.getBankroll().getValue()));
      cashOutProperties.setProperty("NAME", player.toString());

      PropertiesFileWriter.cashOutToPlayerSaves(player.toString(), cashOutProperties);
    } catch (Exception e) {
      showError(e.getMessage());
    }
  }

  private void resetGame() {
    roundNumber = 1;
    oneSolventPlayer = false;
    gameStart = true;
    exitedPoker = false;
    view.clear();
    initializeGameObjects();
    initializePlayerSelectMenu();
    playerViews.clear();
    playerMappings.clear();
  }

  public void initializeProperties(String fileName) {
    try {
      currentGame = fileName;
      fileName = fileName.substring(0, fileName.lastIndexOf('.'));
      Properties modelProperties = PropertiesFileReader.getPropertyFile(fileName);
      totalRounds = Integer.parseInt(modelProperties.getProperty("maxRounds"));
      maxExchangeCards = Integer.parseInt(modelProperties.getProperty("maxExchangeCards"));
      if (gameStart) {
        initializePlayerList(fileName);
        initializeFrontEndPlayers();
        gameStart = false;
      }
      view.makeActionLog();
      pokerModel = new PokerModel(playerList, communityCards, dealer, modelProperties);
      pokerModel.checkInvalidNumberOfCards(numAutoPlayers, totalRounds);
      initializeGameBoard();
      nextRound();
      if (exitedPoker) {
        exitedPoker = false;
        exitPoker(interactivePlayer);
      }
    } catch (NumberFormatException e) {
      showError(
          "Invalid formatting in properties file. Exit program and enter values in correct format.");
      exitedPoker = false;
      exitPoker(interactivePlayer);
    } catch (ControllerException | ModelException e) {
      showError(e.getMessage());
      exitedPoker = false;
      exitPoker(interactivePlayer);
    }
  }

  private void initializeGameSelect() {
    EventHandler<ActionEvent> holdemEvent = e -> initializeProperties("Holdem.properties");
    EventHandler<ActionEvent> drawEvent = e -> initializeProperties("FiveCardDraw.properties");
    EventHandler<ActionEvent> studEvent = e -> initializeProperties("SevenCardStud.properties");
    EventHandler<ActionEvent> customEvent = e -> chooseFileAndInitializeProperties();
    view.makeGameSelectScreen(holdemEvent, drawEvent, studEvent, customEvent);
  }

  private void chooseFileAndInitializeProperties() {
    File customGame = chooseNewFile("properties");
    if (customGame != null) {
      initializeProperties(customGame.getName());
    } else {
      initializePlayerSelectMenu();
    }
  }

  private File chooseNewFile(String initialDirectory) {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Game Type (*.properties)",
        "*.properties");
    fileChooser.getExtensionFilters().add(filter);
    fileChooser.setInitialDirectory(new File(initialDirectory + "/"));
    return fileChooser.showOpenDialog(new Stage());
  }


  private void initializeGameBoard() {
    Table pokerTable = new Table(300, 300, 150, playerViews);
    communityCardGrid = new CommunityCardGrid(pokerTable.getCenterX(), pokerTable.getCenterY());
    PotView potView = new PotView("100", "/pot.png", pokerTable.getCenterX(),
        pokerTable.getCenterY());
    potView.getGameStat().textProperty()
        .bind(pot.getPotTotal().asString());

    view.addGameObject(pokerTable);
    view.addGameObject(communityCardGrid);
    for (PlayerView playerView : playerViews) {
      view.addGameObject(playerView);
      playerView.getCardGrid().clearCardGrid();
    }
    view.addGameObject(potView);

  }

  private void initializePlayerList(String fileName) {
    try {
      Properties modelProperties = PropertiesFileReader.getPropertyFile(fileName);
      String playerListType = modelProperties.getProperty("playerListType");
      Class<?> cl = Class.forName("model." + playerListType + "PlayerList");
      interactivePlayer = new InteractivePlayer(interactivePlayerName,
          playerStartingAmount, communityCards, pot);
      List<Player> players = initializeAutoPlayers();
      players.add(interactivePlayer);
      playerList = (PlayerList) cl.getConstructor(List.class)
          .newInstance(new ArrayList<>(players));
      if (playerStartingAmount == 0) {
        promptBuyIn();
      }
      if (alterHands){
        alterPlayerHands(new Card(2, "CLUBS"));
      }
    } catch (Exception e) {
      throw new ControllerException(
          "Invalid player list input in file. Exit program and reconfigure file.");
    }
  }

  private List<Player> initializeAutoPlayers() {
    List<Player> autoPlayerList = new ArrayList<>();
    for (int numPlayers = 0; numPlayers < numAutoPlayers; numPlayers++) {
      String autoPlayerName = AutoPlayerNames.values()[numPlayers].getValue();
      autoPlayerList.add(
          new AutoPlayer(autoPlayerName, playerStartingAmount, communityCards, pot));
    }
    return autoPlayerList;
  }

  private void initializeFrontEndPlayers() {
    for (Player currentPlayer : playerList.getActivePlayers()) {
      PlayerView newPlayerView;
      if (currentPlayer.isInteractive()) {
        newPlayerView = new PlayerView(currentPlayer.toString(),
            currentPlayer.getBankroll().getValue(), "/default-profile-pic.png");
      } else {
        newPlayerView = new PlayerView(interactivePlayerName,
            currentPlayer.getBankroll().getValue(), "/default-profile-pic.png");
      }

      newPlayerView.getPlayerInfoBox().getBankroll().textProperty()
          .bind(currentPlayer.getBankroll().asString());

      playerMappings.put(currentPlayer, newPlayerView);
      playerViews.add(newPlayerView);
    }
  }

  private void nextRound() {
    while (roundNumber < totalRounds + 1 && !roundManager.isRoundOver() && !exitedPoker) {
      try {
        String action = pokerModel.getAction(roundNumber);
        pokerModel.backEndDeal(roundNumber);
        Class<?> c = Class.forName("controller.Controller");
        Method method = c.getDeclaredMethod(action);
        method.invoke(this);
      } catch (ModelException e) {
        throw new ControllerException(e.getMessage());
      } catch (Exception e) {
        throw new ControllerException(
            "Invalid action inputs in file. Exit program and reconfigure file");
      }
    }
    checkShowDown();
  }

  private void checkShowDown() {
    if (!roundManager.isRoundOver() && !exitedPoker) {
      roundManager.showDown(playerList);
      flipActivePlayerCards();
      view.addToActionLog(roundManager.getWinDialog());
      transitionRound();
    }
  }

  private void flipActivePlayerCards() {
    for (Player player : playerMappings.keySet()) {
      if (player.isActive()) {
        playerMappings.get(player).getCardGrid().flipCards();
      }
    }
  }


  private void dealingRound() {
    String recipient = pokerModel.getRecipient();
    if (recipient.equals("Community")) {
      dealFrontEndCardsInRound(communityCards, communityCardGrid);
    } else {
      for (Player player : playerList.getActivePlayers()) {
        dealFrontEndCardsInRound(player, playerMappings.get(player).getCardGrid());
      }
    }
    roundNumber++;
    playerList.updateActivePlayers();
    initializeActionMenu();
  }

  private void exchangeRound() {
    playerList.updateActivePlayers();
    for (Player player : playerList.getActivePlayers()) {
      if (player.isInteractive()) {
        AutoPlayer autoPlayer = (AutoPlayer) player;
        autoPlayer.decideExchange();
        dealer.exchangeCards(autoPlayer, autoPlayer.decideExchange());
        exchangeFrontEndCards(autoPlayer);
      } else {
        Optional<ButtonType> exchangeBoxResult;
        while (!interactiveActionComplete) {
          Dialog<ButtonType> exchangeBox = view
              .makeExchangeScreen(player.toString(), maxExchangeCards);
          styleDialogBox(exchangeBox);
          exchangeBoxResult = exchangeBox.showAndWait();
          if (exchangeBoxResult.get() == ButtonType.OK && isSelectedCardsExchangeable()) {
            interactiveActionComplete = true;
            Set<CardView> selectedCards = playerMappings.get(interactivePlayer).getCardGrid()
                .getSelectedCards();
            List<Card> exchangeCards = new ArrayList<>();
            for (CardView card : selectedCards) {
              Card cardToBeExchanged = getCardFromCardView(card);
              exchangeCards.add(cardToBeExchanged);
            }
            dealer.exchangeCards(player, exchangeCards);
            exchangeFrontEndCards(player);
          }
        }
      }
    }
    roundNumber++;
    playerList.updateActivePlayers();
    initializeActionMenu();
  }

  private Card getCardFromCardView(CardView card) {
    return frontEndCardMappings.entrySet()
        .stream()
        .filter(entry -> card.equals(entry.getValue()))
        .map(Map.Entry::getKey).findFirst().get();
  }

  private boolean isSelectedCardsExchangeable() {
    return playerMappings.get(interactivePlayer).getCardGrid().getSelectedCards().size()
        <= maxExchangeCards;
  }


  private void initializeActionMenu() {
    playerList.initializeActivePlayers();
    List<Player> players = playerList.getActivePlayers();
    List<Player> playersCopy = new ArrayList<>(players);
    interactiveActionComplete = true;
    if (!oneSolventPlayer) {
      actionLoop:
      while (interactiveActionComplete) {
        for (Player player : playersCopy) {
          if (playerList.getRaiseSeat() != player && player.isSolvent()) {
            lastBet = playerList.getLastBet();
            if (player.isInteractive()) {
              AutoPlayer autoPlayer = (AutoPlayer) player;
              autoPlayer.decideAction(lastBet);
              view.addToActionLog(autoPlayer + autoPlayer.getAction());
            } else {
              interactiveActionComplete = false;
              while (!interactiveActionComplete) {
                ChoiceDialog<Button> dialog = view.makeActionScreen(player.toString(), lastBet,
                    lastBet - player.getTotalBetAmount());
                styleDialogBox(dialog);

                Optional<Button> result = dialog.showAndWait();
                if (result.isPresent()) {
                  try {
                    Class<?> c = Class.forName("controller.Controller");
                    Method method = c
                        .getDeclaredMethod("indicate" + result.get().getId(), Player.class);
                    method.invoke(this, player);
                    if (exitedPoker) {
                      return;
                    }
                  } catch (Exception e) {
                    showError(e.getCause().getMessage());
                  }
                }
              }
            }
            if (playerList.raiseMade(player)) {
              initializeActionMenu();
              break;
            }
            roundManager.checkOnePlayerRemains(playerList);
            if (roundManager.isRoundOver()) {
              view.addToActionLog(roundManager.getWinDialog());
              transitionRound();
              break actionLoop;
            }
          }
        }
        interactiveActionComplete = false;
      }
    }
    oneSolventPlayer = playerList.doesOneSolventPlayerRemain();
    playerList.resetRaiseStats();
    playerList.updateActivePlayers();
  }


  private void dealFrontEndCardsInRound(CardRecipient recipient, CardGrid cardGrid) {
    for (Card newCard : recipient.getNewCards()) {
      CardView displayCard = getFrontEndCard(newCard);
      cardGrid.addCardView(displayCard);
    }
  }

  private void exchangeFrontEndCards(Player player) {
    int cardIndex = 0;
    CardGrid playerCardGrid = playerMappings.get(player).getCardGrid();
    for (Card discardedCard : player.getDiscardedCards()) {
      CardView discardedCardView = frontEndCardMappings.get(discardedCard);
      Card cardToBeDealt = player.getNewCards().get(cardIndex);
      CardView cardViewToBeDealt = getFrontEndCard(cardToBeDealt);

      Point2D locationToDeal = playerCardGrid.removeCard(discardedCardView);
      playerCardGrid.addCardViewToLocation(cardViewToBeDealt, locationToDeal);

      cardIndex++;
    }
  }

  private CardView getFrontEndCard(Card card) {
    boolean isFrontEndVisible = (card.isBackEndVisible() || card.isInteractivePlayerCard());
    CardView cardView = new CardView(jsonReader.getRanks().get(card.getRank()),
        jsonReader.getSuits().get(card.getCardSuit()), cardBack, isFrontEndVisible);
    frontEndCardMappings.put(card, cardView);
    return cardView;
  }

  private void promptBuyIn() {
    for (Player player : playerList.getAllPlayers()) {
      if (!player.isSolvent()) {
        if (player.isInteractive()) {
          player.updateBankroll(1000 - player.getBankroll().getValue());
        } else {
          TextField buyBackInput = new TextField();
          Dialog<String> buyBackBox = view.makeBuyInScreen(buyBackInput);
          styleDialogBox(buyBackBox);
          Optional<String> buyBackBoxResult = buyBackBox.showAndWait();
          if (buyBackBoxResult.isPresent()) {
            try {
              player.updateBankroll(Integer.parseInt(buyBackInput.getText()));
            } catch (NumberFormatException e) {
              showError("Invalid buy-in input. Please enter a numeric value");
              promptBuyIn();
            }
          } else {
            exitedPoker = true;
          }
        }
      }
    }
  }

  private void indicateBet(Player player) {
    int betAmount;
    TextField betInput = new TextField();
    Dialog<String> betBox = view.makeBetPopUp(betInput, betScreenMessage);
    styleDialogBox(betBox);
    Optional<String> betBoxResult = betBox.showAndWait();
    if (betBoxResult.isPresent()) {
      try {
        betAmount = Integer.parseInt(betInput.getText());
        if (player.getTotalBetAmount() + betAmount < lastBet) {
          betScreenMessage =
              "Cannot bet less than the call amount on the table! Please bet at least $" + (lastBet
                  - player.getTotalBetAmount());
          indicateBet(player);
        } else {
          player.bet(betAmount);
          view.addToActionLog(player.toString() + " bet $" + betAmount);
          interactiveActionComplete = true;
        }
      } catch (NumberFormatException e) {
        throw new ControllerException("Invalid bet input. Please enter a numeric value");
      } catch (ModelException e) {
        betScreenMessage = e.getMessage();
        indicateBet(player);
      }
    }
  }

  private void styleDialogBox(Dialog dialogBox) {
    dialogBox.getDialogPane().getStylesheets().add(getClass().getResource("/dukeTheme.css").toExternalForm());
    dialogBox.getDialogPane().getStyleClass().add("dialog-pane");
  }


  private void indicateFold(Player player) {
    interactiveActionComplete = true;
    player.fold();
    view.addToActionLog(player.toString() + " folded");
  }

  private void indicateCall(Player player) {
    interactiveActionComplete = true;
    player.call(lastBet);
    view.addToActionLog(player.toString() + " called");
  }

  private void indicateCheck(Player player) {
    interactiveActionComplete = true;
    view.addToActionLog(player.toString() + " checked");
  }

  private void indicateCashOut(Player player) {
    Alert cashOutConfirm = view
        .makeCashOutAlert(player.toString(), player.getBankroll().getValue());
    Optional<ButtonType> cashOutResult = cashOutConfirm.showAndWait();
    if (cashOutResult.get() == ButtonType.OK) {
      exitedPoker = true;
    }
  }

  private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Controller Error");
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void setInteractivePlayerStats(String name, int value){
    interactivePlayerName = name;
    playerStartingAmount = value;
  }

  public void setAlterState(){
    alterHands = true;
  }
  private void alterPlayerHands(Card card){
    for (Player player: playerList.getActivePlayers()){
      player.receiveCard(card);
    }
  }

  public void changeInteractiveActionCompletion(){
    interactiveActionComplete = false;
  }
}

