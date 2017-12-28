package pl.com.bottega.game.engine;

import pl.com.bottega.game.engine.sound.SoundDevice;

public class ScoreBoard {
    private short points1;
    private short points2;

    private short maxRounds;
    private short currentRound;

    private String host;
    private String guest;
    private SoundDevice soundDevice;

    public ScoreBoard(short maxRounds, String host, String guest, SoundDevice soundDevice) {
        if (maxRounds <= 0)
            throw new IllegalArgumentException("max rounds can not be null");
        if (host == null || host.trim().equals(""))
            throw new IllegalArgumentException("host name can not be empty");
        if (guest == null || guest.trim().equals(""))
            throw new IllegalArgumentException("guest name can not be empty");

        this.maxRounds = maxRounds;
        this.host = host;
        this.guest = guest;
        this.soundDevice = soundDevice;
    }

    public void refresh(Arbiter.GameResult result){
        if (result == Arbiter.GameResult.GAME_OVER){
            displaySummary();
            return;
        }

        currentRound++;

        String message = null;
        switch (result){
            case TIE:
                message = "remis";
                break;
            case P1:
                points1++;
                message = "wygrał " + host;
                break;
            case P2:
                message = "wygrał " + guest;
                points2++;
        }

        displayCurrentState(message);
    }

    private void displayCurrentState(String message) {
        System.out.println(currentRound + ". " + message + " - " + host + ": " + points1 + " | " + guest + ": " + points2);
    }

    private void displaySummary() {
        if (points1 == points2)
            System.out.println("remis");
        else {
            String nr = points1 > points2 ? host : guest;
            System.out.println("Wygrał gracz: " + nr + " punktami: " + Math.abs(points1 - points2));

            if (soundDevice != null)
                soundDevice.playSound();
        }
    }
}
