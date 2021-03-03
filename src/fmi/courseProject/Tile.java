package CourseProject;

import java.awt.*;

public abstract class Tile {

    protected int row;
    protected int col;
    protected String id;
    public static final int TILE_SIZE = 70;
    public static Color lightGray = new Color(221, 221, 221);
    public static Color nGray = new Color(170, 170, 170);
    public static Color nBlack = new Color(68, 68, 68);

    /**
     *  Конструктор за полетата.
     */
    public Tile (int row, int col) {

        this.row = row;
        this.col = col;
    }

    /**
     *  Метод, чрез който визуализираме отделните полета на дъската.
     */
    public abstract void drawTiles(Graphics g);

    /**
     *  Метод, чрез който визуализираме рамка на полетата.
     */
    public void drawBorders(Graphics g, int tileX, int tileY) {

        g.setColor(nBlack);
        g.drawRect(tileX,tileY,TILE_SIZE,TILE_SIZE);
    }

    public String getId() {
        return id;
    }
}
