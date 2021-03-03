package CourseProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameBoard extends JFrame {

    public static int firstRandomNumber;
    public static int secondRandomNumber;
    public Object[][] tileCollection;
    public Object[][] figureCollection;

    public GameBoard() {

        this.setSize(650, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.tileCollection = new Object[7][9];
        fillTileCollection();
        this.figureCollection = new Object[7][9];
        fillP1FigureCollection();
        fillP2FigureCollection();
    }

    /**
     *  Метод, чрез който визуализираме полетата по дадени ред и колона.
     */
    private void renderTiles(Graphics g, int row, int col) {
        if (this.isThereTile(row, col)) {
            Tile t = (Tile)this.getTile(row, col);
            t.drawTiles(g);
        }
    }

    private void renderFigures(Graphics g, int row, int col) {

        if(this.isThereFigure(row,col)) {
            Figure fig = (Figure)this.getFigure(row,col);
            fig.drawFigure(g);
        }
    }

    /**
     *  Метод, чрез който изчертаваме игралната дъска и нейните елементи.
     */
    @Override
    public void paint(Graphics g) {

        for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 9; col++) {

                this.renderTiles(g,row,col);
                this.renderFigures(g, row, col);
            }
        }
    }

    /**
     *  Метод, който ни връща обект от колекцията по зададени ред и колона.
     */
    public Object getFigure(int row, int col) {
        return this.figureCollection[row][col];
    }

    /**
     *  Метод, който ни връща дали на дадени ред и колона има фигура.
     */
    public boolean isThereFigure(int row, int col) {
        return this.getFigure(row, col) != null;
    }

    /**
     *  Метод, който ни връща обект от колекцията по зададени ред и колона.
     */
    public Object getTile(int row, int col) {
        return this.tileCollection[row][col];
    }

    /**
     *  Метод, който ни връща дали на дадени ред и колона вече има поле.
     */
    public boolean isThereTile(int row, int col) {
        return this.tileCollection[row][col] != null;
    }

    /**
     *  Метод, чрез който пълним колекцията на дъската с обекти от тип Tile.
     */
    public void fillTileCollection() {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                this.tileCollection[i][j] = (new PlayerATerritory(i,j));
            }
        }

        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                this.tileCollection[i][j] = (new Battlefield(i,j));
            }
        }

        for (int i = 5; i <= 6; i++) {
            for (int j = 0; j < 9; j++) {
                this.tileCollection[i][j] = (new PlayerBTerritory(i,j));
            }
        }
    }

    /**
     *  Метод, чрез който получаваме две случайни числа и ги записваме на променливите.
     */
    public static void getRandomPositionForP1Figure() {

        Random rand = new Random();
        firstRandomNumber = rand.nextInt(2);
        secondRandomNumber = rand.nextInt(9);
    }

    public static void getRandomPositionForP2Figure() {

        Random rand = new Random();
        firstRandomNumber = rand.nextInt(7);
        secondRandomNumber = rand.nextInt(9);
        while (firstRandomNumber != 5 && firstRandomNumber != 6 ) {
            firstRandomNumber = rand.nextInt(7);
        }
    }

    /**
     *  Метод, чрез който пълним колекция с обекти от тип Figure.
     */
    public void fillP1FigureCollection() {

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP1Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Knight(firstRandomNumber, secondRandomNumber, Color.PINK));
            } else i--;
        }

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP1Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Elf(firstRandomNumber, secondRandomNumber, Color.PINK));
            } else i--;
        }

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP1Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Dwarf(firstRandomNumber, secondRandomNumber, Color.PINK));
            } else i--;
        }
    }

    /**
     *  Метод, чрез който пълним колекция с обекти от тип Figure.
     */
    public void fillP2FigureCollection() {

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP2Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Knight(firstRandomNumber, secondRandomNumber, Color.CYAN));
            } else i--;
        }

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP2Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Elf(firstRandomNumber, secondRandomNumber, Color.CYAN));
            } else i--;
        }

        for(int i = 0; i < 2; i++) {
            getRandomPositionForP2Figure();
            if(!this.isThereFigure(firstRandomNumber, secondRandomNumber)) {
                this.figureCollection[firstRandomNumber][secondRandomNumber] =
                        (new Dwarf(firstRandomNumber, secondRandomNumber, Color.CYAN));
            } else i--;
        }
    }



}
