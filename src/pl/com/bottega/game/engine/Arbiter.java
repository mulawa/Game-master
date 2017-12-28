package pl.com.bottega.game.engine;

import pl.com.bottega.game.engine.players.Hand;
import pl.com.bottega.game.engine.players.IllegalInputException;
import pl.com.bottega.game.engine.players.Player;
import pl.com.bottega.game.engine.players.SmartAssComputerPlayer;

public class Arbiter{

    public enum GameResult{
        P1, P2, TIE, GAME_OVER;
    }

    private Player player1;
    private Player player2;

    private short currentRound;
    private short maxRounds;

    private String[] matchLog;

    public Arbiter(short maxRounds, Player player1, Player player2){
        if (maxRounds <= 0)
            throw new IllegalArgumentException("max rounds must be gt 0");
        if (player1 == null || player2 == null)
            throw new IllegalArgumentException("player can not be null");

        this.maxRounds = maxRounds;
        this.player1 = player1;
        this.player2 = player2;
        this.matchLog = new String[maxRounds];
    }

    public Arbiter(Player player1, Player player2){
        this((short)10, player1, player2);
    }

    public GameResult playRound(){
        if (currentRound == maxRounds)
            return GameResult.GAME_OVER;

        Hand hand1, hand2;
        try {
            hand1 = player1.giveHand();

            //ANTYPRZYKŁAD - nieodpowiednia abstrakcja
            if (player2 instanceof SmartAssComputerPlayer) {
                SmartAssComputerPlayer smartAss = (SmartAssComputerPlayer) player2;
                smartAss.cheat(hand1);
            }
            hand2 = player2.giveHand();
        }
        catch (IllegalInputException e){
            //System.out.println("błąd!!!");
            //ALBO obsługa ALBO propagacja
            throw new IllegalStateException("players are too stupid to play", e);
        }

        GameResult result = calculateResult(hand1, hand2);
        addToLog(hand1, hand2, result);
        currentRound++;

        return result;
    }

    private void addToLog(Hand hand1, Hand hand2, GameResult result) {
        //optymalizacja przez prekompilator bedzie dpolegac na zmianie operatora + na append z kalsy StringBuilder
        matchLog[currentRound] = player1.getName() + ":" + hand1 + " VS " + player2.getName() + ":" + hand2 + " ==> " + result;
    }

    public String generateReport(){
        StringBuilder report = new StringBuilder();

        int i = 0;
        for (String entry : matchLog){
            report.append(i).append(". ").append(entry).append("\n");
            i++;
        }

        return report.toString();
    }

    private GameResult calculateResult(Hand hand1, Hand hand2) {
        if (hand1 == null || hand2 == null)
            return GameResult.TIE;

        if (hand1.betterThan(hand2))
            return GameResult.P1;
        if (hand2.betterThan(hand1))
            return GameResult.P2;
        return GameResult.TIE;
    }
}
