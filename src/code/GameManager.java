import java.util.Scanner;
/**
 * This class is the innerworkings of the game
 * @author Amaya Shabazz
 */
public class GameManager {
    
    public Scanner keyboard = new Scanner(System.in);

    protected GameBoard board;

    public GameManager() {
        board = GameBoard.getInstance();
        board.setMode(getMode());
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
