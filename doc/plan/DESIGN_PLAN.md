##DESIGN PLAN

__Introduction__

Our team is planning to create an application that allows the user to play the game of poker.
Several variations of poker will be playable, including Texas Hold 'em
and Omaha. The program will be split into engine, player, and data APIs that work together
to run the game. While the user will be able to choose from several preset variations, the
program will support modifications to many aspects of gameplay, such as number of cards each player is dealt,
the number of community cards, the types of cards used, hand ranks, visibility of cards. 
  
__Overview__

__Design Details__

__Example game__

Texas Hold'Em is a poker game that each player gets two hidden cards and after a series of betting and new communal cards, the player with the best five card ranking amongst the 5 communal cards and the 2 personal cards wins all the money in the pot.
Omaha is a poker game that each player gets four hidden cards and after a series of betting and new communal cards, the player with the best five card ranking amongst the 3 of the communal cards and all 2 personal cards wins all the money in the pot.
Seven Card Stud is a poker game that each player gets one hidden card and two visible cards and after a series of betting and new visible cards being given to each player until the sixth card, and after a final hidden card is given to each player, the player with the best five card ranking amongst the 7 personal cards wins all the money in the pot.
Clearly these games are different in the amount of cards each player gets, the cards that each player needs to use to make their hands, or the level of card visibility amongst other players at the table. Fortunately we have accounted for these variations in the design by making abstractions related to the rules and the players. This is allowing these specifics to be added to the rest of the code at a very primitive level.


__Design Considerations__

How the rules will flow to the different classes that need them
to do their job. 

