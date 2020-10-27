##DESIGN PLAN

__Introduction__

Our team is planning to create an application that allows the user to play the game of poker.
Several variations of poker will be playable, including Texas Hold 'em
and Omaha. The program will be split into engine, player, and data APIs that work together
to run the game. While the user will be able to choose from several preset variations, the
program will support modifications to many aspects of gameplay, such as number of cards each player is dealt,
the number of community cards, the types of cards used, hand ranks, visibility of cards. 
  
__Overview__

Overall, the engine of our program will drive the extensibility. We have planned on splitting up 
the program into modules that handle their own responsibilities, but are also general enough to 
work for all types of games. An overview of these modules and their responsibilities can be 
seen in the file doc/plan/CRC.md, along with notes that demonstrate some of the design considerations
for each that relate to extensibility. Additionally, we will have data API's that read in properties 
for a certain poker type, and the rules of the game (i.e the engine API implementation) will 
be run accordingly. Lastly, we will have an interactive GUI that is driven by a suite of player APIs,
and these will enable a host of different user options that working along the View and Controller will
move the flow of the game based on user actions and inputs. 

__Design Details__

As discussed, our APIs were planned out while keeping in mind the different variations of possible poker
games. The engine APIs, which will likely exist within the Model of our code, are the primary
handlers of poker variability and logic. There are a number of variations present within existing 
poker types in the real world, such as the number of communal cards that are dealt between 
rounds, the number of cards within each player's hand, or the number of cards from a player's 
hand that must be used to create a valid combination. Within the engine module, classes such as
 DealerRules (which can set the number of communal cards dealt by the dealer, and will be abstracted to 
 allow for more complicated dealer intricacies from game to game), or Win Manager (which can take in the 
 players remaining at the end of the final round and figure out, based on each player's hand 
 and the game type how to rank these players' hands), will be able to handle these variations. 
 Furthermore, we made it a point to consider other variations that might not be present in existing poker
 games, but could potentially be implemented in custom poker games. For example, changing the values of cards, 
 changing the actions of players, or changing the possible win combinations are all poker variations that we 
 felt our code should be able to support. The data module and its APIs will allow us to easily change certain
 attributes intrinsic to a poker game, and again, the engine model with classes and APIs such as 
 Player (which is designed for abstraction so that an AI player can make actions like betting in 
 different ways) will enable for this flexibility to be implemented on the Model level. 

__Example game__

Texas Hold'Em is a poker game that each player gets two hidden cards and after a series of betting and new communal cards, the player with the best five card ranking amongst the 5 communal cards and the 2 personal cards wins all the money in the pot.
Omaha is a poker game that each player gets four hidden cards and after a series of betting and new communal cards, the player with the best five card ranking amongst the 3 of the communal cards and all 2 personal cards wins all the money in the pot.
Seven Card Stud is a poker game that each player gets one hidden card and two visible cards and after a series of betting and new visible cards being given to each player until the sixth card, and after a final hidden card is given to each player, the player with the best five card ranking amongst the 7 personal cards wins all the money in the pot.
Clearly these games are different in the amount of cards each player gets, the cards that each player needs to use to make their hands, or the level of card visibility amongst other players at the table. Fortunately we have accounted for these variations in the design by making abstractions related to the rules and the players. This is allowing these specifics to be added to the rest of the code at a very primitive level.


__Design Considerations__

- How the rules will flow to the different classes that need them to do their job. 
- Utilizing a Queue for `Deck`
- Implementing a `Playable` interface that defines the different moves a player has for a given round such as `bet()`, `check()`, and `raise()`
- Implementing an abstract `Player` class from which `InteractivePlayer` and `ArtificialPlayer` are created


