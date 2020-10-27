Card


| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows value   | Player  |
| Knows suit   | Hand |
|  Knows it's visibility | Deck  |
|    |  |


Deck
* Deck size can differ 
* Number of decks? 

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Shuffle   |  Card  |
| Reset | Dealer |
| Get top card  |  |
| Knows size | |

Hand
* Size could differ

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows size  |  Card  |
| Knows all cards in hand | Player |
| | Dealer |
|  | |

Dealer
* Dealing rules differ
    * Number of cards it deals in a round
    * Order  

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Deals initial cards to players  |  Deck  |
| Deals communal cards | Player |
| Burns cards |  |
| Knows dealing rules| |

Player 
* Interactive vs AI player differ

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
| Knows its hand |  Dealer |
| Knows its money count | Hand |
| Bets | Card |
| Checks | Action |
| Folds | Pot |
| Calls |  |
| Knows if it's active in round | |

Turn Manager
* Order of players and rounds can differ

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows/ tells players who is up  |  Player |
| Knows who is in | Dealer |
|Knows what round it is| Win Manager|
| Knows when round is over ||
| Knows who is active in the game | |
| Knows when showdown is | |

Win Manager

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
| Compares remaining players after end of final round and see's who won  |  Player |
| Knows how many cards a hand needs to use for a valid combination|  Turn Manager |


Dealer Rules
* Who to deal to, when to deal, and how many cards to deal can differ
* When to deal communal cards, and how many communal cards to deal 

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
| Knows who is being dealt | Dealer |
| Knows how many cards to deal|  |
| Knows how many communal cards to deal|  |
| Knows when to deal communal cards| |

Pot

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows money count |  Player |
| Adds money to pot |  |
| Is able to split |  |
| Gives money out | |

Communal Cards

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Holds communal cards |  Dealer |
|  | Hand Evaluator |
|  | |
|  | |




