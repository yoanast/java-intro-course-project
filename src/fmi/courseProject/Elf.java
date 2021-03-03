package CourseProject;

import java.awt.*;

public class Elf extends Figure{

    public Elf(int row, int col, Color color) {
        super(row, col);
        this.color = color;
        this.attack = 5;
        this.shield = 1;
        this.health = 12;
        this.possibleAttackSquares = 3;
        this.speed = 3;
    }

    @Override
    public void drawFigure(Graphics g) {
        int x = this.col * Tile.TILE_SIZE;
        int y = this.row * Tile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x + 10,y + 10,50,50);
        g.setColor(Color.BLACK);
        g.drawString(" E ", x + 30, y + 40);
    }

}
