package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    protected ArrayList <Card> hand; //array list of of cards to hold  players hand
    protected int handScore; //an int that will represent the 'power' of the hand. used with evaluateHand() in hands.java
    protected int playerNumber;

    public Player(Card FirstCard, Card SecondCard,
                  Card ThirdCard, Card FourthCard, Card FifthCard){
        hand = new ArrayList <Card>();

        hand.add(FirstCard);
        hand.add(SecondCard);
        hand.add(ThirdCard);
        hand.add(FourthCard);
        hand.add(FifthCard);

        //sort cards highest to lowest Rank
        for(int x=0; x<hand.size(); x++){
            for(int y=0; y<hand.size(); y++){
                if(hand.get(x).getRankEnum().ordinal() > hand.get(y).getRankEnum().ordinal())
                    Collections.swap(hand, x, y);
            }//end y loop
        }//end x loop

        this.handScore = -1;
    }

    public void swapCards(){}

    public void showCards(){
        for(int i = 0; i <hand.size(); i++) //loop through hand
        {
            System.out.print(hand.get(i).getRankChar() + "" + hand.get(i).getSuitChar() + " ");
        }
        System.out.println("");
    }

    public ArrayList <Card> getHandArrayList(){
        return this.hand;
    }

    public int getHandScore() {
        return handScore;
    }

    public void setHandScore(int handScore) {
        this.handScore = handScore;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
