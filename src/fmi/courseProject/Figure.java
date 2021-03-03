package CourseProject;

import java.awt.*;

public abstract class Figure {

    protected int row;
    protected int col;
    protected Color color;
    protected int attack;
    protected int shield;
    protected int health;
    protected int possibleAttackSquares;
    protected int speed;

    public Figure(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract void drawFigure(Graphics g);



}
