package pl.com.bottega.game.engine.players;


public class SmartAssComputerPlayer extends ComputerPlayer{
    private static final int CONSCIENCE_RATIO = 3;
    private Hand opponentHand;
    private int counter;

    public SmartAssComputerPlayer(String name) {
        super(name);
    }

    public void cheat(Hand opponetHand){
        this.opponentHand = opponetHand;
    }

    @Override
    public Hand giveHand() {
        counter++;
        if (counter % CONSCIENCE_RATIO == 0){
            return strongerHand();
        }
        return super.giveHand();
    }

    private Hand strongerHand() {
        for(Hand hand : Hand.values()){
            if (hand.betterThan(opponentHand))
                return hand;
        }
        return null;
    }
}
