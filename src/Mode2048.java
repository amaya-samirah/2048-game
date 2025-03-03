package src;
/**
 * This is the 2048 game mode
 * @author Amaya Shabazz
 */

public class Mode2048 implements GameMode {
    private GameBoard board;

    public Mode2048(GameBoard board) {
        this.board = board;
    }

    @Override
    public boolean hasWon() {
        boolean won = false;

        return won;
    }
}
