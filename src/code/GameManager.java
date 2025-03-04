import java.util.Scanner;
/**
 * This class is the innerworkings of the game
 * @author Amaya Shabazz
 */
public class GameManager {
    
    public Scanner keyboard = new Scanner(System.in);

    protected GameBoard board;
    private int bestScore;

    public GameManager(int userBestScore) {
        board = GameBoard.getInstance();
        bestScore = userBestScore;
    }

    public void run() {
        println("---WELCOME TO 2048!!!---");
        boolean quit = false;

        while (!quit) {
            quit = play();
        }

        println("---GOODBYE!!!---");
    }

    public boolean play() {

        int mode = getMode();
        board.setMode(mode);

        drawBoard();

        boolean play = true;
        while (play) {
            println("----------------");
            println("Current Score: "+board.getScore());
            println("----------------");
            println("""
                    Enter 1 to move up:
                    Enter -1 to move down
                    Enter 2 to move right:
                    Enter -2 to move left:
                    Enter 0 to quit:""");
            println("----------------");
            int input = keyboard.nextInt();
            keyboard.nextLine();
            if (input == 0) {
                play = false;
                break;
            }
            drawBoard();
            
            if (board.moveBlocks(input)) {
                println("---YOU WIN!!!---");
                play = false;
            }
            if (board.hasLost()) {
                println("---YOU LOSE!!!---");
                play = false;
            }
        }
        
        println("---GAME OVER---");
        return play;
    }

        
    

    public void drawBoard() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                print("["+board.getBlock(y, x)+"]");
            }
            println("");
        }
    }

    private int getMode() {
        println("""
            Enter a Game Mode:
            128
            256
            512
            1024
            2048
            """);
        int mode = keyboard.nextInt();
        keyboard.nextLine();

        return mode;
    }

    public void print(String str) {
        System.out.print(str);
    }

    public void println(String str) {
        System.out.println(str);
    }
}
