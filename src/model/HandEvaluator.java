package model;

public class HandEvaluator {

    public boolean isFlush(Hand hand){
        Suit flushSuit = hand.get(0).getCardSuit();
        for(Card card : hand.getCards()){
            if(card.getCardSuit()!=flushSuit){
                return false;
            }
        }
        return true;
    }


    public boolean isStraight(Hand hand) {
        for (int i = 1; i < hand.getHandSize(); i++) {
            if (hand.get(i).getRank() != (hand.get(i - 1).getRank() - 1)) {
                if(hand.get(0).getRank()==14){
                    Suit aceSuit = hand.get(0).getCardSuit();
                    hand.remove(hand.get(0));
                    hand.add(new Card(1,aceSuit));
                    return isStraight(hand);
                }
                return false;
            }
            if(hand.get(1).getRank()==5&&hand.get(0).getRank()!=6){

            }
        }
        return true;
    }

    public boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }
}
