package pl.com.bottega.game.application;


import pl.com.bottega.game.engine.Arbiter;
import pl.com.bottega.game.engine.ScoreBoard;
import pl.com.bottega.game.engine.players.ComputerPlayer;
import pl.com.bottega.game.engine.players.HumanPlayer;
import pl.com.bottega.game.engine.players.Player;
import pl.com.bottega.game.engine.players.SmartAssComputerPlayer;
import pl.com.bottega.game.engine.sound.BosseDevice;
import pl.com.bottega.game.engine.sound.MantaDevice;
import pl.com.bottega.game.engine.sound.SonyDevice;
import pl.com.bottega.game.engine.sound.SoundDevice;

import java.util.Scanner;

public class GameAppOO {

    static Scanner scanner = new Scanner(System.in);

    static Player player1;
    static Player player2;

    public static void main(String[] args){
        try {
            createPlayers();
            short gamesCount = readGamesCount();

            System.out.println("dzisiaj gra: " + player1 + " vs " + player2);//wywołaie toString

            Arbiter arbiter = new Arbiter(gamesCount, player1, player2);
            SoundDevice soundDevice = createSoundDevice();
            ScoreBoard primaryScoreBoard = new ScoreBoard(gamesCount, player1.getName(), player2.getName(), soundDevice);

            Arbiter.GameResult result;
            do {
                result = arbiter.playRound();
                primaryScoreBoard.refresh(result);
            } while (result != Arbiter.GameResult.GAME_OVER);

            System.out.println("========== RAPORT Z GRY ============");
            System.out.println(arbiter.generateReport());
        }
        catch (Exception ex){
            System.out.println("nieoczekiwany błąd: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void createPlayers() {
        System.out.print("Wybierz tryb: 1 - Comp vs Comp, 2 - Human vs Human, 3 - Human vs Comp: ");
        byte mode = scanner.nextByte();

        switch (mode) {
            case 1:
                player1 = new ComputerPlayer("HAL");
                player2 = new SmartAssComputerPlayer("Deep Blue");
                break;
            case 2:
                player1 = createHumanPlayer();
                player2 = createHumanPlayer();
                break;
            case 3:
                player1 = createHumanPlayer();
                player2 = new ComputerPlayer("Deep Blue");
                break;
        }
    }

    private static Player createHumanPlayer() {
        System.out.print("Podaj nazwę gracza: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        return new HumanPlayer(name);
    }

    private static SoundDevice createSoundDevice() {
        System.out.print("podaj nr urządzenia (1-sony, 2-bosse, 3-manta): ");
        byte nr = scanner.nextByte();
        switch (nr){
            case 1:
                return new SonyDevice();
            case 2:
                return new BosseDevice();
            case 3:
                return new MantaDevice();
        }
        return null;
    }

    private static short readGamesCount() {
        System.out.print("Podaj ilosc gier: ");
        short count = scanner.nextShort();
        scanner.nextLine();
        return count;
    }
}
