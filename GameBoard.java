import java.util.Random;

/**
 * This class deals with the game board
 * @author Amaya Shabazz
 */
public class GameBoard {

    // ROWSxCOLUMNS
    private int board[][] = new int[4][4];  // 4x4 game board
    private static GameBoard gameboard;
    public Random rand = new Random();

    /**
     * Default constructor
     */
    private GameBoard() {
        setUpBoard();
    }

    /**
     * Instance of GameBoard
     * @return GameBoard
     */
    public static GameBoard getInstance() {
        if (gameboard == null) {
            System.out.println("Creating new board...");
            gameboard = new GameBoard();
        }

        return gameboard;
    }

    /**
     * Creates board for start of game
     */
    private void setUpBoard() {
        // Start with 2 blocks on board
        int xPos1 = rand.nextInt(4);
        int yPos1 = rand.nextInt(4);

        // Each block must be in a different space
        int xPos2 = rand.nextInt(4);
        while (xPos2 == xPos1) {
            xPos2 = rand.nextInt(4);
        }

        int yPos2 = rand.nextInt(4);
        while (yPos2 == yPos1) {
            yPos2 = rand.nextInt(4);
        }

        board[yPos1][xPos1] = chooseBlock();
        board[yPos2][xPos2] = chooseBlock();
    }

    /**
     * Resets board to starting point
     */
    public void resetBoard() {
        board = new int[4][4];
    }

    /**
     * Gets the block at certain point on board
     * @param yPos Block row
     * @param xPos Block coloumn
     * @return Value of the block
     */
    public int getBlock(int yPos, int xPos) {
        return board[yPos][xPos];
    }

    /**
     * Decides between 2 and 4 block
     * @return Value of block
     */
    public int chooseBlock() {
        int block = rand.nextInt(2);
        if (block == 0) {
            return 2;
        } else {
            return 4;
        }
    }

    /**
     * Adds block to empty spot on board
     */
    public void addBlock() {
        boolean empty = false;
        int y = 0;
        int x = 0;
        while (!empty) {
            y = rand.nextInt(4);
            x = rand.nextInt(4);

            empty = board[y][x] == 0;
        }
        board[y][x] = chooseBlock();
    }

    /**
     * Combines blocks with the same value
     * @param direction Direction the player has moved the blocks on board
     */
    public void combineBlocks(int direction) {
        // Blocks have already been moved
        switch (direction) {
            case 1:  // up
                // looking if block below
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        // if y=0 reached edge
                        if (y != 0 && (board[y][x] == board[y-1][x])) {
                            board[y-1][x] += board[y-1][x];
                            board[y][x] = 0;
                        }
                    }
                }
                break;
            case -1:  // down
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (y != 3 && (board[y][x] == board[y+1][x])) {
                            board[y+1][x] += board[y+1][x];
                            board[y][x] = 0;
                        }
                    }
                }
                break;
            case 2:  // right
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (x !=3 && (board[y][x] == board[y][x+1])) {
                            board[y][x+1] += board[y][x+1];
                            board[y][x] = 0;
                        }
                    }
                }
                break;
            case -2:  // left
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (x != 0 && (board[y][x] == board[y][x-1])) {
                            board[y][x-1] += board[y][x-1];
                            board[y][x] = 0;
                        }
                    }
                }
                break;
            default:
                throw new AssertionError();
        }

    }

    /**
     * Moves the blocks on the board
     * @param direction The direction the user has moved the blocks
     */
    public void moveBlocks(int direction) {
        switch (direction) {
            case 1:  // up
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (y != 0 && board[y][x] != 0) {
                            if (board[y-1][x] == 0) {
                                board[y-1][x] = board[y][x];
                                board[y][x] = 0;
                            }
                        }
                    }
                }
                combineBlocks(direction);
                break;
            case -1:  // down
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (y != 3 && board[y][x] != 0) {
                            if (board[y+1][x] == 0) {
                                board[y+1][x] = board[y][x];
                                board[y][x] = 0;
                            }
                        }
                    }
                }
                combineBlocks(direction);
                break;
            case 2:  // right
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (x != 3 && board[y][x] != 0) {
                            if (board[y][x+1] == 0) {
                                board[y][x+1] = board[y][x];
                                board[y][x] = 0;
                            }
                        }
                    }
                }
                combineBlocks(direction);
                break;
            case -2:  // left
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        if (x != 0 && board[y][x] != 0) {
                            if (board[y][x-1] == 0) {
                                board[y][x-1] = board[y][x];
                                board[y][x] = 0;
                            }
                        }
                    }
                }
                combineBlocks(direction);
                break;
            default:
                throw new AssertionError();
        }

        addBlock();
    }

}