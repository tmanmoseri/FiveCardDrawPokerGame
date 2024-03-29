package org.example;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hands {
    private int numberOfPlayers;
    private int numberOfAIPlayers;
    private ArrayList <AI_Player> AI_Players;
    private Player you;
    Scanner keyboard = new Scanner(System.in);

    //GET NUMBER OF AI_PLAYERS
    public Hands(Draw gameDeck){
        System.out.println("How many AI players do you want? Enter 1, 2, or 3.\n");
        String numberofAIPlayers = keyboard.nextLine();
            while (!numberofAIPlayers.matches("[0-9]+")) {
                System.out.print("Please type numbers only.Try again:\n How many AI players do you want? Enter 1, 2, or 3.\n");
                numberofAIPlayers = keyboard.nextLine();
            }
            while( Integer.parseInt(numberofAIPlayers) <= 0 || Integer.parseInt(numberofAIPlayers) >3){
                System.out.print("Please type numbers between 1 an 3.Try again:\n How many AI players do you want? Enter 1, 2, or 3.\n");
                numberofAIPlayers = keyboard.nextLine();
                while (!numberofAIPlayers.matches("[0-9]+")) {
                    System.out.print("Please type numbers only.Try again:\n How many AI players do you want? Enter 1, 2, or 3.\n");
                    numberofAIPlayers = keyboard.nextLine();
                }
                numberOfAIPlayers = Integer.parseInt(numberofAIPlayers);

        }
        numberOfPlayers = numberOfAIPlayers + 1;


        you = new Player(gameDeck.drawCard(gameDeck),
                gameDeck.drawCard(gameDeck),
                gameDeck.drawCard(gameDeck),
                gameDeck.drawCard(gameDeck),
                gameDeck.drawCard(gameDeck));


        AI_Players = new ArrayList<AI_Player>();
        for(int i=1; i <= numberOfPlayers; i++){


            AI_Players.add(new AI_Player(gameDeck.drawCard(gameDeck),
                    gameDeck.drawCard(gameDeck),
                    gameDeck.drawCard(gameDeck),
                    gameDeck.drawCard(gameDeck),
                    gameDeck.drawCard(gameDeck), i));

        }//end loop
        int num = 1;
        for(AI_Player a : AI_Players)
        {
            a.setAIPlayerNumber(num);
            num++;
        }
    }// end Hands constructor

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfAIPlayers() {
        return numberOfAIPlayers;
    }

    public void setNumberOfAIPlayers(int numberOfAIPlayers) {
        this.numberOfAIPlayers = numberOfAIPlayers;
    }


    public int evaluateHand(Player player){

        for(int x=0; x<=4; x++){
            for(int y=0; y<=4; y++){
                if(player.getHandArrayList().get(x).getRankEnum().ordinal() > player.getHandArrayList().get(y).getRankEnum().ordinal())
                    Collections.swap(player.getHandArrayList(), x, y);
            }
        }


        int firstCardRankOrdinal = player.getHandArrayList().get(0).getRankEnum().ordinal();
        int secondCardRankOrdinal = player.getHandArrayList().get(1).getRankEnum().ordinal();
        int thirdCardRankOrdinal = player.getHandArrayList().get(2).getRankEnum().ordinal();
        int fourthCardRankOrdinal = player.getHandArrayList().get(3).getRankEnum().ordinal();
        int fifthCardRankOrdinal = player.getHandArrayList().get(4).getRankEnum().ordinal();

        int firstCardSuitOrdinal = player.getHandArrayList().get(0).getSuitEnum().ordinal();
        int secondCardSuitOrdinal = player.getHandArrayList().get(1).getSuitEnum().ordinal();
        int thirdCardSuitOrdinal = player.getHandArrayList().get(2).getSuitEnum().ordinal();
        int fourthCardSuitOrdinal = player.getHandArrayList().get(3).getSuitEnum().ordinal();
        int fifthCardSuitOrdinal = player.getHandArrayList().get(4).getSuitEnum().ordinal();


        if((firstCardRankOrdinal == secondCardRankOrdinal + 1) &&
                (firstCardRankOrdinal == thirdCardRankOrdinal + 2)  &&
                (firstCardRankOrdinal == fourthCardRankOrdinal + 3)  &&
                firstCardRankOrdinal == fifthCardRankOrdinal + 4 &&
                firstCardSuitOrdinal == secondCardSuitOrdinal &&
                secondCardSuitOrdinal == thirdCardSuitOrdinal &&
                thirdCardSuitOrdinal == fourthCardSuitOrdinal &&
                fourthCardSuitOrdinal == fifthCardSuitOrdinal){
            //System.out.println("straight flush - returns 8\n");

            player.setHandScore(8);
            return 8;
        }
        //check if the first card was an Ace to check straight as a low card
        else if(firstCardRankOrdinal == 12 && (firstCardRankOrdinal == secondCardRankOrdinal + 9) &&
                firstCardRankOrdinal == thirdCardRankOrdinal + 10  &&
                firstCardRankOrdinal == fourthCardRankOrdinal + 11  &&
                firstCardRankOrdinal == fifthCardRankOrdinal + 12 &&
                firstCardSuitOrdinal == secondCardSuitOrdinal &&
                secondCardSuitOrdinal == thirdCardSuitOrdinal &&
                thirdCardSuitOrdinal == fourthCardSuitOrdinal &&
                fourthCardSuitOrdinal == fifthCardSuitOrdinal){

            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);

            you.showCards();

            player.setHandScore(8);
            return 8;
        }


        else if((firstCardRankOrdinal == secondCardRankOrdinal) &&
                (firstCardRankOrdinal == thirdCardRankOrdinal)  &&
                (firstCardRankOrdinal == fourthCardRankOrdinal)){
            //System.out.println("four of a kind on 1st 4 cards - returns 7\n");

            player.setHandScore(7);
            return 7;
        }
        else if ((secondCardRankOrdinal == thirdCardRankOrdinal) &&
                (secondCardRankOrdinal == fourthCardRankOrdinal)  &&
                (secondCardRankOrdinal == fifthCardRankOrdinal)){


            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);
            you.showCards();

            player.setHandScore(7);
            return 7;
        }
        //check for Full House 3 pairs then 2 pair
        else if((firstCardRankOrdinal == secondCardRankOrdinal) &&
                (firstCardRankOrdinal == thirdCardRankOrdinal) &&
                (fourthCardRankOrdinal == fifthCardRankOrdinal) &&
                (firstCardRankOrdinal != fifthCardRankOrdinal)){
            //System.out.println("full house 3 pair, 2 pair\n");

            player.setHandScore(6);
            return 6;
        }
        //check for Full House 2 and 3...
        else if((firstCardRankOrdinal == secondCardRankOrdinal) &&
                (thirdCardRankOrdinal == fourthCardRankOrdinal) &&
                (thirdCardRankOrdinal == fifthCardRankOrdinal) &&
                (firstCardRankOrdinal != fifthCardRankOrdinal)){
            //System.out.println("full house 2 pair, then 3 pair\n");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);
            Collections.swap(player.getHandArrayList(), 0, 3);

            player.setHandScore(6);
            return 6;
        }
        //check for flush. return 5
        else if(firstCardSuitOrdinal == secondCardSuitOrdinal
                && thirdCardSuitOrdinal == fourthCardSuitOrdinal &&
                fourthCardSuitOrdinal == fifthCardSuitOrdinal &&
                firstCardSuitOrdinal == thirdCardSuitOrdinal &&
                firstCardSuitOrdinal == fourthCardSuitOrdinal &&
                thirdCardSuitOrdinal == fifthCardSuitOrdinal){
            //System.out.println("flush\n");

            player.setHandScore(5);
            return 5;
        }
        //check for straight. return 4
        else if((firstCardRankOrdinal == secondCardRankOrdinal + 1) &&
                (firstCardRankOrdinal == thirdCardRankOrdinal + 2)  &&
                (firstCardRankOrdinal == fourthCardRankOrdinal + 3)  &&
                firstCardRankOrdinal == fifthCardRankOrdinal + 4){
            //System.out.println("straight - return 4\n");

            player.setHandScore(4);
            return 4;
        }
        //check for a straight with an ace
        else if(firstCardRankOrdinal == 12 && (firstCardRankOrdinal == secondCardRankOrdinal + 9) &&
                firstCardRankOrdinal == thirdCardRankOrdinal + 10  &&
                firstCardRankOrdinal == fourthCardRankOrdinal + 11  &&
                firstCardRankOrdinal == fifthCardRankOrdinal + 12){

            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);

            //System.out.println("straight with an ace");

            player.setHandScore(4);
            return 4;
        }
        //Three of a Kind on 1st 3 cards - ret		//UNCOMMENT THE FOLLOWING 6 LINES TO TEST HUMAN PLAYERS CARDS

        else if(firstCardRankOrdinal == secondCardRankOrdinal &&
                secondCardRankOrdinal == thirdCardRankOrdinal){
            //System.out.println("Three of a kind on 1st 3 cards");

            player.setHandScore(3);
            return 3;
        }
        //Three of a Kind on 2nd 3 cards - returns 3
        else if(secondCardRankOrdinal == thirdCardRankOrdinal &&
                thirdCardRankOrdinal == fourthCardRankOrdinal)
        {
            //System.out.println("Three of a kind on 2st 3 cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);

            player.setHandScore(3);
            return 3;
        }
        //Three of a Kind on last 3 cards - returns 3
        else if(thirdCardRankOrdinal == fourthCardRankOrdinal &&
                fourthCardRankOrdinal == fifthCardRankOrdinal)
        {
            //System.out.println("Three of a kind on 3st 3 cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);

            you.showCards();

            player.setHandScore(3);
            return 3;
        }
        //check for Two Pair on 1st 2 card pair and 2nd 2 card pair (cards are already sorted by rank)
        else if(firstCardRankOrdinal == secondCardRankOrdinal &&
                thirdCardRankOrdinal == fourthCardRankOrdinal &&
                firstCardRankOrdinal != thirdCardRankOrdinal){
            //System.out.println("Two pair on 1st 2 cards and 2nd 2 cards");
            //would already be sorted correctly for Extra credit

            player.setHandScore(2);
            return 2;
        }
        //check for Two Pair on 2nd pair of cards and last pair of cards (cards are already sorted by rank)
        else if(secondCardRankOrdinal == thirdCardRankOrdinal &&
                fourthCardRankOrdinal == fifthCardRankOrdinal &&
                firstCardRankOrdinal >= secondCardRankOrdinal){
            //System.out.println("Two pair on 2nd pair of cards and last pair of cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);

            player.setHandScore(2);
            return 2;
        }
        //check for Two Pair on 1st pair of cards and last pair of cards (cards are already sorted by rank)
        else if(firstCardRankOrdinal == secondCardRankOrdinal &&
                fourthCardRankOrdinal == fifthCardRankOrdinal &&
                thirdCardRankOrdinal >= fourthCardRankOrdinal){
            //System.out.println("Two pair");

            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);
            you.showCards();

            player.setHandScore(2);
            return 2;
        }
        //One Pair or Two of a Kind on 1st 2 cards
        else if(firstCardRankOrdinal == secondCardRankOrdinal &&
                firstCardRankOrdinal != thirdCardRankOrdinal &&
                firstCardRankOrdinal != fourthCardRankOrdinal &&
                firstCardRankOrdinal != fifthCardRankOrdinal){
            //System.out.println("One Pair or Two of a Kind on 1st 2 cards");

            //would already be sorted correctly for Extra credit

            player.setHandScore(1);
            return 1;
        }
        //One Pair or Two of a Kind on 2st 2 cards
        else if(secondCardRankOrdinal == thirdCardRankOrdinal &&
                firstCardRankOrdinal != secondCardRankOrdinal &&
                fourthCardRankOrdinal != secondCardRankOrdinal &&
                fifthCardRankOrdinal != secondCardRankOrdinal){
            //System.out.println("One Pair or Two of a Kind on 2nd 2 cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);

            player.setHandScore(1);
            return 1;
        }
        //One Pair or Two of a Kind on 3rd 2 cards
        else if(thirdCardRankOrdinal == fourthCardRankOrdinal &&
                thirdCardRankOrdinal != firstCardRankOrdinal &&
                thirdCardRankOrdinal != secondCardRankOrdinal &&
                thirdCardRankOrdinal != fifthCardRankOrdinal){
            //System.out.println("One Pair or Two of a Kind on 3rd 2 cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);
            //System.out.println("fdjsldkjflskdjflksdfsdf\n");
            player.showCards();

            player.setHandScore(1);
            return 1;
        }
        //One Pair or Two of a Kind on 4th 2 card pair
        else if(fourthCardRankOrdinal == fifthCardRankOrdinal &&
                fourthCardRankOrdinal != firstCardRankOrdinal &&
                fourthCardRankOrdinal != secondCardRankOrdinal &&
                fourthCardRankOrdinal != thirdCardRankOrdinal){
            //System.out.println("One Pair or Two of a Kind on last 2 cards");

            //EXTRA CREDIT sort cards in groups of pairs
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 3, 4);
            Collections.swap(player.getHandArrayList(), 1, 2);
            Collections.swap(player.getHandArrayList(), 2, 3);
            Collections.swap(player.getHandArrayList(), 0, 1);
            Collections.swap(player.getHandArrayList(), 1, 2);

            player.setHandScore(1);
            return 1;
        }
        else{
            //System.out.println("high card return 1");

            player.setHandScore(0);
            return 0;
        }//wow! that was a lot of if statements!
    }//end evaluateHand()


    public void calculateWinner(Draw gameDeck){
        //first see who's the winner out of all the AI Players
        Player winner = null;
        for(AI_Player a : AI_Players)
            for(AI_Player b : AI_Players)
                winner = this.whoWillWin(a, b);
        //now see who will win, the human, or the top AI Player...
        winner = this.whoWillWin(winner, you);

        //show all hands
        System.out.println("human  players cards:");
        this.you.showCards();
        int i = 0;
        for(AI_Player a : AI_Players)
        {
            i++;
            System.out.println("Ai Player " + i + " cards");
            a.showCards();
        }

        System.out.println("the winner is: ");
        if(winner.equals(you))
            System.out.println("the human.");
        else{
            System.out.println("ai player " + ((AI_Player) winner).getAIPlayerNumber());
            winner.showCards();
        }
    }//end evaluate_hands

    //this function will return the winner out of 2 players
    private Player whoWillWin(Player a, Player b) {
        if(a.getHandScore() > b.getHandScore())
            return a;
        else if(a.getHandScore() == b.getHandScore())
        {
            int x = 0;
            while(x < 5)
            {
                if(a.getHandArrayList().get(x).getRankEnum().ordinal() > b.getHandArrayList().get(x).getRankEnum().ordinal())
                    return a;
                else if(a.getHandArrayList().get(x).getRankEnum().ordinal() < b.getHandArrayList().get(x).getRankEnum().ordinal())
                    return b;
                else
                    x++;
            }
        }
        return b;
    }//end who will win

    public void swapPlayersCards(Draw gameDeck) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many cards would you like to discard? Your cards are:\n");
        you.showCards();
        String howManyCardsToSwap = "1";

        howManyCardsToSwap = keyboard.nextLine();

        while (!howManyCardsToSwap.matches("[0-9]+")) {
            System.out.print("Please type numbers only.\nHow many cards would you like to discard?\n");
            howManyCardsToSwap = keyboard.nextLine();
        }
        while( Integer.parseInt(howManyCardsToSwap) <= 0 || Integer.parseInt(howManyCardsToSwap) >3){
            System.out.print("Please type numbers between 1 an 5.Try again:\nHow many cards would you like to discard?\n");
            howManyCardsToSwap = keyboard.nextLine();
            while (!howManyCardsToSwap.matches("[0-9]+")) {
                System.out.print("Please type numbers only.Try again:\nHow many cards would you like to discard?\n");
                howManyCardsToSwap = keyboard.nextLine();
            }
        }



        String cardToSwap = "-1";
        for(int x = 0; x < Integer.parseInt(howManyCardsToSwap); x++){
            System.out.println("Enter 1 card at a time.");
            cardToSwap = keyboard.nextLine();
            while (!cardToSwap.matches("[0-9]+")) {
                System.out.print("Please type numbers only.Try again:\n Enter 1 card at a time.\n");
                cardToSwap = keyboard.nextLine();
            }
            while( Integer.parseInt(cardToSwap) <= 0 || Integer.parseInt(cardToSwap) >5){
                System.out.print("Please type numbers between 1 an 5.Try again:\n Enter 1 card at a time.\n");
                cardToSwap = keyboard.nextLine();
                while (!cardToSwap.matches("[0-9]+")) {
                    System.out.print("Please type numbers only.Try again:\n Enter 1 card at a time.\n");
                    cardToSwap = keyboard.nextLine();
                }
            }

            Card newCard = gameDeck.drawCard(gameDeck);
            you.getHandArrayList().set(Integer.parseInt(cardToSwap)-1, newCard);
            System.out.println("now your cards are...");
            you.showCards();
        }
        you.setHandScore(this.evaluateHand(you));

        //swap AI Player Cards
        for(int i = 0; i < this.numberOfAIPlayers; i++)
        {
            AI_Players.get(i).setHandScore(this.evaluateHand(AI_Players.get(i))); //needed for AIswapCardsAlgo
            AI_Players.get(i).AIswapCardsAlgo(gameDeck); //swap ai player array cards
            AI_Players.get(i).setHandScore(this.evaluateHand(AI_Players.get(i))); //set scores again after swapping
        }

        //keyboard.close();
    }
}
