package pl.com.bottega.game.engine.players;

public abstract class NamedPlayer implements Player {

    protected String name;

    protected NamedPlayer(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "fajny " + name;
    }
}
