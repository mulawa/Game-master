package pl.com.bottega.game.application;

import java.util.Random;
import java.util.Scanner;

public class GameApp {
    private static final String ROCK = "kamien";
    private static final String SCISSORS = "nozyce";
    private static final String PAPER = "papier";

    private static String userName;
    private static Scanner scanner5 = new Scanner(System.in);
    private static Random random = new Random();

    private static int points1;
    private static int points2;

    public static void main(String[] args){
        readUserName();
        System.out.println("Witaj " + userName + " w grze PNK ");

        int max = readRoundsCount();
        int counter = 0;
        while(counter < max) {
            counter++;

            String hand1 = readUserHand();
            if (hand1 == null)
                return;
            String hand2 = generateComputerHand();

            int result = calculateResult(hand1, hand2);//0 - remis, 1 - wygrał gracz1, 2 - wygrał gracz2
            countPoints(result);
            displayRoundResult(result);
        }

        displayMatchResult();
    }

    private static void readUserName(){
        System.out.print("Wpisz imię: ");
        userName = scanner5.nextLine();
    }

    private static int readRoundsCount(){
        System.out.print("Podaj ilosc gier: ");
        int count = scanner5.nextInt();
        scanner5.nextLine();
        return count;
    }

    private static String readUserHand(){
        System.out.println("Wpisz: kamien, nozyce, papier");

        String hand = scanner5.nextLine();
        if (!(hand.equals(ROCK) || hand.equals(SCISSORS) || hand.equals(PAPER))) {
            System.out.println("input error");
            return null;
        }

        return hand;
    }

    private static String generateComputerHand(){
        //Random random = new Random();
        int r = random.nextInt(3);
        String computerHand = null;
        switch (r) {
            case 0:
                computerHand = ROCK;
                break;
            case 1:
                computerHand = SCISSORS;
                break;
            case 2:
                computerHand = PAPER;
                break;
        }

        return computerHand;
    }

    private static int calculateResult(String p1, String p2){
        if (p1.equals(p2))
            return 0;
        else {
            //wygrywa P1
            if ((p1.equals(PAPER) && p2.equals(ROCK))
                    ||
                    (p1.equals(ROCK) && p2.equals(SCISSORS))
                    ||
                    (p1.equals(SCISSORS) && p2.equals(PAPER))) {
                return 1;
            } else {
                return 2;
            }
        }
    }



    private static void displayMatchResult(){
        if (points1 == points2)
            System.out.println("remis w meczu");
        else{
            if (points1 < points2)
                System.out.println("przegrałeś mecz");
            else
                System.out.println("wygrałeś mecz");
        }
    }

    private static void displayRoundResult(int result) {
        if (result == 0)
            System.out.println("remis");
        else if (result == 1)
            System.out.println("wygrałeś rundę");
        else
            System.out.println("przegrałeś rundę");

    }

    private static void countPoints(int result) {
        if (result == 1)
            points1++;
        else if (result == 2)
            points2++;
    }
}