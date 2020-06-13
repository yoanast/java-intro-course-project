package fmi.courseProject;

import java.util.Scanner;

/**
 * @author Йоана Стойковска
 */

public class Application {
    static Scanner scanner = new Scanner(System.in);
    public static Player player = new Player("Ти", "p", 1, 0, 1000);
    public static Player bot    = new Player("Бот", "b", 2, 0, 1000);
    static int activePlayerId = 0;
    public static boolean isGameRunning = true;
    public static boolean isCycleOver = false;


    public static void main(String[] args) {

        Company.initCompanies();
        Field[] gameBoard = new Field[20];
        GameBoard.fillGameBoard(gameBoard);
        GameBoard.shufflePositions(gameBoard);
        GameBoard.drawGameBoard(gameBoard);
        System.out.println();
        System.out.println();
        Player.StartFirst(gameBoard, scanner);
        System.out.println();
        System.out.println();

        while (isGameRunning) {

            while (isGameRunning && !isCycleOver) {

                Player.switchTurn(gameBoard, scanner);
            }

            Field.startNewCycle(gameBoard);
        }
    }


}
