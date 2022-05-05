Interactive Poker Suite / Game Engine 
====

This project implements a poker engine for multiple related games.

Names: Arjun Peri, Christian Welch, Noah Besner, Yasser Elmzoudi


### Timeline :calendar:

Start Date: October 23, 2020

Finish Date: November 19, 2020

Hours Spent: 400

### Primary Roles :pencil:

- Arjun Peri: Dynamic poker variant implementation and backend-frontend integration (different poker game types, data file configuration, controller, etc).
- Chrisitan Welch: Interactive game setup and implementation (player select screen, file saving and loading, autoplayer funcionality, etc). 
- Noah Besner : Deep back-end poker game logic (hand evaluation, betting orders and raise seats, etc). 
- Yasser Elmzoudi: Front-end GUI setup and implementation (`Table`, `CardView`, `PlayerView`, CSS styling, etc).


### Running the Program :computer:

To run the program, run the main class, `PokerRunner` which is located in the PokerSuite package.

Data files needed: 
- Maven Dependency: org.json:json:202005182

- Card files 
    - Images for suits  
    - Card Back Image
    - Profile Picture Images
    - Pot Image
    - CSS Styling File
    - Card Settings File

- Poker game property files 
    - Texas Holdem: HoldEm.properties   
    - Five Card Draw: FiveCardDraw.properties  
    - Seven Card Stud: SevenCardStud.properties

    - And any more of your choosing!

Features implemented:
- 3 poker "archetypes": Stud, Draw, and Community Card games
    - These three poker types all differ greatly in the way they are played, but our code also allows for customization within these archetypes. For 
    example, instead of playing five card stud, one could easily change values in the configuration files to switch the game to three card draw.
        - Community card games: players have their own hand but also play with shared cards on the table
        - Draw games: players have the option to exchange cards  
        - Stud game: players play with a combination of face up and face down cards
    - In addition, our code allows for combinations of the game types, essentially allowing the user to create a "custom" poker game types. For 
    example, values within the properties files could be changed to make a game that combines aspects of Texas Holdem, Five Card Draw, and Seven Card 
    Stud all at once. 
- Player loading from saved files and creation of new players.
- Interactive GUI that allows for multiple in game options to simulate a real poker game flow as well as enabling navigating around the program with ease. 
- Action log to record all game events from interactive player and autoplayers. 
- Robust exception handling so that the various errors that could occur in a simulated poker game (e.g the deck running out of cards based on too many
players at the table) can be accounted for. 
- CSS stlying to customize our GUI. 
- Variety of other cusomtizable options with cards, gameplay options, GUI styling, and much more. 
    - For cards, the user can change things such as the card rank mappings, suits, card images, and more so that the actual dynamic of the game can be 
    completely changed while still not breaking the program. 



### Notes/Assumptions :clipboard: 

Assumptions or Simplifications:
- You can only chose between 1 and 7 opponents
    - exception is thrown if otherwise
- 52 card deck
    - exception handling is implemented to make sure the combination of the number of players and cards dealt never exceeds 52.
- 10 cards maximum can be dealt to each player or the table     
    - exception is thrown if otherwise
- Hands are evaulated on a 5-card basis
- Only one interactive player allowed. 
- You need to cash out before leaving the game (to simulate a real casino). 

Interesting data files: 
 - HoldemDrawCombo.properties
    - combines aspects of texas holdem and five card draw (exchanging cards while also playing with shared Community cards)
 - We will continue to add more custom game types to explore how different poker variants can be combined together

Known Bugs: 
- When running all tests, the user needs to let the program run uninterrupted until completion otherwise tests may fail (clicking and entering dialog boxes needs to happen without the mouse moving).
- When running tests, press cancel once the file chooser pops up.
- After saving a player's information to a file, the program must be restarted for the save to be updated and accessable in game. We atttempted to debug this issue, but after doing some research we discovered this issue might be inherent because of the way files are read/ written during runtime. 
- When using the choice dialog for the action menu (bet check fold call etc), you need to click off the button (i.e if you want to click the "call" button, click on the space next to the text that says "call") in order for the click to register. Research shows that this is a common bug with JavaFX choice dialogs. 

Additional Features:
- Loading and Saving
- Smart Autoplayer
- Player Profiles
- Preferences (`CardView` suit image, `CardView` back, `PotView` Image, Languages, Amount of Players, and Difficulty are all customizable)


### Impressions :thumbsup:

Poker is on its own merit a very complicated and intricate game. Thus, most of the work on this project came during our implementation of the back end logic and progression that make up the pokerModel APIs. However, our group came in with a significant amount of background knowledge that helped us to work through the most difficult aspects of the project. This project has given us a new appreciation for and understanding of the amount of time and effort that goes into developing deliverable software. 
