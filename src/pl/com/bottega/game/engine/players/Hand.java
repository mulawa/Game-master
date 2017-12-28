package pl.com.bottega.game.engine.players;

public enum Hand {
    PAPER, SCISSORS, ROCK;

    public static Hand fromPolishWords(String word){
        switch (word.toLowerCase()){
            case "kamien": return ROCK;
            case "nozyce": return SCISSORS;
            case "papier": return PAPER;
            default: return null;
        }
    }

    public boolean betterThan(Hand hand2){
        if ((this == ROCK && hand2 == SCISSORS)
                || (this==SCISSORS && hand2 == PAPER)
                || (this==PAPER && hand2==ROCK))
            return true;
        return false;
    }
}
