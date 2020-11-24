ROCK SOLID
POKER

PRESENTATION

- Noah Besner
- Yasser Elmzoudi
- Arjun Peri
- Christian Welch 

---

FUNCTIONALITY

---

Player Menu 
    - Creating a player
    - Loading a player 

---

Navigating the GUI
- Full poker functionality 
    - Buttons
    - Dialog Boxes
    - User inputs
    - Cancelling out of choices
        - Leaving game

---

Real-time updating
- Action log
- Player bankroll
- Pot total 

---

3 Working Poker Archetypes 
- Community Card Games - Texas Hold'em
- Draw Games - Five Card Draw
- Stud Games - Seven Card Stud
- Full round & betting progression & hand evaluation
    - Dynamic autoplayer 

---

Data Files 
- Custom games based entirely on properties files inputs 
    - Wide array of specifications matched with wide array of exception handling
- Player profile properties files 
- JSON files and card customization

---

Exception Handling 
- Typical input errors
- Deeper logic-based poker errors

---

DESIGN

---

Design Goals Revisited
- Extremely flexible & open - custom games based entirely on configuration files
    - Reflection used for different types of poker rounds
    - Betting order and progression determined from PlayerList abstraction
    - Limits of file inputs are handled with exceptions
    - JSON files for card settings

---

API's

---

API 1: CardRecipient

---

Public Methods: 
```java    
public abstract void receiveCard(Card card);
public void clearNewCards();
public List<Card> getNewCards()    
```

---

Discussion
- Adding in any new object that might receive cards would just need to extend
- Team members can generalize parameters 
- New cards featured implemented as frontend intergration progressed

---

Use Case
- Dealer Class: 
```java    
public void dealCards(CardRecipient recipient, List<Boolean> visibilityList) {
    recipient.clearNewCards();
    for (boolean isVisible : visibilityList) {
      Card cardDealt = deck.getTopCard();
      if (isVisible) {
        cardDealt.makeBackEndVisible();
      }
      recipient.receiveCard(cardDealt);
    }
  }
```

---


API 2: CardGrid

---

Public Methods:
```java    
  public void addCardView(CardView card);

  public void addCardViewToLocation(CardView card, Point2D location);
  
  public Point2D removeCard(CardView card);
  
  public void clearCardGrid();

  public void flipCards();
  
  public Set<CardView> getSelectedCards();
```

---

Discussion
- Utilization of Composition  
- Aligns with Backend functionalities 
- Created during final Sprint to ease GUI Integration 

---

Use Case

```java    
  private void dealFrontEndCardsInRound(CardRecipient recipient, CardGrid cardGrid) {
    for (Card newCard : recipient.getNewCards()) {
      CardView displayCard = getFrontEndCard(newCard);
      cardGrid.addCardView(displayCard);
    }
  }
```

---

Stable vs. Dynamic Designs

---

Stable Design: Player
- Having a player class that extends card recipient and is open for extension for other players (e.g autoplayers)
- Autoplayers have decideAction method which in turn calls upon Player action methods

---

Dynamic Design: Rules/Model
- Began with Model superclass and three abstract Model subclasses for each archetype
- As program became more data-driven, differences between subclasses decreased
- In the end, realized that at their core, the models all worked the same - removed abstraction

---

TEAM

---

Completed vs. Planned Project

---

Sprint 1: Expectations vs. reality
- Set up the primary general classes to be used in the MVC layout
- Have one functioning casino game (Yasser)
- Have testing structure for the one casino game in order to base future testing on (Christian)
- Focus on implementing a basic view (Arjun)

---

Sprint 2: Expecations vs. reality:
- Complete the remaining casino games (Noah and Yasser)
- Add element of user interactivity with the game (theme, language, error-support) (Arjun and Christian)

---

Sprint 3: Expectations vs. reality:
- Integrate the casino games into a seamless experience (Noah and Christian)
- Create more user interactivity (Arjun and Yasser)

---

Wireframe comparison
- Not all features implemented, but styling exceeded expectations

---

![](https://i.imgur.com/L78hERd.png)


---

![](https://i.imgur.com/0iupTUE.png)

---

![](https://i.imgur.com/X7d3ycY.png)

---

Learnings From Agile/Scrum Management Process

---

Arjun 
- Having to demo early and often enforced a strict work regimen 

---

Noah
- Breaking the project into chunks made it a lot less daunting 

---

Yasser
- The Agile principle of a transparent process made the separate parts of the project come together naturally

---

Christian
- Integrating different modules of project early (even if rudimentary) was key 

---

Significant Events & Communication

---

First Integration of Frontend & Backend 

---

Functional Hand Evaluation and Poker Showdown

---

Final Integration of Frontend & Backend 

---

Handling the Multitude of Possible Errors

---

Large-Scale Project Management

---

Christian
- Communication (both electronically and in person) was extremely imperative 

---

Yasser
- Limiting yourself to one role can harm the overall progress of the group and restrict the extent of your possible contributions. 

---

Noah
- It was extremely important to stay ahead of tasks to allow time for solving unforseen issues

---

Arjun 
- A large project can only progress forward if the worst parts of it (bugs, design issues, etc) are accounted for

---

Team Improvement (& Steps to be Taken)

---

Expanding Role Definitions 

---

Pair Programming

---

Adherence to Sprint Deadlines and Management 

---

Needs to Improve: Willingness to Reach Out for Help

---

Fostering Positive Team Culture 

---

Yasser
- Being able to meet up in person allowed us to foster a positive team outlook 

---

Christian
- Having an evironment where everyone feels comfortable engaging with the team in a meaningful way is neccesary to bring the best out of each member

---

Arjun 
- Keeping spirits and team morale high during stressful times is as important as putting in the work to get through those times

---

Noah
- Increasing team chemistry through events that might not be project-related showed a significant increase in team commitment and productivity

---

Team Contract Review

- Useful
    - Mandating respect of ideas and contributions
    - Fair, democratic delegation of tasks
- Needs updating 
    - Frequency of team meetings
    - Strictness of meeting schedules and group decision criteria
- Needs adding 
    - Required initial discussion of individual schedules!


---

Handling Team Issues and Working Through Team Dillemmas

---

Arjun
- Recognizing when entitativity versus when individuality should be prioritized is key to avoiding attrittion

---

Christian
- It is important to address team conflicts immediately so that change can be made and also so that there is no time for resentment to fester

---

Yasser
- Checking in often on teammates to see not only how they are doing on the project, but also how they are feeling about their role is neccesary for group members to stay motivated.

---

Noah 
- Having a mediator during team debates and dillemmas really helped keep emotions calm and conversations focused on code and logic. 

---

CONCLUSION 