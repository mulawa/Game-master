package pl.com.bottega.game.engine.players;

import java.util.Scanner;

public class HumanPlayer extends NamedPlayer {

    private static final int MAX_ERRORS_COUNT = 3;

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    public Hand giveHand() throws IllegalInputException {
        Hand hand = null;
        byte counter = 0;
        do{
            System.out.println(name + ", wpisz: kamien, nozyce, papier: ");
            String handStr = scanner.nextLine();
            hand = Hand.fromPolishWords(handStr);
            counter++;
        }while(wrongInput(hand) && doesNotExceedTries(counter));

        if (hand == null)
            throw new IllegalInputException("user exceeded " + MAX_ERRORS_COUNT + " attempts");

        return hand;
    }

    private boolean doesNotExceedTries(byte counter) {
        return counter < MAX_ERRORS_COUNT;
    }

    private boolean wrongInput(Hand hand) {
        return hand == null;
    }


}
