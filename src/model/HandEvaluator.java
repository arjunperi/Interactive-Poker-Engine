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
                    Hand tempHand = hand.copyHand();
                    tempHand.remove(hand.get(0));
                    tempHand.add(new Card(1,aceSuit));
                    return isStraight(tempHand);
                }
                return false;
            }

        }
        return true;
    }

    public boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public boolean isFourOfAKind(Hand hand){
        if(rankCount(hand,hand.get(0).getRank())==4 || rankCount(hand,hand.get(1).getRank())==4){
            return true;
        }
        return false;
    }


    public boolean isFullHouse(Hand hand){
        if((rankCount(hand,hand.get(0).getRank())==3 && rankCount(hand,hand.get(3).getRank())==2 )|| (rankCount(hand,hand.get(0).getRank())==2 && rankCount(hand,hand.get(2).getRank())==3)){
            return true;
        }
        return false;
    }

    public boolean isThreeOfAKind(Hand hand) {
        if ((rankCount(hand, hand.get(0).getRank()) == 3)|| (rankCount(hand, hand.get(1).getRank()) == 3) || (rankCount(hand, hand.get(2).getRank()) == 3)){
            return true;
        }
        return false;
    }


    public boolean isTwoPair(Hand hand){
        if((rankCount(hand,hand.get(1).getRank())==2 && rankCount(hand,hand.get(3).getRank())==2 )){
            return true;
        }
        return false;
    }

    public boolean isPair(Hand hand){
        for (Card card : hand.getCards()){
            if((rankCount(hand,card.getRank()))==2){
                return true;
            }
        }
        return false;
    }

    public boolean isHighCard(Hand hand) {
        if (isStraight(hand) || isFlush(hand)) {
            return false;
        }
        for (Card card : hand.getCards()) {
            if ((rankCount(hand, card.getRank())) > 1) {
                return false;
            }
        }
        return true;
    }

    public int rankCount(Hand hand, int rank){
        int rankCount=0;
        for(Card card : hand.getCards()){
            if(card.getRank()==rank){
                rankCount++;
            }
        }
        return rankCount;
    }
}
