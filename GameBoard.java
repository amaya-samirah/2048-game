import java.util.Random;

/**
 * This class deals with the game board
 * @author Amaya Shabazz
 */
public class GameBoard {
    
    private int board[][] = new int[4][4];  // 4x4 game board
    private int currXPos;
    private int currYPos;
    private static GameBoard gameboard;
    public Random rand = new Random();

    private GameBoard() {
        currXPos = 0;
        currYPos = 0;
    }

    public static GameBoard getInstance() {
        if (gameboard == null) {
            System.out.println("Creating new board...");
            gameboard = new GameBoard();
        }

        return gameboard;
    }

    /**
     * Sets up board for new game
     */
    public void setUpBoard() {
        // Starts with 2 blocks on board
        int startXPos1 = rand.nextInt(0, 4);
        int startYPos1 = rand.nextInt(0, 4);

        // Each block must be in different spaces
        int startXPos2 = rand.nextInt(0, 4);
        if (startXPos2 == startXPos1) {
            startXPos2 = rand.nextInt(0, 4);
        }

        int startYPos2 = rand.nextInt(0, 4);
        if (startYPos2 == startYPos1) {
            startYPos2 = rand.nextInt(0, 4);
        }

        // Game starts with either a 2 or 4 block
        int blockChoice1 = rand.nextInt(2);
        int blockChoice2 = rand.nextInt(2);

        if (blockChoice1 == 0) {
            // Set block to 2
            board[startXPos1][startYPos1] = 2;
        } else if (blockChoice1 == 1) {
            // Set block to 4
            board[startXPos1][startYPos1] = 4;
        }

        if (blockChoice2 == 0) {
            // Set block to 2
            board[startXPos2][startYPos2] = 2;
        } else if (blockChoice2 == 1) {
            // Set block to 4
            board[startXPos2][startYPos2] = 4;
        }

    }

    /**
     * Resets board for new game to be set up
     */
    public void resetBoard() {
        board = new int[4][4];
    }








}