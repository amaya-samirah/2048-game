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

        board[startXPos1][startYPos1] = chooseBlock();
        board[startXPos2][startYPos2] = chooseBlock();
    }

    /**
     * Resets board for new game to be set up
     */
    public void resetBoard() {
        board = new int[4][4];
    }

    /**
     * Decides between a 2 block or a 4 block
     * @return The corresponding number for the block
     */
    public int chooseBlock() {
        // Game starts with either a 2 or 4 block
        int blockChoice = rand.nextInt(2);

        if (blockChoice == 0) {
            // Set block to 2
            return 2;
        } else {
            // Set block to 4
            return 4;
        }
    }

    /**
     * Adds 2 or 4 block to board
     */
    public void addBlock() {
        // search for empty space
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (board[x][y] == 0) {
                    board[x][y] = chooseBlock();
                }
            }
        }
    }

    /**
     * If two of the same blocks are in the same spot, they are added together
     * @param xPos The x position on the board
     * @param yPos The y position on the board
     * @param direction The direction the blocks were moved
     */
    public void combineBlocks(int xPos, int yPos, int direction) {
        // doBlocksEqual check if blocks the same
        switch (direction) {
            case 1:  // up/down
                board[xPos][yPos] += board[xPos][yPos];
                // if blocks combined then block moved resets
                board[xPos][yPos+1] = 0;
                break;
            case 2:  // right/left
            board[xPos][yPos] += board[xPos][yPos];
            // if blocks combined then block moved resets
            board[xPos+1][yPos] = 0;
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Checks the board for blocks moved next to each other that equal
     * @param direction The direction the blocks were moved
     */
    public void doBlocksEqual(int direction) {
        // blocks will only combine with blocks in the direction the board was moved
        // check if blocks the same & if not do nothing
        switch (direction) {
            case 1:  // up/down
                // if blocks moved up/down were moved adjacent to same block
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 3; y++) {  // past 3 is out of bounds
                        // if up then x values stay the same so check for y values
                        if (board[x][y] == board[x][y+1]) {
                            combineBlocks(x, y, direction);
                        }
                    }
                }
                break;
            case 2:  // right/left
                // if blocks moved right/left were moved adjacent to same block
                for (int x = 0; x < 3; x++) { // past 3 is out of bounds
                    for (int y = 0; y < 4; y++) {
                        if (board[x][y] == board[x+1][y]) {
                            combineBlocks(x, y, direction);
                        }
                    }

                }
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Moves ALL the blocks on the board
     * @param direction The directions blocks were moved (up, down, left, right)
     */
    public void moveBlock(int direction) {
        switch (direction) {
            case 1:  // up
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        board[x][y] = board[x][y-1];  // descreases -> top
                        // after moving reset previous spot
                        board[x][y-1] = 0;
                    }
                }
                doBlocksEqual(1);
                break;
            case -1:  // down
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 3; y++) {
                        board[x][y] = board[x][y+1];  // increases -> bottom 
                        // after moving reset previous spot
                        board[x][y+1] = 0;
                    }
                }
                doBlocksEqual(1);
                break;
            case 2:  // right
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 4; y++) {
                        board[x][y] = board[x+1][y];  // increases -> right
                        // after moving reset previous spot
                        board[x+1][y] = 0;
                    }
                }
                doBlocksEqual(2);
                break;
            case -2:  // left
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        board[x][y] = board[x-1][y];  // decreases -> left
                        // after moving reset previous spot
                        board[x-1][y] = 0;
                    }
                }
                doBlocksEqual(2);
                break;
            default:
                throw new AssertionError();
        }
    }






}