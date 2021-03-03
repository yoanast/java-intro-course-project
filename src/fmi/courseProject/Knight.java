package CourseProject;

import java.awt.*;

public class Knight extends Figure{

    public Knight(int row, int col, Color color) {
        super(row, col);
        this.color = color;
        this.attack = 8;
        this.shield = 3;
        this.health = 10;
        this.possibleAttackSquares = 1;
        this.speed = 1;
    }

    @Override
    public void drawFigure(Graphics g) {
        int x = this.col * Tile.TILE_SIZE;
        int y = this.row * Tile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x + 10,y + 10,50,50);
        g.setColor(Color.BLACK);
        g.drawString(" K ", x + 30, y + 40);
    }
}
