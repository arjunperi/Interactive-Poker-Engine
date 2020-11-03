package controller;


import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.FrontEndCard;
import view.GameDisplayRecipient;
import view.GameView;



import java.awt.desktop.ScreenSleepEvent;
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

    private GameDisplayRecipient community;


    private GameView view;
    private Stage stage;

    private int roundNumber;
    private int numberOfCards;
    private String recipient;

    private int xOffset;


    private Stack<Card> cardsRemoved;

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

        cardsRemoved = new Stack<>();
        view = new GameView();
        initializeFrontEndPlayers();
        initializeCommunity();

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
        community = new GameDisplayRecipient(50,50);
    }



    //might be able to combine these two into one method
    //should these be in View or Controller?
    private void initializeFrontEndPlayers(){

        int playerOffset = 0;
        for (Player currentPlayer: playerList.getAllPlayers()){
            System.out.println(currentPlayer.toString());
            GameDisplayRecipient player = new GameDisplayRecipient(playerOffset,playerOffset);
            System.out.println(playerOffset);
            frontEndPlayers.add(player);
            playerOffset += 20;

        }
    }

    private void updateFrontEndPlayers(){
        frontEndPlayers.clear();
        for (Player activePlayer : playerList.updateActivePlayers()){
            GameDisplayRecipient player = new GameDisplayRecipient(100,100);
            frontEndPlayers.add(player);
        }
    }


    private void bettingRound(){

        //just dealt the cards
        //prop
        updateFrontEndPlayers();
        for (GameDisplayRecipient player: frontEndPlayers){
            view.promptAction(player);
        }

       //for each player in the front end player list
            //pop up an option box allowing them to either bet raise call check fold (if valid)
            //send this information back to the backend
            //the backend updates the pot, player totals, and decides who's turn it is
                    //or if the round is over
                    //or if it is showdown time
                    //or if the game is over?
    }

//
//    public void startBettingRound(PlayerList pokerPlayerList, int totalRounds){
//        List<Player> allPlayers = pokerPlayerList.getAllPlayers();
//        for (Player currentPlayer: allPlayers){
//            System.out.println("\n" + currentPlayer.toString() + " is up");
//            if (currentPlayer.isActive()){
//                currentPlayer.performAction();
//            }
//            activePlayers = pokerPlayerList.updateActivePlayers();
//            if (activePlayers.size() == 1 ){
//                winner = activePlayers.get(0);
//                endGame();
//            }
//        }
//        currentRound ++;
//        if (currentRound == totalRounds){
//            showDown(activePlayers);
//        }
//    }

    private void promptAction(){
        //once action is selected, update the totals and then call
    }

}
