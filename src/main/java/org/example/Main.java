package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String stopGame = "1";
        while (!stopGame.equalsIgnoreCase("0")) {
            Draw gameDeck = new Draw();
            Hands hand = new Hands(gameDeck);
            System.out.println("The deck is being shuffled!\n");//shuffle function is actually called in the hands constructor
            System.out.println("The cards are being delt to " + hand.getNumberOfPlayers() + " players\n");
            hand.swapPlayersCards(gameDeck);
            hand.calculateWinner(gameDeck);
            System.out.println("press any key to continue or 0 to exit");
            stopGame = keyboard.nextLine();
        }
    }
}