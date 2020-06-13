package fmi.courseProject;

import java.util.Arrays;
import java.util.Collections;

public class GameBoard {

    /** Метод, чрез който запълваме игралната дъска със съответните полета.
     *
     * @param gameBoard Масив от полета
     */

    public static void fillGameBoard(Field[] gameBoard) {

        for (int i = 0; i <= 7; i++) {
            gameBoard[i] = new Field("Trap" + i, "| T ", i, i, "| T ",true);
        }

        for(int i = 7; i <= 10; i++ ) {
            gameBoard[i] = new Field("Invest" + i, "| I ", i, i, "| I ",true);
        }

        for(int i = 10; i <= 13; i++ ) {
            gameBoard[i] = new Field("Party" + i, "| P ", i, i,"| P ",true);
        }

        for(int i = 13; i <= 16; i++ ) {
            gameBoard[i] = new Field("Chance" + i, "| C ", i, i, "| C ",true);
        }

        for(int i = 16; i <= 19; i++ ) {
            gameBoard[i] = new Field("Steal" + i, "|St ", i, i,"|St ", true);
        }

        for(int i = 19; i <= 19; i++) {
            gameBoard[i] = new Field("Start" + i, "| S |", i, i, "| S |");
        }

    }

    /** Метод, чрез който визуализираме игралната дъска.
     *
     * @param gameBoard Масив от полета
     */

    protected static void drawGameBoard(Field[] gameBoard) {

        for(int i = 0; i <= 19; i++) {
            System.out.printf("%s", gameBoard[i].symbol);

            if(i == 7 || i == 9 || i == 11) System.out.println("|");
            if(i == 8 || i == 10) System.out.print("|                       ");
        }

    }

    /** Метод, чрез който разбъркваме позициите на полетата на игралната дъска.
     *
     * @param gameBoard Масив от полета
     */

    public static void shufflePositions(Field[] gameBoard) {

        int tempPos = 0;
        int tempId = 0;
        String tempName = null;
        String tempSymbol = null;
        String initSymbol = null;

        Collections.shuffle(Arrays.asList(gameBoard));

        for(int i = 0; i<= 19; i++) {
            if(gameBoard[i].getName().equals("Start19")) {
                tempPos = i;
                tempSymbol = gameBoard[19].getSymbol();
                tempName = gameBoard[19].getName();
                initSymbol = gameBoard[19].getInitSymbol();
                tempId = i;
                break;
            }

        }
        gameBoard[tempPos].setName(tempName);
        gameBoard[tempPos].setSymbol(tempSymbol);
        gameBoard[tempPos].setId(tempId);
        gameBoard[tempPos].setInitSymbol(initSymbol);
        gameBoard[19].setName("Start19");
        gameBoard[19].setSymbol("| S |");
        gameBoard[19].setInitSymbol("| S |");
        gameBoard[19].setId(19);
        setMovePositions(gameBoard);

    }

    /** Заради формата на дъската се налага да създадем метод , чрез който задаваме позиции на полетата, за да може
     *  играчът да се движи по нея по правилния(очаквания) начин.
     *
     * @param gameBoard Масив от полета
     */

    public static void setMovePositions(Field[] gameBoard) {

        gameBoard[0].setPosition(10);
        gameBoard[1].setPosition(11);
        gameBoard[2].setPosition(12);
        gameBoard[3].setPosition(13);
        gameBoard[4].setPosition(14);
        gameBoard[5].setPosition(15);
        gameBoard[6].setPosition(16);
        gameBoard[7].setPosition(17);

        gameBoard[8].setPosition(9);
        gameBoard[9].setPosition(18);
        gameBoard[10].setPosition(8);
        gameBoard[11].setPosition(19);

        gameBoard[12].setPosition(7);
        gameBoard[13].setPosition(6);
        gameBoard[14].setPosition(5);
        gameBoard[15].setPosition(4);
        gameBoard[16].setPosition(3);
        gameBoard[17].setPosition(2);
        gameBoard[18].setPosition(1);
        gameBoard[19].setPosition(0);


    }

}