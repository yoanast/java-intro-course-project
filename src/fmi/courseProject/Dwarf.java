package CourseProject;

import java.awt.*;

public class Dwarf extends Figure{

    public Dwarf(int row, int col, Color color) {
        super(row, col);
        this.color = color;
        this.attack = 6;
        this.shield = 2;
        this.health = 12;
        this.possibleAttackSquares = 2;
        this.speed = 2;
    }

    @Override
    public void drawFigure(Graphics g) {
        int x = this.col * Tile.TILE_SIZE;
        int y = this.row * Tile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x + 10,y + 10,50,50);
        g.setColor(Color.BLACK);
        g.drawString(" D ", x + 30, y + 40);
    }
}
