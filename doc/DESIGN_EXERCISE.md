# OOGA Lab Discussion
## Names and NetIDs
Arjun Peri - ap458
Noah Besner - nkb13
Yasser Elmzoudi - ye9
Christian Welch -  cw356

## Fluxx

### High Level Design Ideas


### CRC Card Classes

This class's purpose is to handle the end goal of the game, and how it might change
```java
 public class Goal {
     public boolean goalReached(){}
     public void changeGoal(){}
 }
```

This class's purpose is to handle the ungoal of the game, and how it might change
```java
 public class Ungoal {
     public boolean ungoalReached(){}
     public void changeUngoal(){}
 }
```

This class's purpose is to handle the rules of the game. It can be abstracted to allow for 
multiple different subsets of rules
```java
 public abstract class Rules {
    public void setHandSize(int handSize){}
    public void setPlaySize(int playSize){}
 }
```

This class's purpose is to handle Card objects. It will be abstracted to allow for the different 
types of cards and their respective actions. 
```java
 public abstract class Card {
    public Card getType(){}
    abstract void getJob(){}
 }
```

This class's purpose is to represent the cards available for 
a certain player. 
```java
 import java.util.Queue;public class Hand {
    public Queue getCards(){}
 }
```

This class's purpose is to represent the Deck of remaining cards 
and get the next cards available. 
```java
 public class Deck {
    public Card getNextCard(){
        return Deck.pop();
 }
```

This class's purpose is to represent a player of the game.
```java
 public class Player {
     public Card playCard(Card card){}
     public void drawCard(){}
 }
```

This class's purpose is to run the actual gameplay itself.
```java
 public class Game {
     public void runGame(){}
 }
```

### Use Cases

 * A player plays a Goal card, changing the current goal, and wins the game.
 
 A player plays a goal card. Goal is updated. Each player checks if they have won the game. Player has won game.
 
 * A player plays an Action card, allowing him to choose cards from another player's hand and play them.
 
 A player plays action card. Action is read. Player picks player. Player reads cards. Player picks cards. Player plays card.
 
 * A player plays a Rule card, adding to the current rules to set a hand-size limit, requiring all players to immediately drop cards from their hands if necessary.
 
 A player plays rule card. Rules updated. Rules read. Hand size checked. Pick cards. Discard cards.
 
 * A player plays a Rule card, changing the current rule from Play 1 to Play All, requiring the player to play more cards this turn.

A player plays rule card. Rules updated. Rules read. Player plays card.

* A player plays a card, fulfilling the current Ungoal, and everyone loses the game.


