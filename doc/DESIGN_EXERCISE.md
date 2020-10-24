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

This class's purpose is to handle the rules of the game. It can be abstracted to allow for 
multiple different subsets of rules
```java
 public abstract class Rules {
     
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

This class's purpose is to 
```java
 public class Hand {
     
 }
```

This class's purpose is to 
```java
 public class Deck {
     
 }
```

This class's purpose is to 
```java
 public class Player {
     
 }
```
### Use Cases

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```