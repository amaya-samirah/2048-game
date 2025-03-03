package src;
import java.util.Scanner;

public class GameDriver {
    public static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        println("""
                Enter a Game Mode:
                --128--
                --256--
                --512--
                --1024--
                --2048--
                """);
        int mode = keyboard.nextInt();
        keyboard.nextLine();
        GameBoard gameBoard = GameBoard.getInstance(mode);
        //gameBoard.resetBoard();
        drawBoard(gameBoard);

        boolean play = true;
        while (play) {
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
            if (gameBoard.moveBlocks(input)) {
                println("YOU WIN!!!");
                play = false;
            } else {
                drawBoard(gameBoard);
            }
            
            
        }
        
        println("You have exited the game.");

    }

    public static void drawBoard(GameBoard board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                print("["+ board.getBlock(i, j)+"]");
                //print("\t");
            }
            println("");
        }
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String str) {
        System.out.println(str);
    }
}
