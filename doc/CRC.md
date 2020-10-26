Card

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows value   | Player  |
| Knows suit   | Hand |
|    | Deck  |
|    |  |


Deck

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Shuffle   |  Card  |
| Reset | Dealer |
| Get top card  |  |
| Get size | |

Hand

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows size  |  Card  |
| Knows all cards in hand | Player |
|  | Dealer |
|  | |

Dealer

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Deals initial cards to players  |  Deck  |
| Deals communal cards | Player |
| Burns cards |  |
|  | |

Player 
- Interactive Player
- AI Player

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
| Knows its hand |  Dealer |
| Knows its money count | Hand |
| Bets | Card |
| Checks | Action |
| Folds | Pot |
| Calls |  |
| Knows if it's active in round | |
| Knows if it's at showdown point | |
| Card activity | |

Turn Manager

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows/ tells players who is up  |  Player |
| Knows who is in | Dealer |
|Knows what round it is|  |
| Knows when round is over | |

Pot

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|  Knows money count |  Player |
| Adds money to pot |  |
| Is able to split |  |
| Gives money out | |

Rules

| Responsibilities      | Collaborators    |
| :------------- | :----------: |
|Knows hand ranks | |
|  |  |
|  |  |
