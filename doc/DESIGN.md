# OOGA Design Final
### Names 
Noah Besner

Arjun Peri

Yasser Elmzoudi

Christian Welch

## Team Roles and Responsibilities

Noah Besner : Deep back-end poker game logic (hand evaluation, betting orders and raise seats, etc).

Yasser Elmzoudi: Front-end GUI setup and implementation (Table, CardView, PlayerView, CSS styling, etc).

Arjun Peri: Dynamic poker variant implementation and backend-frontend integration (different poker game types, data file configuration, controller, etc).

Chrisitan Welch: Interactive game setup and implementation (player select screen, file saving and loading, autoplayer funcionality, etc).


## Design goals

#### What Features are Easy to Add
* We wanted to make it easy to add new game variations.
    - In order to add a new game variation, all that needs to be done is to create property files that followed the correct format for reading the properties of the game and place it in the properties directory of the project. 
 The game properties files can be found and created in the properties directory.
  The first part of the properties file dictates how the order of the players is initialized. There are two types of player lists: standard and stud. The standard player list type initializes the players in order based on a simple rotation of who goes first after every round. The stud player list type initializes the order of the players based on the stregth of the visible cards of each player. The next part of the properties file determines the max number of cards a player can exchange in an exchange round. If a game does not have exchange rounds, that number will be zero. The next part of the properties file is a number that dictats the amount of rounds (either betting or exchange rounds) that take place in one hand of the poker game. For example, Holdem has four betting rounds, so that number is four. The next part of the properties file dictates the destination of cards at the beginning of each of the corresponding round number in one hand of poker. The two destination types are either Community or Player. The next part of the file determines how many cards are dealt each round and also the visibility of each of those cards to the rest of the table. The first digit represents the amount of cards dealt that round to the corresponding destinations that are not visible to all the players and the second digit represents the amount of cards that are dealt in that round that are visible to each player. The last part the file should be the type of action that goes on in each of the rounds. There can either be a dealing round in which new cards are dealt to the correct card destination, or exchange rounds in which no cards are dealt, but players are given the opportunity to exchange a number of cards that is less than or equal to the max number of exchange cards. In all of the last three parts, the number to the left of the equal sign needs to correspond to the round number in which they take place and there needs to be the same amount of rounds for each of these aspects that also match the amount of rounds specified at the top of the properties file. Give the file the correct name and you can pick and play that game when you choose to play a custom game on the game select screen on the main menu.


* We also wanted to make it easy to alter different card settings. The way our program accounts for this is found in our `cardSettings.json` file. We elected to utilize a JSON file for this information due to the fact that, by utilizing certain libraries, we were easily able to parse these JSON files and the content of the card settings. This was especially important considering that this file primarily served to map backend information to its frontend equivalent. Specifically, this file contains:
     - A String representing the card back image to be used for nonvisible cards
     - A mapping of suits from their backend distinguisher to their front end displayed image  
     - A mapping of ranks from their backend rank to their front end displayed text
     - A mapping of hand rankings from their backend "weight" to their front end displayed text 


## High-level Design

#### Core Classes

* `PokerRunner` is our main class that simply runs the program for the player to use.

* `Card` is the class for which the entire game is built around. A Card has a suit and a rank that are read in from the JSON file, as well as booleans that dictate whether the card should be visibile on the front end as well as if the card belongs to the interactive player (as opposed to the Auto player).

* The `Deck` class is initialized based on the amount of ranks and suits read in from the JSON file in order to make a stack of 52 unique card objects.

* The `Dealer` handles the deck class and is responsible for taking cards from the deck and giving the card to the correct card recipient.

* The `CardRecipient` abstract class recieves cards from the dealer. The Player class and the CommunityCard class extend card recipient because they the objects that recive cards from the dealer.

* The `Player` class is responsible for player actions (like betting and folding) as well as holding a hand of cards. There are two subclasses of player (interactive and auto players) that extend the player class as well.

* The `Hand` class is responsible for holding the cards that are accessible to the player.

* The `HandEvaluator` class is responsible for evaluating the strength of a player's hand by using the hand checking and hand formatting classes to determine the strength of a player's hand.

* The `PokerModel` class dictates the properties of the game and invokes the back end dealing process.

* The `Controller` class is responsible for back end and front end collaboration. This class allows for actions that take place on the front end to be understood by the back end, and vice-versa. Generally speaking, the game is functional because of the Controller's ability to allow both ends of the project to communicate with each other.

* The `PlayerList` abstract class is responsible for tracking all the player's movements and actions. The two subclasses (StandardPlayerList and StudPlayerList differ only on initializng the order of the player list).

Interaction of Classes: 
The view package is responsible for handling front end visuilization for the user interface. The model package is responsible for back-end game flow and logic. The view package is responsible for coordinating changes between the model and view packages. The View package is easy to use because you can see all the aspects on the user interface and if there is an error it would be apparent. The model is easy to use because it is well tested and any errors would present themselves throught the ample testing suite. The controller is easy to use because any error that takes place within the program can be easily debugged within the controller because all interactions between the view and the model will be picked up there. The view hides the specifics of the button initializations from the controller and the model. The model hides the data structures of the card initializations and other more basic building structures from the controller and the view. The controller hides user inputs from the view and the controller and simply gives them the corresponding actions.

## Assumptions that Affect the Design
  
  
*  52 card deck (exception handling is implemented to make sure the combination of the number of players and cards dealt never exceeds 52).
  
  
*  10 cards maximum can be dealt to each player or the table (exception is thrown if otherwise)
  
 
*  Hands are evaulated on a 5-card basis
  
*  Only one interactive player allowed.
  
 * You need to cash out before leaving the game (to simulate a real casino).
#### Features Affected by Assumptions

* You can only chose between 1 and 7 opponents (exception is thrown if otherwise)


## Significant differences from Original Plan


* We initially had an idea for a Rules API that involved a Model superclass and a
subclass for each poker archetype. After making our code more data driven, we soon
realized that the differences between these subclasses was minimal. Thus, 
we eliminated all the duplicated code and refactored it all out into just one 
Model class. 

## New Features HowTo

#### Other Features not yet Done
* If we had more time, we would build functionality for a Credits tab to be filled in with text Credits fill it with our names and responsibilities
* Another feature we would add if we had more time, would be to make the game more customizable so that a player could choose an image to be put on the back of the cards and pick an avatar to be used for their player icon. Implementing this would consist of adding a file chooser
