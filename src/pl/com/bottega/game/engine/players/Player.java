package pl.com.bottega.game.engine.players;

public interface Player {
    public Hand giveHand() throws IllegalInputException;
    public String getName();
}
