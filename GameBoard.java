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

    private GameBoard() {
        setUpBoard();
    }

    public static GameBoard getInstance() {
        if (gameboard == null) {
            System.out.println("Creating new board...");
            gameboard = new GameBoard();
        }

        return gameboard;
    }

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

    public void resetBoard() {
        board = new int[4][4];
    }

    public int getBlock(int yPos, int xPos) {
        return board[yPos][xPos];
    }

    public int chooseBlock() {
        int block = 0;
        block = rand.nextInt(2);
        if (block == 0) {
            return 2;
        } else {
            return 4;
        }
    }

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

        //addBlock();
    }

}